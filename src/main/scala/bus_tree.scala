package my.se

// A bus is a hierarchical data structure for propagating 
// dataflow dependence with the dataflow graph. 

import my.se._

abstract sealed class BusElement 

case class AtomicElement (val name: String, val width: Int) extends BusElement
{
}

case class Bus (val name: String, val children:List[BusElement]) extends BusElement
{
  lazy val width:Int = {
    children.foldLeft (0) {
      (acc:Int, c:BusElement) => acc + (
        c match {
          case a:AtomicElement => a.width
          case b:Bus => b.width
        }) 
    }
  }

  lazy val nChildren = children.length
  lazy val nDescendants:Int = 
    children.foldLeft (0) {
      (acc, c) => acc + (
        c match {
          case _:AtomicElement => 1
          case b:Bus => b.nDescendants
        }
      )
    }

  
}
