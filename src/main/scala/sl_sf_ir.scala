package sl.ir.sf

import sl.ir.HasId
import scala.collection.mutable.ArrayBuffer

/** 
  An intermediate representation of Stateflow objects. 
*/

/** Syntax part */
sealed abstract class StateflowObject extends AnyRef with HasId

sealed abstract class Statelike extends StateflowObject

case class State(override val id:Int, 
  val name:String, subStates: List[State] = List()) extends Statelike
{
  def toDotString = f"$id%d[shape=Mrecord]"+"[label=\"" +
    f"$name%s" + "\"]"
}

case class Junction(override val id:Int) extends Statelike
{
  val out = ArrayBuffer[Transition] ()
  val in = ArrayBuffer[Transition] ()
}

sealed abstract class Transition extends StateflowObject
{ def toDotString: String }

case class NormalTransition(override val id: Int, 
  val source:Statelike, val destination:Statelike,
  val condition: Option[Condition], val event: Option[Event],
  val cAction: Option[Action], val tAction: Option[Action] ) 
    extends Transition
{
  override def toDotString = f"${source.id}%d -> ${destination.id}%d" + 
    "[label=\"" + f"$id%d" + "\"]"
}

case class DefaultTransition(override val id: Int,
  val destination: Statelike) extends Transition
{
  override def toDotString = "default[style=invisible]\n" + 
  f"default -> ${destination.id}%d" + "[label=\"" + 
  f"$id%d" + "\"]"
}

case class Event(override val id: Int) extends StateflowObject
{
}

case class Data(override val id:Int) extends StateflowObject
{
}

class Condition {
}

class Action {
}

class Function {
}

class Chart(val states: List[State], 
  val transitions:List[Transition] ) {
  // No known mutable data members yet

  def toDotString = {
    "digraph G { " +
      (states.foldLeft ("") { (s, x) => s + "\n" + x.toDotString }) +
       transitions.foldLeft ("") { (s, t) => s+ "\n" + t.toDotString} +
      "\n}\n"
  }
}
