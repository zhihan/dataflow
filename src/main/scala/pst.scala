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

class PstFactory {
  val tf = new TreeNodeFactory()

  def basic() = {
    val root = tf.make()
    // println("Basic: " + root.id)
    new Pst(root, BasicRegion())
   }
  def chain(l:List[Pst]) = {
    val root = tf.make()
    // println("Chain: " + root.id)

    val pst = new Pst(root, ChainRegion())
    for (i <- l) {
      root.addChild(i.t)
      pst.regions ++= i.regions
    }
    pst
  }
  def ifElse(b1:Pst, b2:Pst) = {
    val root =tf.make()
    // println("If: " + root.id)

    root.addChild(b1.t)
    root.addChild(b2.t)
    val pst = new Pst(root, IfElseRegion()) 
    pst.regions++= b1.regions
    pst.regions++= b2.regions
    pst
  }

  def whileR(b:Pst) = {
    val root =tf.make()
    // println("While: " + root.id)
    root.addChild(b.t)
    val pst = new Pst(root, WhileRegion()) 
    pst.regions++= b.regions
    pst
  }
}

class ComputePst() {
    /*
     */
  val pstF = new PstFactory()
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
    // Move past merge
    val mergeNext = g.succ(merge1)
    assert(mergeNext.length == 1)
    (mergeNext.head, pstF.ifElse(b1Pst, b2Pst))
  }

  def createWhilePst(header: Vertex, g:Graph, m:CFGMap) = {
     // Entry node has only one successor

    val successors = g.succ(header)
    assert(successors.length == 1) 
    val whileNode = successors.head
    
    val loopEntry = g.succ(whileNode).filter(_.sid != "whileExit")
    val loopExit = g.succ(whileNode).filter(_.sid == "whileExit")
    assert(loopExit.length == 1)
    val (_,body) = createPst(loopEntry.head, g, header, m)

    val nextV = g.succ(loopExit.head)
    assert(nextV.length == 1)
    (nextV.head, pstF.whileR(body))
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
      def isNoop(v:Vertex) = {
	m.getStatements(v).forall ( 
	  s =>
	    s match 
	  {
	    case Noop(_) => true
	    case _ => false
	  }
	)
      } 
      // Create a new Basic Region if the list of
      // statement so far is non-empty
      if (listOfVertex.length > 0) {
	if (!listOfVertex.forall(isNoop(_))) {
	  val l = listOfVertex.toList
	  
	  val br = pstF.basic()
	  listOfRegion.append(br)
	} 
	listOfVertex.clear()
      }
    }
    
    var complete  = false
    var currentV = entry
    var currentRegion = BasicRegion() // Assume
    while (!complete && !isExitNode(currentV)) {  
      val successors = g.succ(currentV)
      val predecessors = g.pre(currentV)
      // Detect start of a new region
      if (successors.length > 1 || predecessors.length > 1) {
	// Reached a region beginning
	
	// First finish the basic region if any
	finishBasicRegion() 

	if (currentV.sid == "if") {
	  // Create the If-Pst
	  val (lastV, ifRegion) = createIfPst(currentV, g, exit, m)
 	  listOfRegion.append(ifRegion)
	  currentV = lastV
	} else if (currentV.sid == "whileHead") {
	  // Perform backward reachability
	  val (lastV, whileRegion) = createWhilePst(currentV, g, m)
	  listOfRegion.append(whileRegion)
	  currentV = lastV
	} else if (isMergeNode(currentV)) {
	  // Reach a merge node
	  complete = true
	}
      } else if (successors.length == 1) {
	val v = successors.head
	listOfVertex.append(currentV)
	complete = isExitNode(currentV)
	if (!complete) { 
	  currentV = v
	}
      }       
    }
    finishBasicRegion()
    if (listOfRegion.length == 1) {
      (currentV, listOfRegion.head)
    } else {
      (currentV, pstF.chain(listOfRegion.toList))     
    }
  }

  def createPst(l:List[Statement]):Pst = {
    val listOfRegion = ListBuffer[Pst]()  
    val listOfStmt = ListBuffer[Statement]()
    def isNoop(s:Statement) = {
      s match {
	case Noop(_) => true
	case _ => false
      }
    }
    def finishBasicRegion() {
      if (!listOfStmt.isEmpty && 
	!listOfStmt.forall(isNoop(_))) {
	// Create a new Basic Region and
	// add append it to list of region
	val l = listOfStmt.toList
	listOfStmt.clear()
	val br = pstF.basic()
	listOfRegion.append(br)
      }
    }
    
    for (s <- l) {
      s match {
	case IfElse(_,b1,b2) => {
	  finishBasicRegion()
	  val r1 = createPst(b1)
	  val r2 = createPst(b2)
	  listOfRegion.append( pstF.ifElse(r1,r2))
	}
	case Noop(_) => {
	  // Nothing
	}
	case While(_,b) => {
	  val r = createPst(b)
	  listOfRegion.append(pstF.whileR(r))
	}
	case Assignment(_,_) => listOfStmt.append(s)
      }	
    }
    finishBasicRegion()
    if (listOfRegion.length > 1) {
      pstF.chain(listOfRegion.toList)
    } else {
      listOfRegion.head
    }
  }
}

  


