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

  class BusReachBack(graph:Graph, busProcs:Map[Int,BusAction],  inactive:Inactive) {

    val busReached = Map[Int, SubBus]()

    // Reachability in terms of buses
    private def visitBackward(v:Vertex):ArrayBuffer[Vertex] = {
      // In the BFS, need to distinguish between Var and Proc and
      // handle bus logics
      val pred = Graph.filteredPredecessor(graph,v,inactive)
      val next = ArrayBuffer[Vertex]()
      nodes(v.id) match {
        case Var(_) => 
          for (p <- pred) {
              if (busProcs.contains(p.id)) {
                busProcs(p.id) match {
                  case BusCreate(_) | BusPass(_) => {
                    val current = busReached(v.id)
                    val before = busReached.getOrElse(p.id, SubBusOp.empty(current))
                    if (! SubBusOp.isSubset(current, before)) {
	              busReached(p.id) = SubBusOp.union(current, before)
                      next += p
                    }
                  }
                  case BusSelect(b, i) => {
                    if (!bfs.visited.contains(p)) {
                      busReached(p.id) = SubBus(b, Set(i))
                      next += p
                    }
                  }
                }
              } else {
                // Non-bus proc
                if (!bfs.visited.contains(p)) next += p
              }
            }
        case Proc(_) => 
          if (busProcs.contains(v.id)) {
            busProcs(v.id) match {
              case BusSelect(_,_) | BusPass(_) => 
                for (p <- pred) {
                  val current = busReached(v.id)
                  val before = busReached.getOrElse(p.id, SubBusOp.empty(current))
                  if (! SubBusOp.isSubset(current, before)) {
	            busReached(p.id) = SubBusOp.union(current, before)
                    next += p
                  }
                }
              case BusCreate(b) => {
                val children = b.children
                
              }
                
            }
          } else {
            for (p <- pred) {
              if (!bfs.visited.contains(p)) next += p
            }
          }
        
      }
      next
    }

    val bfs = new BFS(visitBackward)
    
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
  def getE(from:Int, to:Int) = g.getE(from, to)

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



 
