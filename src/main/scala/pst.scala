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

class Pst(val root:TreeNode, r:Region ) {
  val regions = Map(root.id -> r) // mutable
}

object Pst {
  val tf = new TreeNodeFactory()
  def basic(l:List[Statement]) = {
    val root = tf.make()
    new Pst(root, BasicRegion())
   }
  def chain(l:List[Pst]) = {
    val root = tf.make()
    val pst = new Pst(root, ChainRegion())
    for (i <- l) {
      root.addChild(i.root)
      pst.regions ++= i.regions
    }
    pst
  }
  def ifElse(b1:Pst, b2:Pst) = {
    val root =tf.make()
    root.addChild(b1.root)
    root.addChild(b2.root)
    val pst = new Pst(root, IfElseRegion()) 
    pst.regions++= b1.regions
    pst.regions++= b2.regions
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

  def createPst(l:List[Statement]):Pst = {
    if (l.forall( s => !isIf(s))) {
      Pst.basic(l)
    } else {
      val listOfRegion = ListBuffer[Pst]()  
      val listOfStmt = ListBuffer[Statement]()
      def finishBasicRegion() {
	if (!listOfStmt.isEmpty) {
	  // Create a new Basic Region and
	  // add append it to list of region
	  val l = listOfStmt.toList
	  listOfStmt.clear()
	  val br = Pst.basic(l)
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
	  case _ => {
	    listOfStmt.append(s)
	  }
	}
      }
      finishBasicRegion()
      Pst.chain(listOfRegion.toList)
    }
  }
    
}
  


