package sl.ir.sf

import sl.ir.HasId

/** An intermediate representation of Stateflow objects. 
*/
sealed abstract class StateflowObject extends AnyRef with HasId

case class State(override val id:Int) extends StateflowObject
{

}

case class Transition(override val id: Int) extends StateflowObject
{
}

case class Action(override val id: Int) extends StateflowObject
{
}

case class Event(override val id: Int) extends StateflowObject
{
}

case class Data(override val id:Int) extends StateflowObject
{
}


