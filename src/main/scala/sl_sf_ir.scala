package sl.ir.sf

import sl.ir.HasId
import scala.collection.mutable.ArrayBuffer

/** 
  An intermediate representation of Stateflow objects. 
*/

/** Syntax part */
sealed abstract class StateflowObject extends AnyRef with HasId

sealed abstract class StateLike extends StateflowObject
{
  def toDotString: String
}

case class State(override val id:Int, 
  val name:String, subStates: List[StateLike] = List()) extends StateLike
{
  def toDotString = f"$id%d[shape=Mrecord]"+"[label=\"" +
    f"$name%s" + "\"]"

  // Ordered out-going transitions
  val out = ArrayBuffer[Transition]()
}

case class Junction(override val id:Int) extends StateLike
{
  val out = ArrayBuffer[Transition] ()
  val in = ArrayBuffer[Transition] ()
  def toDotString = f"$id%d[shape=circle]" + "[label=\"\"]"
}

sealed abstract class Transition extends StateflowObject
{ def toDotString: String }

case class NormalTransition(override val id: Int, 
  val source:StateLike, val destination:StateLike,
  val condition: Option[Condition], val event: Option[Event],
  val cAction: Option[Action], val tAction: Option[Action] ) 
    extends Transition
{
  override def toDotString = {
    val condStr = condition match {
      case Some(c) => f"[${c.text}%s]"
      case None => ""
    }

    f"${source.id}%d -> ${destination.id}%d" +
    "[label=\"" +  f"$id%d" + condStr + "\"]"
  }
}

case class DefaultTransition(override val id: Int,
  val destination: StateLike) extends Transition
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

class Condition(val text: String) {

}

class Action {
}

class Function {
}

class Chart(val states: List[StateLike], 
  val transitions:List[Transition] ) {
  // No known mutable data members yet

  def toDotString = {
    "digraph G { " +
      (states.foldLeft ("") { (s, x) => s + "\n" + x.toDotString }) +
       transitions.foldLeft ("") { (s, t) => s+ "\n" + t.toDotString} +
      "\n}\n"
  }
}
