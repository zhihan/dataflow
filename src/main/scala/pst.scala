// Program structure tree 
// Construct a PST from a program IR
package my.ir.pst

import my.ir._

abstract class Region
case class BasicRegion(body:List[Statement]) extends Region
case class ChainRegion(body:List[Region]) extends Region
case class IfElseRegion(b1:Region, b2:Region) extends Region



object Pst {
    /*
     */
  private def isIf(s:Statement) = {
    s match {
      case IfElse(_,_,_) => true
      case _ => false
    }
  }

  def createPst(l:List[Statement]):Region = {
    if (l.forall( s => !isIf(s))) {
      BasicRegion(l)
    } else {
      // 
      ChainRegion(List[Region]())
    }
  }
    
}
  


