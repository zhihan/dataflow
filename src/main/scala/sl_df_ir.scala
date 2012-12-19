package my.sl.ir

import my.se._
import scala.collection.mutable.Map

abstract class DataflowNode(i:Int) 
{
  val id = i
}
case class Var(_i:Int) extends DataflowNode(_i)
{
}
case class Proc(_i:Int) extends DataflowNode(_i)
{
}


class DataflowGraph() {
  val g = new Graph()  

  // Two-way maps between graphical vertices and semantic 
  // nodes

  val nodes = Map[Int, DataflowNode]()

  def newVarNode(name:String) = {
    val v = g.newVertex(name)
    val n = Var(v.id)
    nodes(v.id) = n
    n
  }

  def newProcNode(name:String) = {
    val v = g.newVertex(name)
    val n = Proc(v.id)
    nodes(v.id) = n
    n
  }

  def addEdge(src:Int, dst:Int) {
    g.addEdge(src, dst)
  }

  def addEdge(src:DataflowNode, dst:DataflowNode) {
    
    g.addEdge(src.id, dst.id)
  }
  
  
  def toDotString(): String = {
    
    def vLabel(v:Vertex):String = {
      val n = nodes(v.id)
      val shape = 
        n match {
          case Var(_) => "[shape='box']"
          case Proc(_) => "[shape='ellipse']"
      }
      val label = "[label='" + v.sid + "']"
      shape + label
    }

    writeGraphviz(g, vLabel)
  }
}


