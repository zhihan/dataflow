/** Intermediate representation for Simulink graphic objects*/
package sl.ir

import my.se._
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

trait HasId 
{
  val id:Int  // A simple trait specifying it has an Id field.
}

/** There are two kinds of ports, inport accepts input signals
 * outport generate output signals. */
abstract sealed class Port extends AnyRef with HasId

// Represent an input port of a block 
case class Inport(id: Int) extends Port
{ }

// Represent an output port of a block
case class Outport(id:Int) extends Port
{ }


/** Virtual port graph captures the relationship between
 * ports of virtual blocks. It is used to compute the
 * virtual blocks in  a signal pathway*/

class VirtualPortGraph() {

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
      // Check src and dst type
      assert(nodes(src) != nodes(dst))
      // Graphical graph is not a multi-graph
      if (!g.hasE(src,dst)) 
        g.addEdge(src, dst)
      else 
        g.getEdge(src, dst)
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

  /** Compute reachable ports and return them in input-output pairs
   *
   *  The return array is alternating list of out and in ports.
   * [out1 in1 out2 in2 ... outn inn]
   *  where the connections are out1 -> in1, out2 -> in2 ,.... outn -> inn.
   *
   */

  private def getIOPorts(outList:List[Int], reachSet:Set[Int]):Array[Int] = {
    val ioList = new ListBuffer[Int]()
    outList.foreach( oId => {
      val oV = g.getV(oId) // outV
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

  def forwardReachablePairs(src:Int): Array[Int] = {
    val reach = new Reachable(g)
    val reachSet = reach.forward(src).toSet
    val reachOutports = reachSet.filter(isOutport)

    val outList = reachOutports.toList
    getIOPorts(outList, reachSet)
  }

  def reachablePairs(src:Array[Int], dst:Array[Int]) = {
    val reach = new Reachable(g)
    val fwd = reach.forward(src).toSet
    val bwd = reach.backward(dst).toSet
    val both = fwd & bwd
    val reachOutports = both.filter(isOutport)

    getIOPorts(reachOutports.toList, both)

  }

}

/** Virtual block graph is a different edit-time graph that captures
 * the inter-connection of virtual blocks including Subsystem inports
 * and outports. */

case class Block(id:Int) extends AnyRef with HasId

class VirtualBlockGraph() {
  val g = new Graph()
  val nodes = Map[Int, Block]()

  def newBlock(name:String) = {
    val b = g.newVertex(name)
    val n = Block(b.id)
    nodes(b.id) = n
    n
  }

  def newBlocks(names:Array[String]) = {
    val result = ListBuffer[Int]()
    names.foreach{ name => 
      result.append(newBlock(name).id)}
    result.toArray
  }
  
  def addEdge(src:Int, dst:Int) = {
    if (src == dst) {
      throw new RuntimeException("No self-loop is allowed")
    } else {
      g.addEdge(src,dst)
    }
  }

  def toDotString(): String = {
    def vLabel(v:Vertex) = "[label=\"" + v.sid + "\"]"
    writeGraphviz(g, vLabel)
  }

  // For a given set of forward reachable nodes and backward
  // reachable nodes, compute the set of unreachable nodes. 
  def unreachable(fSrc:Array[Int], bSrc:Array[Int]): Array[Int] = {
    val reach = new Reachable(g)
    
    val fwd = if (fSrc!= null) { 
      reach.forward(fSrc).toSet
    } else {
      Set[Int]()
    }
    val bwd = if (bSrc != null) {
      reach.backward(bSrc).toSet
    } else {
      Set[Int]()
    }
    val both = fwd.intersect(bwd).toArray
    val result = g.V.filter(v => !both.contains(v.id))
    result.map(_.id).toArray
    
  }
}
