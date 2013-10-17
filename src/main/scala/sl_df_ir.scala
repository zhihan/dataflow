package sl.ir

import my.se._
import scala.collection.mutable.Map
import scala.collection.mutable.ArrayBuffer

/**
 * A dataflow node can be either a variable node or a proc node.
 *
 * - Variable node models variables used for signals, state and data stores.
 * - Proc node models block methods that uses or writes to the variables.
 *
 */

import sl.ir._

abstract class DataflowNode extends AnyRef with HasId
case class Var(override val id:Int) extends DataflowNode 
{
}
case class Proc(override val id:Int) extends DataflowNode
{
}

/**
 * A Simulink dataflow graph consists of a set of dataflow nodes
 * and dataflow edges. Multiple edges can exists between two distinct nodes.
 * Self-loop is not allowed.
 */
class DataflowGraph() {
  val g = new Graph()  

  // Get the node for a given Id
  val nodes = Map[Int, DataflowNode]()

  def nNodes = g.V.size
  def nEdges = g.E.size

  private def isProc(n:DataflowNode) = {
    n match {
      case Var(_) => false
      case Proc(_) => true 
    }
  }

  def newVarNode(name:String) = {
    val v = g.newVertex(name)
    val n = Var(v.id)
    nodes(v.id) = n
    n
  }

  def getVarNodes(name:String) = {
    g.V.filter(_.sid == name)
  }

  def newProcNode(name:String) = {
    val v = g.newVertex(name)
    val n = Proc(v.id)
    nodes(v.id) = n
    n
  }

  def addEdge(src:Int, dst:Int) = { 
    if (src == dst) {
      throw new RuntimeException("No self-loop is allowed")
    } else {
      g.addEdge(src, dst)
    }
  }

  def addEdge(src:DataflowNode, dst:DataflowNode) = 
    g.addEdge(src.id, dst.id)
  
  
  
  def toDotString(): String = {
    
    def vLabel(v:Vertex):String = {
      val n = nodes(v.id)
      val shape = 
        n match {
          case Var(_) => "[shape=\"ellipse\"]"
          case Proc(_) => "[shape=\"box\"]"
      }
      val label = "[label=\"" + v.sid + "\"]"
      shape + label
    }
  
    def eLabel(e:Edge):String = {
      "[label=\"" + e.id + "\"]"
    }

    writeGraphviz(g, vLabel, eLabel)
  }

  // Bus analysis
  val busActions = Map[Int, BusAction]()
  def setBusAction(p:Proc, a:BusAction) {
    busActions(p.id) = a
  }

  class BusReachBack(graph:Graph, 
		     busProcs: Map[Int,BusAction],  
		     busElementEdge: Map[Int, BusSelect],
		     inactive: Inactive) {

    val busReached = Map[Int, SubBus]()

    // Test if a variable vertex is bus by testing its one and 
    // only writer.
    private def isVarBus(v:Vertex) = {
      val inE = graph.inE(v)
      if (inE.size == 1) {
	val writerP = inE(0).from
	if (busProcs.contains(writerP.id)) true
	else false
      } else { 
	// shared data cannot be bus (XXX)
	val writerP = inE(0).from
	if (busProcs.contains(writerP.id)) true
	else false
      }
    }

    // Visit a Var node with a bus-capable writer
    private def visitVarWithBusWriter(v:Vertex,
                                      p:Vertex):Boolean = {
      busProcs(p.id) match {
        case BusCreate(_,_) | BusPass(_) => {
          if (busReached.contains(v.id)) {

            val current = busReached(v.id)
            val before = busReached.getOrElse(p.id, SubBusOp.empty(current))
            if (! SubBusOp.isSubset(current, before)) {
	      busReached(p.id) = SubBusOp.union(current, before)
              true
            } else 
              false
          } else {
            throw new RuntimeException("Error here" + p.sid + "=>" + v.sid)
            true
          }
        } 
      }
    }

    // Visiting a proc which reads a sub-bus from a var.
    //   (BusVar) --> [proc] 
    private def visitBusReader(proc:Vertex,
			       v:Vertex): Boolean = {
      def result(current:SubBus) = {
        val before = busReached.getOrElse(v.id, SubBusOp.empty(current))
        if (!SubBusOp.isSubset(current,before)) {
          busReached(v.id) = SubBusOp.union(current, before)
          true
        } else
          false
      }

      val e = graph.getEdge(v.id, proc.id)
      if (busElementEdge.contains(e.id)) {
        // There is a selection between busVar and proc
        val bs = busElementEdge(e.id)
        val b = bs.bus
        // Reached elements at [proc]
        val vReached = 
	  if (busReached.contains(proc.id)) busReached(proc.id).elements else Set(0)
        val i = bs.i
        // Compute the corresponding reached at (BusVar)
        val current = SubBus(b, b.fromDescendant(i, vReached))
        
        result(current)
      } else {
        // There is no selection between busVar and proc
        assert(busReached.contains(proc.id))
        val current = busReached(proc.id)
        result(current)
      }

    }

    // Visit a bus-capable proc
    // 
    //  (a) ---> | proc |
    //  (b) ---> |      |
    private def visitBusProc(v:Vertex,
                             pred:ArrayBuffer[Vertex]): ArrayBuffer[Vertex] = {
      
      busProcs(v.id) match {
        case BusPass(b) => { 
	  val next = ArrayBuffer[Vertex]()
          for (p <- pred) {
            val current = busReached(v.id)
            val before = busReached.getOrElse(p.id, SubBus(b, Set[Int]()))
            if (! SubBusOp.isSubset(current, before)) {
	      busReached(p.id) = SubBusOp.union(current, before)
              next += p
            }
            
          } 
          next
        }
        case BusCreate(b,srcEdges) => {
          // For virtual bus selector, get the sub-bus at the source
          def getSrcBusSel(srcEId:Int, dstSet:Set[Int]) = {
            val busSelection = busElementEdge(srcEId)
            val srcBus = busSelection.bus
            val i = busSelection.i
            (srcBus, SubBus(srcBus, srcBus.fromDescendant(i, dstSet)))
          }

          val next = ArrayBuffer[Vertex]()
          val reached = busReached(v.id).distribute
	  var src = srcEdges // Index into array
          for ((c,r) <- reached) {
            val srcEId = src.head
            // (p)--->[v]
            val p = graph.getE(srcEId).from
            c match {
              case _:AtomicElement => 
                if (!r.isEmpty) {
                  if (busElementEdge.contains(srcEId)) {
                    // (p)==Bus Select==>[v]
                    // First get the sub-bus of the signal at target inport
                    val (srcBus, current) = getSrcBusSel(srcEId, Set(0))
                    val before = busReached.getOrElse(p.id, SubBus(srcBus, Set[Int]()))
                    if (!SubBusOp.isSubset(current, before)) {
                      busReached(p.id) = SubBusOp.union(current, before)
                      next += p
                    } 
                  } else {
                    // bus element is reached
                    if (!bfs.visited.contains(p)) next += p
                  }
                }
              case bc:Bus => {
                if (busElementEdge.contains(srcEId)) {
                  // (p)==Bus Select==>[v]
                  // First get the sub-bus of the signal at target inport
                  val (srcBus,current) = getSrcBusSel(srcEId, r)
                  val before = busReached.getOrElse(p.id, SubBus(srcBus, Set[Int]()))
                  if (!SubBusOp.isSubset(current, before)) {
                    busReached(p.id) = SubBusOp.union(current, before)
                    next += p
                  } 
                } else {
                  // (p)==bus==>[v]
                  val current = SubBus(bc, r)
                  val before = busReached.getOrElse(p.id, SubBus(bc, Set[Int]()))
                  if (! SubBusOp.isSubset(current, before)) {
	            busReached(p.id) = SubBusOp.union(current, before)
                    next += p
                  }
                }
              }
            }
            src = src.tail
          }
          next
        }
      }  
    }

    // Reachability in terms of buses
    private def visitBackward(v:Vertex):ArrayBuffer[Vertex] = {
      // In the BFS, need to distinguish between Var and Proc and
      // handle bus logics
      val pred = Graph.filteredPredecessor(graph,v,inactive)
      nodes(v.id) match {
        case Var(_) => {
	  // visit variable
          val next = ArrayBuffer[Vertex]()
          for (p <- pred) {
            if (busProcs.contains(p.id)) {
              if (visitVarWithBusWriter(v, p)) next += p
            } else {
              // Non-bus proc
              if (!bfs.visited.contains(p)) next += p
            }
          }
          next
        }
        case Proc(_) => 
          if (busProcs.contains(v.id)) {
	    // Visit a bus-capable proc
            visitBusProc(v, pred)
          } else {
            val next = ArrayBuffer[Vertex]()
            // Non-bus proc
            for (p <- pred) {
	      if (isVarBus(p)) {
		if (visitBusReader(v, p)) next += p
	      } else {
		if (!bfs.visited.contains(p)) next += p
	      }
            }
            next
          }
        
      }
    }

    val bfs = new BFS(visitBackward)

    def run(start:Array[Int]) = {
      bfs.initialize(graph.getV(start))
      bfs.runAlways()
      (bfs.visited, busReached)
    }
  }

  def backreachBus(src:Array[Int], inactive:Inactive, 
                   busProcs:Map[Int,BusAction],
		   busElemEdge: Map[Int, BusSelect]) = {
    val reach = new BusReachBack(g, busProcs, busElemEdge, inactive)
    reach.run(src)
  }

  // Forward reach
  class BusReachFor(graph:Graph, 
		    busProcs:Map[Int,BusAction], 
		    busElemEdge: Map[Int, BusSelect],
		    inactive:Inactive) {

    val busReached = Map[Int, SubBus]()

    // Visit a Var node with a bus-capable reader
    private def visitVarWithBusReader(v:Vertex,
                                      p:Vertex,
				      reached: Set[Int]):Boolean = {
      busProcs(p.id) match {
          case BusPass(_) => {
            val current = busReached(v.id)
            val before = busReached.getOrElse(p.id, SubBusOp.empty(current))
            if (! SubBusOp.isSubset(current, before)) {
	      busReached(p.id) = SubBusOp.union(current, before)
              true
            } else 
              false
          }
        case BusCreate(b, srcEdges) => {
	  val e = graph.getEdge(v.id, p.id)
          val cs = srcEdges.map(i => if (e.id == i) Set[Int](0) else Set[Int]())
          val current = SubBus(b, b.collect(cs))
          val before = busReached.getOrElse(p.id, SubBusOp.empty(current))
            if (! SubBusOp.isSubset(current, before)) {
	      busReached(p.id) = SubBusOp.union(current, before)
              true
            } else 
              false
        }
      }
    }
    
    // Utility function, visit a variable that is the output of a selector
    // returns
    //
    //  (bus) -- ... (virtual selector) ... --> [reader]
    // 
    private def visitBusVarReader(v: Vertex, 
				  reader: Vertex) : Option[(Vertex,Set[Int])] = {
      val reached = busReached(v.id) // reached subbus
      val b = reached.bus
      val e = graph.getEdge(v.id, reader.id)
      val bSel = busElemEdge(e.id)
      val sel = Set(bSel.i) // selected subbus
      val inter = b.intersect(sel, reached.elements)
      
      if (!inter.isEmpty){ 
        val selected = b.get(bSel.i)
        selected match {
	  case sb:Bus => {
            val c = b.distribute(inter) // This cannot be distribute.
            val current = SubBus(sb, c(bSel.i))
            val before = busReached.getOrElse(reader.id, SubBus(sb, Set[Int]()))
            if (!SubBusOp.isSubset(current, before)) {
              Some(reader, current.elements)
            } else 
              None
	  }
	  case _:AtomicElement => {
            if (!bfs.visited.contains(reader)) Some(reader, Set(0)) 
            else None
	  }   
        }
      } else None
    }

    // Visit a bus-capable proc
    // [v] => (vars)
    private def visitBusProc(v:Vertex,
                             vars:ArrayBuffer[Vertex]): ArrayBuffer[Vertex] = {
      
      busProcs(v.id) match {
        case BusPass(b) => {
          val next = ArrayBuffer[Vertex]()
          for (p <- vars) {
            val current = busReached(v.id)
            val before = busReached.getOrElse(p.id, SubBus(b, Set[Int]()))
            if (! SubBusOp.isSubset(current, before)) {
	      busReached(p.id) = SubBusOp.union(current, before)
              next += p
            }
          }
          next
        }
        case BusCreate(b, _) => {
          val next = ArrayBuffer[Vertex]()
          for (p <- vars) {
            val current = busReached(v.id)
            val before = busReached.getOrElse(p.id, SubBus(b, Set[Int]()))
            if (! SubBusOp.isSubset(current, before)) {
	      busReached(p.id) = SubBusOp.union(current, before)
              next += p
            }
          }
          next
        }
      }  
    }

    // Test if a variable vertex is bus by testing its one and 
    // only writer.
    private def isVarBus(v:Vertex) = {
      val inE = graph.inE(v)
      if (inE.size == 1) {
	val writerP = inE(0).from
	if (busProcs.contains(writerP.id)) true
	else false
      } else { 
	// shared data cannot be bus (XXX)
	val writerP = inE(0).from
	if (busProcs.contains(writerP.id)) true
	else false
      }
    }

    private def isVarSelection(v:Vertex, reader:Vertex) = {
      val e = graph.getEdge(v.id, reader.id)
      busElemEdge.contains(e.id)
    }

    // Reachability in terms of buses
    private def visitForward(v:Vertex):ArrayBuffer[Vertex] = {
      // In the BFS, need to distinguish between Var and Proc and
      // handle bus logics
      val pred = Graph.filteredSuccessor(graph,v,inactive)
      nodes(v.id) match {
        case Var(_) => {
          val next = ArrayBuffer[Vertex]()
          for (p <- pred) {
	    val opt = if (isVarSelection(v, p)) visitBusVarReader(v,p)
		      else {
			if (busReached.contains(v.id)) {
			  // v is bus and it does not pass through a selection
			  val reached = busReached(v.id)
			  Some(p, reached.elements)
			} else {
			  // v is not a bus signal
			  Some(p, Set(0))
			}
		      }

	    opt match {
	      case Some((_, el)) =>
		if (busProcs.contains(p.id)) {
		  if (visitVarWithBusReader(v, p, el)) next += p
		} else {
		  // Non-bus proc
		  if (!bfs.visited.contains(p)) next += p
		}
	      case None => {}
            }
	  }
          next
        }
        case Proc(_) => 
          if (busProcs.contains(v.id)) {
            visitBusProc(v, pred)
          } else {
            val next = ArrayBuffer[Vertex]()
            // Non-bus proc
            for (p <- pred) {
              if (!bfs.visited.contains(p)) next += p
            }
            next
          }
        
      }
    }

    val bfs = new BFS(visitForward)

    def run(start:Array[Int]) = {
      bfs.initialize(graph.getV(start))
      bfs.runAlways()
      (bfs.visited, busReached)
    }
  }

  def reachBus(src:Array[Int], inactive:Inactive, 
               busProcs:Map[Int,BusAction], 
	       busElemEdge:Map[Int, BusSelect]) = {
    val reach = new BusReachFor(g, busProcs,  
				busElemEdge, inactive)
    reach.run(src)
  }


  private def forwardReachable(src:Array[Int]) : Array[Int] = {
    val reach = new Reachable(g)
    reach.forward(src)
  }
  
  def forwardReachableProcs(src:Array[Int]): Array[Int] = {
    forwardReachable(src).filter( i=> isProc(nodes(i))).toArray
  }
  def forwardReachableVars(src:Array[Int]): Array[Int] = {
    forwardReachable(src).filter( i=> !isProc(nodes(i))).toArray
  }

  def forwardReachableProcs(src:Array[Int],
    inactive:Inactive) = {
    val reach  = new Reachable(g)
    val allReached = reach.forward(src, inactive)
    allReached.filter( i=> isProc(nodes(i))).toArray
  }

  def forwardReachableVars(src:Array[Int],
    inactive:Inactive) = {
    val reach  = new Reachable(g)
    val allReached = reach.forward(src, inactive)
    allReached.filter( i=> !isProc(nodes(i))).toArray
  }


  def backwardReachable(src:Array[Int]): Array[Int] = {
    val reach = new Reachable(g)
    reach.backward(src)
  }

  def backwardReachableProcs(src:Array[Int]) : Array[Int] = {
    backwardReachable(src).filter( i => isProc(nodes(i))).toArray
  }

  def backwardReachableVars(src:Array[Int]) : Array[Int] = {
    backwardReachable(src).filter( i => !isProc(nodes(i))).toArray
  }

  def backwardReachableProcs(src: Array[Int], 
                             inactive:Inactive) = {
    val reach = new Reachable(g)
    val allReached = reach.backward(src,inactive)
    allReached.filter( i => isProc(nodes(i))).toArray
  }

  def backwardReachableVars(src: Array[Int], 
                             inactive:Inactive) = {
    val reach = new Reachable(g)
    val allReached = reach.backward(src,inactive)
    allReached.filter( i => !isProc(nodes(i))).toArray
  }

  // Combine forward reach and backward reach 
  def reachableProcs(src: Array[Int],
                    sink: Array[Int],
                    inactive:Inactive) = {
    val reach = new Reachable(g)
    val fwd = reach.forward(src, inactive).toSet
    val bwd = reach.backward(sink, inactive).toSet
    (fwd & bwd).filter ( i => isProc(nodes(i))).toArray
  }

  // Combine forward reach and backward reach 
  def reachableVars(src: Array[Int],
                    sink: Array[Int],
                    inactive:Inactive) = {
    val reach = new Reachable(g)
    val fwd = reach.forward(src, inactive).toSet
    val bwd = reach.backward(sink, inactive).toSet
    (fwd & bwd).filter ( i => !isProc(nodes(i))).toArray
  }


  def allProcsButID(ids:Array[Int]) = {
    val idSet = ids.toSet
    
    val result = g.V.filter(v => isProc(nodes(v.id))
                          ).filter( v=> !idSet.contains(v.id) 
                                 ).toArray
    result.map(v => v.id)
  }

  /** Compute ids of the out edges for a given node filtered by the target nodes */
  def outEIdFilteredByTarget(which:Int, ids:Array[Int]) = {
    val out = g.outE(getV(which))
    val idSet = ids.toSet
    out.filter( e => idSet.contains (e.to.id)
             ).map(e => e.id
                 ).toArray
  }

  def outEIdsFiltered(src:Array[Int], ids:Array[Int]) = {
    val idSet = ids.toSet
    src.flatMap( v => 
      g.outE(getV(v)).filter( e => idSet.contains (e.to.id)) 
    ).map( e => e.id).toArray
  } 

  // Delegate methods to 
  def getE(from:Int, to:Int) = g.getEId(from, to)

  def getV(which:Int) = g.getV(which)
  def getV(which:Array[Int]) = g.getV(which)
  
  /** Predecessor */
  def pre(which:Int) = g.pre(getV(which)).map(_.id).toArray

  /** Successor */
  def succ(which:Int) = g.succ(getV(which)).map(_.id).toArray

  def outEId(which:Int) = g.outE(getV(which)).toArray


  // Reduce variable nodes
  def reduceVarNodes {
    val varNodes = g.V.filter(v => !isProc(nodes(v.id)))
    varNodes.foreach ( v => g.reduceVertex(v))
  }
  
}



 
