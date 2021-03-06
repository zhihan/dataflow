/** Intermediate representation for Simulink graphic objects*/
package sl.ir.graph

import me.zhihan.se._
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer
import me.zhihan.utility._

/** There are two kinds of ports, inport accepts input signals
 * outport generate output signals. */
abstract sealed class Port extends HasId

/** Represent an input port of a block  */
case class Inport(override val id: Int) extends Port
{ }

/**  Represent an output port of a block */
case class Outport(override val id:Int) extends Port
{ }


/** Virtual port graph captures the relationship between
 * ports of virtual blocks. It is used to compute the
 * virtual blocks in  a signal pathway. */

class VirtualPortGraph() {

  /** Underlying dag */
  val g = new Graph()
  /** Map: id -> Port data structure*/
  val nodes = Map[Int, Port]() 

  def newOutport(name:String) = {
    val p = g.newVertex(name)
    val n = Outport(p.id)
    nodes(p.id) = n
    p.id
  }

  def newInport(name:String) = {
    val p = g.newVertex(name)
    val n = Inport(p.id)
    nodes(p.id) = n
    p.id
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
  
  def isOutport(n: Int) = {
    nodes(n) match {
      case Outport(_) => true
      case Inport(_) =>false
    }
  }

  /**
    Compute the reachable I/O port from a list of output port Ids. 

    Given a list of outport and a set of reachable ports, compute all
   outport-inport pairs and return them in an array with alternating
   outport and inport ids.
   [ out1 in1 out2 in2 ... out_n in_n] 
   
  */
  private def getIOPorts(outList:List[Int], reachSet:Set[Int]):Array[Int] = {
    val ioList = new ListBuffer[Int]()
    outList.foreach( oId => {
      // Get the vertex from its id
      val oV = g.getV(oId) 
      // Get the list of out-going edges that reaches vertices in the reach set 
      val eList = g.E.filter(e => ((e.from == oV) &&
                                  (reachSet.contains(e.to.id))))
      // Get the destination of the out-going edges
      val iVList = eList.map(_.to)
      iVList.foreach( iV => {
        ioList.append(oV.id)
        ioList.append(iV.id)
      })
    })
                  
    ioList.toArray    
  }

  /** Compute reachable ports and return them in input-output pairs
   */

  def forwardReachablePairs(src:Int): Array[Int] = {
    val reach = new Reachable(g)
    val reachSet = reach.forward(src).toSet
    val reachOutports = reachSet.filter(isOutport)

    val outList = reachOutports.toList
    getIOPorts(outList, reachSet)
  }
  
  /**
    Compute reachable ports form a set of sources (outports) and a 
  set of detinations (inports). The algorithm computes the intersection
  of the forward reach set from the sources and te backward reach set
  from the destinations.
  */

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
{
  def json:String = "{\"block\": $id%d }"
}

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

  def addEdges(src:Array[Int], dst:Array[Int]) {
    if (src != null) {
      src.zip(dst).foreach { case (s,d) =>
        addEdge(s,d) }
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

  def fwdUnreachable(fSrc:Array[Int]) :Array[Int] = {
    val reach = new Reachable(g)
    val fwd = if (fSrc!= null) { 
      reach.forward(fSrc).toSet
    } else {
      Set[Int]()
    }
    val result = g.V.filter ( v => !fwd.contains(v.id))
    result.map(_.id).toArray
  }
  def bwdUnreachable(bSrc:Array[Int]):Array[Int] = {
    val reach = new Reachable(g)
    val bwd = if (bSrc!= null) { 
      reach.backward(bSrc).toSet
    } else {
      Set[Int]()
    }
    val result = g.V.filter ( v => !bwd.contains(v.id))
    result.map(_.id).toArray
  }

  private def allContainedIn(x:ArrayBuffer[Int], filt:Set[Int]) = 
    x.forall(filt.contains(_))

  def allSrcContainedIn(candidates:Array[Int], filt:Array[Int]) = {
    if (candidates != null) {
      if (filt != null) { 
        val filtSet = filt.toSet
        candidates.filter( v => allContainedIn(g.pre(g.getV(v)).map(_.id), filtSet))
      } else {
        Array[Int]()
      }
    } else Array[Int]()
  }

  def allDstContainedIn(candidates:Array[Int], filt:Array[Int]) = {
    if (candidates != null) {
      if (filt != null) { 
        val filtSet = filt.toSet
        candidates.filter( v => allContainedIn(g.succ(g.getV(v)).map(_.id), filtSet))
      } else Array[Int]()
    } else Array[Int]()
  }
  
}

/** NamedTreeNode is similar to string trie, where each node has 
  a string label. Each non-root node is idenfitied by the unique
  string consisting the labels on the path of that node. */
class NameTreeNode(val id: Int, 
  val children:ArrayBuffer[NameTreeNode], 
  val name:String) {
  var relPath = name
  // Recursive 
  private def dotString(): List[String] = {
    val l = id.toString() + 
    "[label=\"" + name + ":" + relPath + "\"]"

    val conn = (for (c <- children) yield
      id.toString() + " -- " + c.id.toString).toList 
    
    val rest = (for (c <- children) yield c.dotString()).toList
    l :: (conn ++ rest.flatten)
  }
  
  def toDotString() = {
    val l = dotString()
    " graph G {\n" + l.mkString("\n") + " } "
  }
  def addChild(t: NameTreeNode) {
    children.append(t)
  }

  def reduceChild(i:Int) {
    val name = children(i).name
    val cc = children(i).children
    children.remove(i)
    cc.foreach{ v => 
      v.relPath = name + "/" + v.relPath
      addChild(v) 
    }
  }
  
}

class NameTreeNodeFactory {
  val gensym = new Gensym()

  def make(n:String) = new NameTreeNode(gensym(), 
    ArrayBuffer[NameTreeNode](), n)
}

class NameTreeUtil {
  def computeParentMap(root: NameTreeNode) = {
    def addMap(x: NameTreeNode, 
      m: Map[Int, NameTreeNode]) {
      x.children.foreach{ c =>
        m += (c.id -> x) 
      }
      x.children.foreach( addMap(_, m) )
    }
    val m = Map[Int, NameTreeNode]()
    addMap(root, m)
    m
  }

  def fullpath(root: NameTreeNode,
    target:NameTreeNode) = {
    val parentMap = computeParentMap(root)
    def getPath(cur: NameTreeNode, rel:String): String = {
      if (cur == root) 
        rel
      else if (parentMap.contains(cur.id)) 
        getPath(parentMap(cur.id),
          "/" + cur.name + rel) 
      else throw new RuntimeException("Cannot find path")
    }
    getPath(target, "")
  }
}



