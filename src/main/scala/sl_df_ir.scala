package sl.ir

import my.se._
import scala.collection.mutable.Map

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

    writeGraphviz(g, vLabel)
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

  // Delegate methods to 
  def getE(from:Int, to:Int) = g.getE(from, to)

  def getV(which:Int) = g.getV(which)
  def getV(which:Array[Int]) = g.getV(which)
  
  /** Predecessor */
  def pre(which:Int) = g.pre(getV(which)).map(_.id).toArray

  /** Successor */
  def succ(which:Int) = g.succ(getV(which)).map(_.id).toArray

  def outEId(which:Int) = g.outE(getV(which)).toArray



}


 
