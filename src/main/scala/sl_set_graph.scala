/** Intermediate representation for Simulink synchronous block set */

package sl.ir

import sl.ir._
import my.se._
import sl.ir._
import my.utility._

import scala.collection.mutable.Map

class SyncSet(val l:List[Proc], val id:Int) extends HasId
{
}

class SynSetGraph() {
  val g = new Graph()
  val m = Map[Int,SyncSet]()

  def vSize = g.V.size
  def eSize = g.E.size

}


class SyncFactory() {
  
}


