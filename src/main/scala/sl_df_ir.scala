package sl.ir

import my.se._
import scala.collection.immutable.Set
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
// A Var node corresponds to the output buffer of a nonvirtual block
case class Var(override val id:Int) extends DataflowNode 
{
}
// A Proc node correspond to the execution unit of a block
case class Proc(override val id:Int) extends DataflowNode
{
}
// An input node correspond to a input argument of the Proc, which 
// can be a Var or a slice of a Var, e.g., elements of a bus signal.
case class Input(override val id: Int) extends DataflowNode
{
}

// Identify each dataflow edge by the index at input port
sealed abstract class DataflowEdge 

// A signal edge correspond to edges that connects Var to Input 
case class Signal(val inportIdx:Int) extends DataflowEdge

// A read edge connects an input
// 1) a Proc node such that the Proc read through the input
// 2) another input such that some Proc read through the input
case object Read extends DataflowEdge

// A write edge connects a Proc to the signal it writes to
case object Write extends DataflowEdge

// A DSRead edge connects a DSM var with the Proc that reads
// from it.
case object DSRead extends DataflowEdge 

// Object to store the result of reachability analysis. 
class ReachSet(
  graph: DataflowGraph,
  val reachedVertices:Set[Int],
  val reachedSubBus:Map[Int, SubBus]) {

  def getProcs = 
    reachedVertices.filter{ i => graph.isProc(graph.nodes(i))} 

  def getProcsArray = getProcs.toArray

  def getVars = 
    reachedVertices.filter{ i => graph.isVar(graph.nodes(i)) }
  def getVarsArray = getVars.toArray

  def getInputs =
    reachedVertices.filter{ i => graph.isInput(graph.nodes(i))} 
  def getInputsArray = getInputs.toArray

  def getVarInputPairArray: Array[Int] = { 
    val pairs = for ( vid <- reachedVertices
		     if (graph.isVar(graph.nodes(vid)));
		     v = graph.g.getV(vid);
		     iV <- graph.g.succ(v) 
		     if (reachedVertices.contains(iV.id) &&
                         // The following is to filter out direct 
                         // connection due to DSM
                         graph.isInput(graph.nodes(iV.id)))
                   )
		yield {
		  (vid, iV.id)
		}
    // Workaround for MATLAB return argument
    val result = ArrayBuffer[Int]()
    for ((x,y) <- pairs) {
      result += x
      result += y
    }
    result.toArray
  }

  def getInputInputPairArray: Array[Int] = {
    val pairs = for (vid <- reachedVertices
		     if (graph.isInput(graph.nodes(vid)));
		     v = graph.g.getV(vid);
		     preV <- graph.g.pre(v)
		     if (reachedVertices.contains(preV.id) &&
		       graph.isInput(graph.nodes(preV.id)))
		   )
		  yield {
		  (preV.id, vid)
		  }
    // Workaround for MATLAB return argument
    val result = ArrayBuffer[Int]()
    for ((x,y) <- pairs) {
      result += x
      result += y
    }
    result.toArray
  }
  
  // Datastore nodes are Var nodes that feeds directly to
  // Proc nodes
  def getVarWithDirectRead: Array[Int] = {
    val vIDs = for (vid <- reachedVertices 
		    if (graph.isVar(graph.nodes(vid)));
		    v = graph.g.getV(vid);
		    succV <- graph.g.succ(v)
		    if (reachedVertices.contains(succV.id) &&
			graph.isProc(graph.nodes(succV.id)))
		  )
	       yield vid
    vIDs.toArray
  }
}

object DataflowUtil {
  // Determine if a variable is of bus type
  // Currently the heuristic is to check if the block that
  // writes to the signal is bus-capable. 
  def isVarBus(graph:Graph, 
	       v:Vertex, 
	       busProcs: Map[Int, BusAction]):Boolean = {
    val inEdges = graph.inE(v)
    if (inEdges.size == 1) {
      val writerP = inEdges(0).from
      if (busProcs.contains(writerP.id)) {
        busProcs(writerP.id) match {
          case BusCreate(_,_) => true
          case BusSelect(_, _) => false // unknown
          case BusPass(_,_,_) => true
	  case BusAssign(_,_,_) => true
        }
      } else false
    } else { 
      if (inEdges.size > 1) { 
        // shared data cannot be bus (XXX)
        val writerP = inEdges(0).from
          if (busProcs.contains(writerP.id)) {
            busProcs(writerP.id) match {
              case BusCreate(_,_) => true
              case BusSelect(_, _) => false // unknown
              case BusPass(_,_,_) => true
	      case BusAssign(_,_,_) => true
            }
          }
          else false
      } else false
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
  val edges = Map[Int, DataflowEdge]()

  def nNodes = g.V.size
  def nEdges = g.E.size

  def isProc(n:DataflowNode) = {
    n match {
      case Var(_) => false
      case Proc(_) => true 
      case Input(_) => false
    }
  }

  def isVar(n:DataflowNode) = {
    n match {
      case Var(_) => true
      case Proc(_) => false 
      case Input(_) => false
    }
  }

  def isInput(n:DataflowNode) = {
    n match {
      case Var(_) => false
      case Proc(_) => false 
      case Input(_) => true
    }
  }

  def newVarNode(name:String) = {
    val v = g.newVertex(name)
    val n = Var(v.id)
    nodes(v.id) = n
    n
  }

  def newVarNodes(names:Array[String]): Array[Int] = {
    names.map( n => {
      val v = newVarNode(n) 
      v.id
    })
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

  def newProcNodes(names:Array[String]) = {
    names.map( n => {
      val v = newProcNode(n) 
      v.id
    })
  }

  def newInputNode(name:String) = {
    val v = g.newVertex(name)
    val n = Input(v.id)
    nodes(v.id) = n
    n
  }

  def newInputNodes(names:Array[String]) = {
    names.map( n => {
      val v = newInputNode(n) 
      v.id
    })
  }

  // Note: special function to speed things up
  // due to MATLAB's current JIT limitation and 
  // MCOS performance.
  def createNodesFromArray( nTypes: Array[Int]) {
    nTypes.foreach{ 
      case 1 => { val _ = newVarNode("") }
      case 2 => { val _ = newProcNode("") }
      case 3 => { val _ = newInputNode("") }
    }
  }

  def addEdgesFromArray(src:Array[Int], dst:Array[Int], typeOrIdx:Array[Int]) {
    val edgeIds = addEdges(src, dst)
    (edgeIds, typeOrIdx).zipped.foreach( (eId, ty) => setEdgeType(eId, ty))
  }

  def createNodes(name:String, nIn: Int, nOut:Int) = {
    val p = newProcNode(name)
    val inputs = ArrayBuffer[DataflowNode]()
    for (i <- 1 to nIn) {
      val vI = newInputNode(name + "_i_" + i)
      addEdge(vI, p)
      inputs += vI
    }
    val outputs = ArrayBuffer[DataflowNode]()
    for (i <- 1 to nOut) {
      val vO = newVarNode(name + "_o_" + i)
      addEdge(p, vO)
      outputs += vO
    }
    (inputs, p, outputs)
  }


  def addEdge(src:Int, dst:Int) = { 
    if (src == dst) {
      throw new RuntimeException("No self-loop is allowed")
    } else {
      g.addEdge(src, dst)
    }
  }

  def setEdgeType(eid:Int, typeOrIdx:Int) {
    typeOrIdx match {
      // Maps an integer value to its edge type
      case -2 => {
	edges(eid) = DSRead 
      }
      case -1 => {
	edges(eid) = Read
      }
      case 0 => {
	edges(eid) = Write
      }
      case _ => {
	assert(typeOrIdx>0)
	edges(eid) = Signal(typeOrIdx)
      }
    }
  }

  def addEdges(src:Array[Int], dst:Array[Int]):Array[Int] = {
    // Create a map to speed up lookup.
    val m = g.V.map{ v => (v.id, v) }.toMap
    (src,dst).zipped.map {
      (s, d) => g.addEdge(m(s),m(d))
    }.map{ e => e.id }.toArray
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
      if (!v.sid.isEmpty) {
        val label = "[label=\"" + v.sid + "\"]"
        shape + label
      } else shape
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
		     inactive:Inactive = new Inactive(null,null),
		     dependence: Dependence = new Dependence(null, null)
		   ) {

    val busReached = Map[Int, SubBus]()

    // Test if a variable vertex is bus by testing its one and 
    // only writer.
    private def isVarBus(v:Vertex) = {
      DataflowUtil.isVarBus(graph, v, busProcs)
    }

    private def isEdgeSelection(v:Vertex, reader:Vertex) = {
      val e = graph.getEdge(v.id, reader.id)
      busElementEdge.contains(e.id)
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
	case BusPass(_,_,outV) => {
	  if (outV.contains(v.id)) {
	    val current = busReached(v.id)
	    updateResult(p, current)
	  } else !bfs.visited.contains(p)
	}
        case BusCreate(_,_) | BusAssign(_,_,_) => {
          if (busReached.contains(v.id)) {
            val current = busReached(v.id)
	    updateResult(p, current)
          } else {
            throw new RuntimeException("Error visiting var bus: " +
                                       "reachset of " + v.sid + " is not computed" +
                                       p.sid + "=>" + v.sid)
            true
          }
        }

	case BusSelect(b, varMap) => {
	  // Bus selector is selecting a sub-bus from a bus signal
          val vReached = if (busReached.contains(v.id)) busReached(v.id).elements
                         else Set[Int](0)
	  val i = varMap(v.id)
          val pReached = SubBus(b, b.fromDescendant(i, vReached))
	  updateResult(p, pReached) 
	}
      }
    }
    
    // Visit an input node which might be aggregated var nodes
    //  (var) --- bus selection --> <input>
    private def visitBusInput(in: Vertex,
			      varNode:Vertex): Boolean = {
      // print(" Visit bus input " + varNode.sid + " -> " + in.sid)
      // For virtual bus selector, get the sub-bus at the source
      def getSrcBusSel(srcEId:Int, dstSet:Set[Int]) = {
        val busSelection = busElementEdge(srcEId)
        val srcBus = busSelection.bus
        val i = busSelection.i
        (srcBus, SubBus(srcBus, srcBus.fromDescendant(i, dstSet)))
      }
      
      val edges  = graph.getEdges(varNode.id, in.id)
      var updated = false
      for (e <- edges) {
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
      
      busProcs(v.id) match {
        case BusPass(b, inV, _) => {
	  val next = ArrayBuffer[Vertex]()
	  for (p <- pred) {
	    if (inV.contains(p.id)) {
	      val current = busReached(v.id)
	      if (updateResult(p, current)) next +=p 
	    } else {
	      if (!bfs.visited.contains(p)) next +=p
	    }
	  }
	  next
	}
	case BusSelect(b, _) => {
	  val next = ArrayBuffer[Vertex]()
	  assert(pred.size == 1)
          for (p <- pred) {
            val current = busReached(v.id)
	    if (updateResult(p, current)) next += p
	  }
          next
	}
	case BusAssign(b, busInput, assignMap) => {
	  // Given R as reached, (R \ As)  is passed to input
	  assert(busReached.contains(v.id))
	  val next = ArrayBuffer[Vertex]()
	  val reached = busReached(v.id)
	  val assignSet = assignMap.map{ case(k,v) => v}.toSet
	  val passed = b.diff(reached.elements, assignSet)
	  if (!passed.isEmpty) {
	    // Nonempty set pass to input
	    val current = SubBus(b, passed)
	    val inputV = graph.getV(busInput)
	    if (updateResult(inputV, current)) next += inputV
	  }

	  for ((vid,idx) <- assignMap) {
	    // For each assigned element, compute (as \cap R)
	    // pass the result to the element. 
	    val inter = b.intersect(Set(idx), reached.elements) 
	    // lower the bus to the descendant type
	    if (!inter.isEmpty) {
	      val elem = b.get(idx)
	      val p = graph.getV(vid)
	      elem match {
		case sb:Bus => {
		  val inputSet = b.toDescendant(idx, inter)
		  val current = SubBus(sb, inputSet)
		  if (updateResult(p, current)) next += p
		}		  
		case _:AtomicElement => 
		  if (!bfs.visited.contains(p)) next += p
		
	      }
	    }
	    
	  }
	  next
	}
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
      val inE = graph.E.groupBy(_.to.id)
      val pred = Graph.customizedPredecessor(inE, graph,v,inactive, dependence)
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
	    if (busReached.contains(v.id)) {
	      // The input node contains bus signal
	      if (visitBusInput(v, p)) next+=p 
	    } else if (isEdgeSelection(p, v)) {
              if (visitBusInput(v, p)) next+=p 
            } else {
              // print(" Visit nonbus input ")
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
      // Return result in an object
      (bfs.visited, busReached)
    }
  }

  def backreachBus(src:Array[Int], 
		   inactive:Inactive,
		   busProcs:Map[Int,BusAction],
		   busElemEdge: Map[Int, VBusSelect],
		   dependence: Dependence = new Dependence(null, null)) = {
    val reach = new BusReachBack(g, busProcs, busElemEdge, 
				 inactive, dependence)
    val (visited, subBusMap) = reach.run(src)
    // Repeated parameter
    val immutableV = Set(visited.toSeq:_*).map(_.id)
    new ReachSet(this, immutableV, subBusMap)
  }


  def backreachNoBus(src:Array[Int], inactive:Inactive,
		   dependence: Dependence = new Dependence(null, null)) = {
    val busProcs = Map[Int, BusAction]()
    val busElemEdge = Map[Int, VBusSelect]()
    backreachBus(src, inactive, busProcs, busElemEdge, dependence)
  }

  // Forward reach with support for nonvirtual buses
  class BusReachFor(graph:Graph, 
		    busProcs:Map[Int,BusAction], 
		    busElemEdge: Map[Int, VBusSelect],
		    inactive:Inactive = new Inactive(null, null),
		    dependence: Dependence = new Dependence(null, null)) {

    private def isVarBus(v:Vertex) = DataflowUtil.isVarBus(graph, v, busProcs)

    private def isEdgeSelection(v:Vertex, reader:Vertex) = {
      val e = graph.getEdge(v.id, reader.id)
      busElemEdge.contains(e.id)
    }

     // Update the reach set and return the comparison result 
    def updateResult(v:Vertex, current:SubBus):Boolean = {
      val before = busReached.getOrElse(v.id, SubBusOp.empty(current))
      if (!SubBusOp.isSubset(current,before)) {
        busReached(v.id) = SubBusOp.union(current, before)
        true
      } else
        false
    }
   

    val busReached = Map[Int, SubBus]()


    // Visit a Input node with a bus-capable reader
    private def visitInputWithBusReader(v:Vertex,
                                        p:Vertex):Boolean = {
        
      busProcs(p.id) match {
	case BusPass(_, inV, _) => {
	  if (inV.contains(v.id)) {
	    val reached = busReached(v.id)
	    updateResult(p, reached)
	  } else {
	    // Non-bus-pass
	    !bfs.visited.contains(p)
	  }
	}
        case BusSelect(_, _) => {
          if (!busReached.contains(v.id)) {
            throw new RuntimeException("Error: reach set not found for " + v.sid)
          }
          val reached = busReached(v.id)
          updateResult(p, reached)
        }
        case BusCreate(b, srcEdges) => {
          // Need to 'lift' the reach set to the parent bus level.
          val reached = if (busReached.contains(v.id)) busReached(v.id).elements else Set[Int](0)
          val cs = srcEdges.map(i => if (v.id == i) reached else Set[Int]())
          val current = SubBus(b, b.collect(cs))
          updateResult(p, current)
         }
	case BusAssign(b, inputId, assignMap) => {
	  if (v.id == inputId) {
	    val assignSet = assignMap.map{ case(k,v) => v}.toSet
	    val reached = busReached(v.id)
	    val passed = b.diff(reached.elements, assignSet)
	    if (!passed.isEmpty) {
	      updateResult(p, SubBus(b, passed))
	    } else 
	      false
	  } else {
	    assert(assignMap.contains(v.id))
	    val idx = assignMap(v.id)
	    val inputSet = 
	      if (busReached.contains(v.id))
		busReached(v.id).elements
	      else Set(0)
	    val bSet = b.fromDescendant(idx, inputSet)
	    updateResult(p, SubBus(b,bSet))
	  }
	    
	}
      }
    }
    
    // Utility function, visit a variable that is feeding a proc via
    // virtual bus selector and compute the recach set at input.
    //
    //  (bus) -- ... (virtual selector) ... --> <reader input>
    // 
    private def visitBusVarWithEdgeSelect(v: Vertex, 
					  reader: Vertex) : 
    (Boolean, Option[SubBus]) = {
      // Compute the intersection of the reached set and the bus selection
      val reached = busReached(v.id) // reached subbus
      val b = reached.bus
      val e = graph.getEdge(v.id, reader.id)
      val bSel = busElemEdge(e.id)
      val sel = Set(bSel.i) // selected subbus
      val inter = b.intersect(sel, reached.elements)
      
      if (!inter.isEmpty){ 
        val selected = b.get(bSel.i) // Get the selected element type
        selected match {
	  case sb:Bus => {
            val sbset = b.toDescendant(bSel.i, inter) 
            val current = SubBus(sb, sbset)
            val before = busReached.getOrElse(reader.id, SubBus(sb, Set[Int]()))
            if (!SubBusOp.isSubset(current, before)) {
              (true, Some(current))
            } else 
              (false, None)
	  }
	  case _:AtomicElement => {
            if (!bfs.visited.contains(reader)) (true, None) 
            else (false, None)
	  }   
        }
      } else (false, None)
    }

    // Utility function, examins a variable that is the output of a selector
    // and returns whether need to visit v and 
    // the new reach set if it needs update.
    private def varSelection(b:Bus, i:Int, proc: Vertex, v: Vertex) : 
    (Boolean, Option[SubBus]) = {
      val select = b.fromDescendant(i, Set(0))
      val reached = busReached(proc.id)
      val inter = b.intersect(select, reached.elements)
      
      if (!inter.isEmpty){ 
        b.get(i) match {
	  case sb:Bus => {
            // Selection is also a bus
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
      def simplePass = {
          val next = ArrayBuffer[Vertex]()
          for (p <- vars) {
            if (updateResult(p, busReached(v.id))) {
              next += p
            }
          }
          next
      }
      busProcs(v.id) match {
	case BusSelect(b, varMap) => {
	  val next = ArrayBuffer[Vertex]()
	  for ( p <- vars ) {
            // Depends on whether the i-th element is bus
	    val (isNew, optV) = varSelection(b, varMap(v.id), v, p) 
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
        case BusPass(_,_,outV) => {
	  val next = ArrayBuffer[Vertex]()
          for (p <- vars) {
	    if (outV.contains(p.id)) {
              if (updateResult(p, busReached(v.id))) next += p
            } else {
	      if (!bfs.visited.contains(p)) next += p
	    }
          }
          next
	} 
	case  BusCreate(_,_) | BusAssign(_,_,_) => simplePass
      }
    }


    // Reachability in terms of buses
    private def visitForward(v:Vertex):ArrayBuffer[Vertex] = {
      // In the BFS, need to distinguish between Var and Proc and
      // handle bus logics
      val pred = Graph.customizedSuccessor(graph,v,inactive, dependence)
      nodes(v.id) match {
        case Var(_) => {
          val next = ArrayBuffer[Vertex]()
          for (p <- pred) {
	    // Compute the reach set at input of the proc.
	    // Note that if the proc is bus creator it might need
	    // to be 'lifted' to the created bus.
	    val (reached, opt) = if (isEdgeSelection(v, p)) { 
              assert(isVarBus(v)) // The var must be bus type
              assert(busReached.contains(v.id))
              
	      visitBusVarWithEdgeSelect(v,p)
	    } else {
	      if (busReached.contains(v.id)) {
		// v is bus and it does not pass through a selection
		val reached = busReached(v.id)
		(updateResult(p, reached), Some(reached))
	      } else {
                if (!bfs.visited.contains(p)) (true, None)
                else (false, None)
	      }
	    }
	    if (reached) { 
	      opt match {
	        case Some(reached:SubBus) => {
                  busReached(p.id) = reached
                }
	        case None => {}
              }
              next += p
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
        case Input(_) => 
          {
            assert(pred.size == 1) 
            val p = pred(0)
            if (busProcs.contains(p.id)) {
              if (visitInputWithBusReader(v,p)) ArrayBuffer(p)
              else ArrayBuffer[Vertex]()
            } else {
              // Non-bus proc
              if (!bfs.visited.contains(p)) ArrayBuffer(p) 
              else ArrayBuffer[Vertex]()
            }
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
	       busElemEdge:Map[Int, VBusSelect]) = {
    val reach = new BusReachFor(g, busProcs,  
				busElemEdge, 
				inactive)
    val (visited, busReach) = reach.run(src)
    val immutableV = Set(visited.toSeq:_*).map(_.id)
    new ReachSet(this, immutableV, busReach)
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
    val busProcs = Map[Int, BusAction]()
    val busElemEdge = Map[Int, VBusSelect]()
    val reachSet = reachBus(src, inactive, busProcs, busElemEdge)
    reachSet.getProcs.toArray
  }

  def forwardReachableVars(src:Array[Int],
    inactive:Inactive) = {
    val busProcs = Map[Int, BusAction]()
    val busElemEdge = Map[Int, VBusSelect]()
    val reachSet = reachBus(src, inactive, busProcs, busElemEdge)
    reachSet.getVars.toArray
  }


  def backwardReachable(src:Array[Int]): Array[Int] = {
    val reach = new Reachable(g)
    reach.backward(src)
  }

  def backwardReachableProcs(src:Array[Int]) : Array[Int] = {
    backwardReachable(src).filter( i => isProc(nodes(i))).toArray
  }

  def backwardReachableVars(src:Array[Int]) : Array[Int] = {
    backwardReachable(src).filter( i => isVar(nodes(i))).toArray
  }

  def backwardReachableProcs(src: Array[Int], 
                             inactive:Inactive) = {
//    val reach = new Reachable(g)
//    val allReached = reach.backward(src,inactive)
    val busProcs = Map[Int,BusAction]()
    val busElemEdge = Map[Int, VBusSelect]()
    val reachSet = backreachBus(src, inactive, 
				busProcs, busElemEdge)
    reachSet.getProcs.toArray
  }

  def backwardReachableVars(src: Array[Int], 
                             inactive:Inactive) = {
//    val reach = new Reachable(g)
//    val allReached = reach.backward(src,inactive)
    val busProcs = Map[Int,BusAction]()
    val busElemEdge = Map[Int, VBusSelect]()
    val reachSet = backreachBus(src, inactive, 
				busProcs, busElemEdge)
    reachSet.getVars.toArray
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
    (fwd & bwd).filter ( i => isVar(nodes(i))).toArray
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

  def intermediateNodeFilteredByTarget(src:Int, proc:Array[Int]) = {
    val procSet = proc.toSet

    val inputs = g.outE(getV(src)).map(e => e.to.id)
    inputs.filter(v => procSet.exists( d => 
      g.hasE(v, d))).toArray

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
  def pre(which:Array[Int]) = which.flatMap(
    (i:Int) => g.pre(getV(i))).map(_.id).toArray

  /** Successor */
  def succ(which:Int) = g.succ(getV(which)).map(_.id).toArray

  def outEId(which:Int) = g.outE(getV(which)).map(_.id).toArray

  def allIds = g.V.map(_.id).toArray

  // Reduce non-proc nodes
  def reduceNonProcNodes {
    val varNodes = g.V.filter(v => !isProc(nodes(v.id)))
    varNodes.foreach ( v => g.reduceVertex(v))
  }
  
}
