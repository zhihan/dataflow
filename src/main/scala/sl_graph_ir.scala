package sl.ir

import my.se._
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

trait HasId 
{
  val id:Int  // A simple trait specifying it has an Id field.
}

abstract class Port extends AnyRef with HasId

case class Inport(override val id: Int) extends Port
{
}
case class Outport(override val id:Int) extends Port
{
}

/** Virtual block graph captures the relationship between
 * virtual blocks. It is used to compute the
 * virtual blocks in  a signal pathway*/

class VirtualBlockGraph() {

  /** Underlying dag */
  val g = new Graph()
  /** Map: id -> Port data structure*/
  val nodes = Map[Int, Port]() 

  def newOutport(name:String) = {
    val p = g.newVertex(name)
    val n = Outport(p.id)
    nodes(p.id) = n
    n
  }

  def newInport(name:String) = {
    val p = g.newVertex(name)
    val n = Inport(p.id)
    nodes(p.id) = n
    n
  }

  def addEdge(src:Int, dst:Int): Edge= { 
    if (src == dst) {
      throw new RuntimeException("No self-loop is allowed")
    } else {
      g.addEdge(src, dst)
    }
  }

  def addEdge(src:Port, dst:Port):Edge = addEdge(src.id, dst.id)

  def toDotString(): String = {
    
    def vLabel(v:Vertex):String = {
      val n = nodes(v.id)
      val ioLabel = 
        n match {
          case Inport(_) => "i"
          case Outport(_) => "o"
      }
      "[label=\"" + v.sid + ":" + ioLabel + "\"]"
    }

    writeGraphviz(g, vLabel)
  }
  
  private def isOutport(n: Int) = {
    nodes(n) match {
      case Outport(_) => true
      case Inport(_) =>false
    }
  }

  def forwardReachablePairs(src:Int): Array[Int] = {
    val reach = new Reachable(g)
    val reachSet = reach.forward(src).toSet
    val reachOutports = reachSet.filter(isOutport)

    val outList = reachOutports.toList
    val ioList = new ListBuffer[Int]()
    outList.foreach( oId => {
      val oV = g.getV(oId)
      val eList = g.E.filter(e => ((e.from == oV) &&
                                  (reachSet.contains(e.to.id))))
      val iVList = eList.map(_.to)
      iVList.foreach( iV => {
        ioList.append(oV.id)
        ioList.append(iV.id)
      })
    })
                  
    ioList.toArray
  }

  

}
