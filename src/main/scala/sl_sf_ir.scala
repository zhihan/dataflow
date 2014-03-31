package sl.ir.sf

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map

// My utility imports
import my.se._ // Graph, Vertex, Edge
import sl.ir.HasId
import my.utility.Gensym  


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

/** Utility object for StateflowObject */
object StateflowObject {
  def isStateLike(n: StateflowObject) = {
    n match {
      case x:StateLike => true
      case t:Transition => false
      case d:Data => false
      case e:Event => false
    }
  }
}

/**
  A utility class to create StateflowObject objects and keep
  track of the ids.
  */
class StateflowFactory() {
  val objects = Map[Int, StateflowObject]()
  val gensym = new Gensym()

  def state(name: String) = {
    val s = State(gensym(), name)
    objects += (s.id -> s)
    s
  }

  def normalTransition(source: StateLike, 
    destination: StateLike, condition: Option[Condition],
    event: Option[Event], cAction: Option[Action],
    tAction: Option[Action]) = {
    val t = NormalTransition(gensym(), source, destination,
      condition, event, cAction, tAction)
    objects += (t.id -> t)
    t
  }
  def defaultTransition(destination: StateLike) = {
    val t = DefaultTransition(gensym(), destination)
    objects += (t.id -> t)
    t
  }

  def junction() = {
    val j = Junction(gensym())
    objects += (j.id -> j)
    j
  }
}

/**
  A Stateflow dependence graph consists a set of Stateflow objects
  (using their ids) and stores the depenedence among the objects.
  */

class StateflowDependenceGraph (val objects: Map[Int, StateflowObject]) {
  val g = new Graph() 

  // Get the node for a given id, returns its Stateflow id
  val nodes = Map[Int, Int]()
  
  def nNodes = g.V.size

}
