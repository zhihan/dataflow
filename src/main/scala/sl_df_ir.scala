package sl.ir

import my.se._
import scala.collection.mutable.Map
import scala.collection.mutable.ArrayBuffer

/**
 * A dataflow node can be either a variable node or a proc node, or special
 * input node.
 *
 * - Variable node models variables used for signals, state and data stores.
 * 
 * - Proc node models block methods that uses or writes to the variables.
 *
 * - Input node is associated with block input, it can be combined of multiple
 *   varialbles. Thererfore the need. It is particularly useful in defining the
 *   behavior of bus creators.
 *
 */

import sl.ir._

sealed abstract class DataflowNode extends AnyRef with HasId
case class Var(override val id:Int) extends DataflowNode 
{
}
case class Proc(override val id:Int) extends DataflowNode
{
}
case class Input(override val id: Int) extends DataflowNode
{
}



object DataflowUtil {
  def isVarBus(graph:Graph, 
	       v:Vertex, 
	       busProcs: Map[Int, BusAction]):Boolean = {
    val inEdges = graph.outE(v)
    if (inEdges.size == 1) {
      val writerP = inEdges(0).to
      if (busProcs.contains(writerP.id)) true
      else false
    } else { 
      // shared data cannot be bus (XXX)
      val writerP = inEdges(0).to
      if (busProcs.contains(writerP.id)) true
      else false
    }
  }
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
      case Input(_) => false
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

  def newInputNode(name:String) = {
    val v = g.newVertex(name)
    val n = Input(v.id)
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
	  case Input(_) => "[shape=\"diamond\"]"
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
		     busElementEdge: Map[Int, VBusSelect],
		     busElementVar: Map[Int, Int],
		     inactive: Inactive) {

    val busReached = Map[Int, SubBus]()

    // Test if a variable vertex is bus by testing its one and 
    // only writer.
    private def isVarBus(v:Vertex) = {
      DataflowUtil.isVarBus(graph, v, busProcs)
    }

    // Update the reach set and return the comparison result 
    def updateResult(v:Vertex, current:SubBus) = {
      val before = busReached.getOrElse(v.id, SubBusOp.empty(current))
      if (!SubBusOp.isSubset(current,before)) {
        busReached(v.id) = SubBusOp.union(current, before)
        true
      } else
        false
    }

    // Visit a Var node with a bus-capable writer
    // The bus capable writer is either a pass or 
    // a creator of bus; in some cases, the bus capable
    // writer might be a BusSelector too.
    private def visitVarBus(v:Vertex,
                            p:Vertex):Boolean = {
      busProcs(p.id) match {
        case BusCreate(_,_) | BusPass(_) => {
          if (busReached.contains(v.id)) {
            val current = busReached(v.id)
	    updateResult(p, current)
          } else {
            throw new RuntimeException("Error here" + p.sid + "=>" + v.sid)
            true
          }
        }

	case BusSelect(b) => {
	  // Bus selector is selecting a sub-bus from a bus signal
          val vReached = busReached.getOrElse(v.id, SubBus(b,Set[Int](0)))
	  val i = busElementVar(v.id)
          val pReached = SubBus(b, b.fromDescendant(i, vReached.elements))
	  updateResult(p, pReached) 
	}
      }
    }
    
    // Visit an input node which might be aggregated var nodes
    //  (var) --- bus selection --> <input>
    private def visitBusInput(in: Vertex,
			      varNode:Vertex): Boolean = {

      // For virtual bus selector, get the sub-bus at the source
      def getSrcBusSel(srcEId:Int, dstSet:Set[Int]) = {
        val busSelection = busElementEdge(srcEId)
        val srcBus = busSelection.bus
        val i = busSelection.i
        (srcBus, SubBus(srcBus, srcBus.fromDescendant(i, dstSet)))
      }
      
      val processedE = Set[Int]() 
      val edges  = graph.getEdges(varNode.id, in.id)
      var updated = false
      for (e <- edges) {
	if (!processedE.contains(e.id)) {
	  // Prevent repeating
	  if (busElementEdge.contains(e.id)) {
            // There is a selection between busVar and in
            // Reached elements at <in>
            val vReached = 
	      if (busReached.contains(in.id)) busReached(in.id).elements else Set(0)
            // Compute the corresponding reached at (BusVar)
            val (_,current) = getSrcBusSel(e.id, vReached)
            
            if (updateResult(varNode, current)) updated = true
	  } else {
	    // No selection, pass the reached to input
	    if (updateResult(varNode, busReached(in.id))) updated = true
	  }
	}
      }
      updated
    }

    // Visiting a regular proc which reads a sub-bus from a var.
    //   <input> --> [proc]
    // The block is not a bus creator or bus pass.
    private def visitBusReader(proc:Vertex,
			       in:Vertex): Boolean = {

      if (graph.inE(proc).size > 1) {
	throw new RuntimeException("Expecting single input bus reader")
      }
      assert(busReached.contains(proc.id))

      updateResult(in, busReached(proc.id))
    }

    // Visit a bus-capable proc
    // 
    //  (in) ---> | proc |
    // 
    private def visitBusProc(v:Vertex,
                             pred:ArrayBuffer[Vertex]): ArrayBuffer[Vertex] = {
      
      // For bus pass or bus select, pass the reached part to
      // the variable at input. Handle the case where the incoming
      // edge is virtual bus selector.
      def predBusPass(b:Bus) = {
	val next = ArrayBuffer[Vertex]()
	assert(pred.size == 1)
        for (p <- pred) {
          val current = busReached(v.id)
	  if (updateResult(p, current)) next += p
	}
        next
      }
      
      busProcs(v.id) match {
        case BusPass(b) => predBusPass(b)
	case BusSelect(b) => predBusPass(b)
        case BusCreate(b,srcInputs) => {
          val next = ArrayBuffer[Vertex]()
          val reached = busReached(v.id).distribute
	  var srcVar = srcInputs
          for ((c,r) <- reached) {
            var srcIn = srcVar.head
            // <p>--->[v]
            val p = graph.getV(srcIn)
            c match {
              case _:AtomicElement => 
		// Create bus from atomic element
                if (!r.isEmpty) {
                  if (!bfs.visited.contains(p)) next += p
                }
              case bc:Bus => {
                val current = SubBus(bc, r)
                val before = busReached.getOrElse(p.id, SubBus(bc, Set[Int]()))
                if (! SubBusOp.isSubset(current, before)) {
	          busReached(p.id) = SubBusOp.union(current, before)
                  next += p
                }
              }
            }
            srcVar = srcVar.tail
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
              if (visitVarBus(v, p)) next += p
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
	
	case Input(_) => {
	  val next = ArrayBuffer[Vertex]()
          for (p <- pred) {
	    if (isVarBus(p)) {
	      // Variable is bus signal
	      if (visitBusInput(v, p)) next+=p 
	    } else {
	      // Variable is atomic signal
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
		   busElemEdge: Map[Int, VBusSelect],
		   busElemVars: Map[Int, Int]) = {
    val reach = new BusReachBack(g, busProcs, busElemEdge, 
				 busElemVars, inactive)
    reach.run(src)
  }

  // Forward reach with support for nonvirtual buses
  class BusReachFor(graph:Graph, 
		    busProcs:Map[Int,BusAction], 
		    busElemEdge: Map[Int, VBusSelect],
		    busElemVar: Map[Int, Int],
		    inactive:Inactive) {

    private def isVarBus(v:Vertex) = DataflowUtil.isVarBus(graph, v, busProcs)

    private def isEdgeSelection(v:Vertex, reader:Vertex) = {
      val e = graph.getEdge(v.id, reader.id)
      busElemEdge.contains(e.id)
    }

     // Update the reach set and return the comparison result 
    def updateResult(v:Vertex, current:SubBus) = {
      val before = busReached.getOrElse(v.id, SubBusOp.empty(current))
      if (!SubBusOp.isSubset(current,before)) {
        busReached(v.id) = SubBusOp.union(current, before)
        true
      } else
        false
    }
   

    val busReached = Map[Int, SubBus]()


    // Visit a Var node with a bus-capable reader
    private def visitVarWithBusReader(v:Vertex,
                                      p:Vertex,
				      reached: Set[Int]):Boolean = {
      // Reached set is already computed to take into consideration
      // of the possible edge selections. 
      def busPassSet(b:Bus, r:Set[Int]) = {
	updateResult(p, SubBus(b, r))
      }
 
      busProcs(p.id) match {
        case BusPass(b) => busPassSet(b, reached) 
	case BusSelect(b) => busPassSet(b, reached)
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
    
    // Utility function, visit a variable that is feeding a block via
    // virtual bus selector.
    //
    //  (bus) -- ... (virtual selector) ... --> [reader]
    // 
    private def visitBusVarWithEdgeSelect(v: Vertex, 
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

    // Utility function, visit a variable that is the output of a selector
    // and returns whether need to visit v and 
    // the new reach set if it needs update.
    private def varSelection(b:Bus, proc: Vertex, v: Vertex) : 
    (Boolean, Option[SubBus]) = {
      val i = busElemVar(v.id)
      val select = b.fromDescendant(i, Set(0))
      val reached = busReached(proc.id)
      val inter = b.intersect(select, reached.elements)
      
      if (!inter.isEmpty){ 
        b.get(i) match {
	  case sb:Bus => {
            val current = SubBus(sb, b.toDescendant(i, inter))
            val before = busReached.getOrElse(v.id, SubBus(sb, Set[Int]()))
            if (!SubBusOp.isSubset(current, before)) {
              (true, Some(current))
            } else 
              (false, None)
	  }
	  case _:AtomicElement => {
            if (!bfs.visited.contains(v)) (true, None) 
            else (false, None)
	  }   
        }
      } else  // Intersection of selection and reached is empty
	(false, None)
    }

    // Visit a bus-capable proc
    // [v] => (vars)
    private def visitBusProc(v:Vertex,
                             vars:ArrayBuffer[Vertex]): ArrayBuffer[Vertex] = {
      
      busProcs(v.id) match {
	case BusSelect(b) => {
	  val next = ArrayBuffer[Vertex]()
	  for ( p <- vars ) {
            // Depends on whether the i-th element is bus
	    val (isNew, optV) = varSelection(b, v, p) 
	    if (isNew) {
	      optV match {
		case Some(reached) => busReached(p.id) = reached
		case None => {}
		}
	      next += p
	    }
	  }
	  next
	}
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


    // Reachability in terms of buses
    private def visitForward(v:Vertex):ArrayBuffer[Vertex] = {
      // In the BFS, need to distinguish between Var and Proc and
      // handle bus logics
      val pred = Graph.filteredSuccessor(graph,v,inactive)
      nodes(v.id) match {
        case Var(_) => {
          val next = ArrayBuffer[Vertex]()
          for (p <- pred) {
	    // Compute the reach set at incoming of the proc.
	    // Note that if the proc is bus creator it might need
	    // to be 'lifted' to the created bus.
	    val opt = if (isEdgeSelection(v, p)) { 
	      visitBusVarWithEdgeSelect(v,p)
	    } else {
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
	       busElemEdge:Map[Int, VBusSelect],
	       busElemVar:Map[Int, Int]) = {
    val reach = new BusReachFor(g, busProcs,  
				busElemEdge, busElemVar, 
				inactive)
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



 
