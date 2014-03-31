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

case class Data(override val id:Int, val name:String) extends StateflowObject
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

  def states(names: Array[String]) = 
    (for (n <- names;
      x = state(n)) yield x).toArray
  

  def data(name:String) = {
    val d = Data(gensym(), name)
    objects += (d.id -> d)
    d
  }

  def dataArray(names:Array[String]) = 
    (for (n <- names; x = data(n)) yield x).toArray


  def normalTransition(source: StateLike, 
    destination: StateLike, 
    condition: Option[Condition],
    event: Option[Event], 
    cAction: Option[Action],
    tAction: Option[Action]): NormalTransition = {
    val t = NormalTransition(gensym(), source, destination,
      condition, event, cAction, tAction)
    objects += (t.id -> t)
    t
  }

  def normalTransition(source: StateLike,
    destination: StateLike, 
    c: Condition = null,
    e: Event = null, 
    cA: Action = null,
    tA: Action = null): NormalTransition = {
    val condition = if (c == null) None else Some(c)
    val event = if (e == null) None else Some(e)
    val cAction = if (cA == null) None else Some(cA)
    val tAction = if (tA == null) None else Some(tA)
    normalTransition(source, destination, condition,
      event, cAction, tAction)
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
  // Get the node for a given id, returns its Stateflow id
  val nodes = Map[Int, Int]()
  val objToId = Map[Int, Int]()

  // Create a dependence graph from the Stateflow objects
  def createGraph = {
    val g = new Graph()

    for ((id, obj) <- objects) {
      val v = g.newVertex("") 
      nodes += (v.id -> id) // Graph id to SF id
      objToId += (id -> v.id) // SF id to graph id
    }

    for ((id, obj) <- objects) {
      obj match {
        case NormalTransition(id, source, dst, _,_,_,_) => {
          val srcId = objToId(source.id)
          val dstId = objToId(dst.id)
          val tId = objToId(id)

          g.addEdge(srcId, tId)
          g.addEdge(tId, dstId)
        }
        case DefaultTransition(id, dst) => {
          val dstId = objToId(dst.id)
          val tId = objToId(id)
          g.addEdge(tId, dstId)
        }
        case _ => {}
      }
    }
    g
  }

  val g = createGraph 

  def toDotString  = {
    var result = " digraph G {\n"
    result += "graph [rankdir=\"LR\"]\n"
    g.V.foreach( v =>
      {
        result = result + v.id +
        "[label=\"" +
        nodes(v.id) +"\"]\n"
      })
    g.E.foreach( e =>
      result = result +
	e.from.id + " -> " +
	e.to.id +
	"[label=\"" + "\"]\n")
    result + " }\n"
  }
    
 
  
  def nNodes = g.V.size
  def nEdges = g.E.size

  def backreach(i:Int) = {
    val reach = new Reachable(g)
    reach.backward(objToId(i)).map(nodes(_))
  }

  def backreach(objId:Array[Int], dependence:Dependence) = {
    val reach = new Reachable(g)
    val inactive = new Inactive(Array[Int](), Array[Int]())
    val start = objId.map( objToId(_))
    val vertices = reach.backward(start, inactive, dependence)
    vertices.map(nodes(_))
  }
}
