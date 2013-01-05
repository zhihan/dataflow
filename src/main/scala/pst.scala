// Program structure tree 
// Construct a PST from a program IR
package my.ir.pst

import my.ir._
import my.se._
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

abstract class Region
case class BasicRegion() extends Region
case class ChainRegion() extends Region
case class IfElseRegion() extends Region
case class WhileRegion() extends Region

class Pst(val t:TreeNode, r:Region ) {
  val regions = Map(t.id -> r) // mutable
}

object Pst {
  val tf = new TreeNodeFactory()
  def basic() = {
    val root = tf.make()
    new Pst(root, BasicRegion())
   }
  def chain(l:List[Pst]) = {
    val root = tf.make()
    val pst = new Pst(root, ChainRegion())
    for (i <- l) {
      root.addChild(i.t)
      pst.regions ++= i.regions
    }
    pst
  }
  def ifElse(b1:Pst, b2:Pst) = {
    val root =tf.make()
    root.addChild(b1.t)
    root.addChild(b2.t)
    val pst = new Pst(root, IfElseRegion()) 
    pst.regions++= b1.regions
    pst.regions++= b2.regions
    pst
  }

  def whileR(b:Pst) = {
    val root =tf.make()
    root.addChild(b.t)
    val pst = new Pst(root, IfElseRegion()) 
    pst.regions++= b.regions
    pst
  }
}

object PstFactory {
    /*
     */
  private def isIf(s:Statement) = {
    s match {
      case IfElse(_,_,_) => true
      case _ => false
    }
  }

  def isMergeNode(v:Vertex) = {
    // test string
    v.sid == "merge" 
  }

 
  def createIfPst(ifV:Vertex, g:Graph, exit:Vertex, m:CFGMap) = {
     val nextV = g.succ(ifV)
    // Because the CFG is structured these assertion should hold.
    assert(nextV.length == 2)
    val (merge1, b1Pst) = createPst(nextV(0), g, exit, m)
    val (merge2, b2Pst) = createPst(nextV(1), g, exit, m)
    assert(merge1 == merge2)

    assert(isMergeNode(merge1))
    (merge1, Pst.ifElse(b1Pst, b2Pst))
  }

  def createPst(entry:Vertex, g:Graph, 
		exit:Vertex, m:CFGMap):(Vertex, Pst) = {

    def isExitNode(v:Vertex) = {
      v == exit
    }
 
   // Traverse the SeseGraph from entry to exit
    val listOfRegion = ListBuffer[Pst]()  
    val listOfVertex = ListBuffer[Vertex]()
    
    def finishBasicRegion() {
      // Create a new Basic Region if the list of
      // statement so far is non-empty
      if (listOfVertex.length > 0) {
	val l = listOfVertex.toList
	listOfVertex.clear()
	val br = Pst.basic()
	listOfRegion.append(br)
      }
    }
    
    var complete  = false
    var currentV = entry
     var currentRegion = BasicRegion() // Assume
    while (!complete) {  
      val successors = g.succ(currentV)
      if (successors.length > 1) {
	// Reached a branch point 
	
	// First finish the basic region if any
	finishBasicRegion() 
	// Create the If-Pst
	val (mergeV, ifRegion) = createIfPst(currentV, g, exit, m)

	listOfRegion.append(ifRegion)
	currentV = mergeV
      } else if (successors.length == 1) {
	val v = successors.head
	if (isMergeNode(v)) {
	  // Reach a merge node
	  complete = true
	  currentV = v
	} else {
	  // regular node
	  complete = isExitNode(v)
	  if (!complete) {
	    listOfVertex.append(v)
	  }
	  currentV = v
	}
      } else {
	// Should not reach here
	throw new RuntimeException("No unique exit node")
      }
    }
    if (listOfRegion.isEmpty) {
      (currentV, Pst.basic())
    } else {
      finishBasicRegion()
      (currentV, Pst.chain(listOfRegion.toList))
    }
  }

  def createPst(l:List[Statement]):Pst = {
    if (l.forall( s => !isIf(s))) {
      Pst.basic()
    } else {
      val listOfRegion = ListBuffer[Pst]()  
      val listOfStmt = ListBuffer[Statement]()
      def finishBasicRegion() {
	if (!listOfStmt.isEmpty) {
	  // Create a new Basic Region and
	  // add append it to list of region
	  val l = listOfStmt.toList
	  listOfStmt.clear()
	  val br = Pst.basic()
	  listOfRegion.append(br)
	}
      }
      
      for (s <- l) {
	s match {
	  case IfElse(_,b1,b2) => {
	    finishBasicRegion()
	    val r1 = createPst(b1)
	    val r2 = createPst(b2)
	    listOfRegion.append( Pst.ifElse(r1,r2))
	  }
	  case Noop(_) => {
	    listOfStmt.append(s)
	  }
	  case While(_,b) => {
	    val r = createPst(b)
	    listOfRegion.append(Pst.whileR(r))
	  }
	  case Assignment(_,_) => listOfStmt.append(s)
	}
      }
      finishBasicRegion()
      Pst.chain(listOfRegion.toList)
    }
  }
    
}
  


