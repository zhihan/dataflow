package sl.ir.sf

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.collection.mutable.Queue


// My utility imports
import my.se._ // Graph, Vertex, Edge
import my.utility.HasId
import my.utility.Gensym  
import sl.ir._

/** 
  An intermediate representation of Stateflow objects. 
*/

/** Syntax part */
sealed abstract class StateflowObject extends HasId
{
  def name: String
}

sealed abstract class StateLike extends StateflowObject
{
  def toDotString: String
}

case class State(override val id:Int, 
  override val name:String, 
  subStates: List[StateLike] = List()) extends StateLike
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
  def name = ""
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
  def name = ""
}

case class DefaultTransition(override val id: Int,
  val destination: StateLike) extends Transition
{
  override def toDotString = "default[style=invisible]\n" + 
  f"default -> ${destination.id}%d" + "[label=\"" + 
  f"$id%d" + "\"]"

  def name = ""
}

case class Event(override val id: Int) extends StateflowObject
{
  def name = ""
}

case class Data(override val id:Int, 
  override val name:String) extends StateflowObject
{
  def toDotString = f"$id%d[shape=box]"+"[label=\"" +
    f"$name%s" + "\"]"
}

class Condition(val text: String) {
  def name = ""
}

class Action {
}

class Function {
}

class Chart(val states: List[StateLike], 
  val transitions:List[Transition], 
  val data:List[Data]) {
  // No known mutable data members yet

  def toDotString = {
    "digraph G { " +
    (states.foldLeft ("") { (s, x) => s + "\n" + x.toDotString }) +
    transitions.foldLeft ("") { (s, t) => s+ "\n" + t.toDotString} +
    data.foldLeft("") { (s, t) => s+ "\n" + t.toDotString}  + 
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

class SFDependence(val fromObj: Array[Int], val toObj: Array[Int]) 
{
  def getDependence(objToVId: Map[Int,Int], g:Graph) = 
    new Dependence(fromObj.map(v => objToVId(v)),
      toObj.map(v=>objToVId(v)), g)

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
        nodes(v.id) +":" + objects(nodes(v.id)).name + 
        "\"]\n"
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
    val reach = new StateflowDependenceReachBack(this, dependence)
    reach.run(objId)
  }
}

/** A visitor is a stateful object that visit the dataflow
  graph in DFS order. */
class StateflowDependenceBackwardVisitor (dg: StateflowDependenceGraph, 
  dep:Dependence) {

  val graph = dg.g
  val inactive = new Inactive(Array[Int](), Array[Int]())
  // Hash incoming edges for fast lookup
  val inE = graph.E.groupBy(_.to.id) 

  private def predecessor = {(v:Vertex) => 
    Graph.customizedPredecessor(inE, graph, v, inactive, dep)}

  // For use with BFSAlways algorithm
  def visit(v:Vertex, visited:Set[Vertex]) = {
    val toVisit = ArrayBuffer[Vertex]()
    val pred = predecessor(v)
    for (p <- pred; if (!visited.contains(p))) {
      toVisit += p
    }
    toVisit
  }
}

class StateflowDataflowMap(inObjId: Array[Int], inNodeId: Array[Int], 
  outObjId:Array[Int], outNodeId:Array[Int]) {

  private val inObjToNode = Map() ++ (
    for ((k,v) <- inObjId.zip(inNodeId)) yield (k->v))
  private val inNodeToObj = Map() ++ (
    for ((k,v) <- inNodeId.zip(inObjId)) yield (k->v))
  private val outObjToNode = Map() ++ (
    for ((k,v) <- outObjId.zip(outNodeId)) yield (k->v))
  private val outNodeToObj = Map() ++ (
    for ((k,v) <- outNodeId.zip(outObjId)) yield (k->v))

  def isInObj(i:Int) = inObjToNode.contains(i)
  def isOutNode(d:Int) = outNodeToObj.contains(d)

  def getOutObj(d:Int) = outNodeToObj(d)
  def getInNode(i:Int) = inObjToNode(i)

  def inObjString = inObjId.map(_.toString).mkString(",")
  def outObjString = outObjId.map(_.toString).mkString(",")
}


class StateflowDependenceRepeatedVisitor(dg: StateflowDependenceGraph,
  dfMap: StateflowDataflowMap, 
  dep: Dependence) { 
  val graph = dg.g
  val inactive = new Inactive(Array[Int](), Array[Int]())
  // Hash incoming edges for fast lookup
  val inE = graph.E.groupBy(_.to.id) 

  private def predecessor = {(v:Vertex) => 
    Graph.customizedPredecessor(inE, graph, v, inactive, dep)}

  // State of the visitor, keep track of visited vertices
  // within the Stateflow dependence graph
  val visited = Set[Vertex]()

  // Note, the provided dfVisited argument is from the outer loop
  def visit(dfVId:Int) = {
    val toVisitId = ArrayBuffer[Int]()

    val sfV = graph.getV(dg.objToId(dfVId))
    var q = Queue[Vertex](sfV)

    while (! q.isEmpty ) {
      val e = q.dequeue()
      if (! visited.contains(e)) {
        // Visit new nodes

        // If the node is on SF-SL border, add to candidate
        val eObjId = dg.nodes(e.id)  // Map SF Graph to SF obj
        if (dfMap.isInObj(eObjId)) { // SF Obj has map to SL Graph
          toVisitId.append(eObjId)
        }
        val next = predecessor(e)
        /*
        print("next ")
        next.foreach{i=> print(i.id); print(" ") }
        println("")
         */
        visited.add(e)
        for (c <- next if !visited.contains(c)) q.enqueue(c)
      }
    }
    toVisitId // return the obj Id
  }

}

/** A simple reachability analysis algorithm for Stateflow chart only */
class StateflowDependenceReachBack(val dg: StateflowDependenceGraph,
  dependence: Dependence) {
  val visitor = new StateflowDependenceBackwardVisitor(dg, dependence)
  def run(objId:Array[Int]) = {
    val bfs = new BFSAlways(visitor.visit)
    val start = objId.map(dg.objToId(_))
    bfs.initialize(dg.g.getV(start))
    bfs.run()

    val vertices = bfs.visited.toArray
    vertices.map( x => dg.nodes(x.id))
  }
}




/** Hybrid bfs search algorithm on Simulink DF graph and Stateflow 
  Dependence graph */

class SLSFGraphVisitor(
  graph: Graph,  // Dataflow Graph to get Vertex from Id
  dfgVisitor: DataflowBackVisitor,
  dgVisitor: StateflowDependenceRepeatedVisitor,
  sfMap: StateflowDataflowMap)
{
  // DF
  def visit(dfgV:Vertex, dfgVisited:Set[Vertex]): ArrayBuffer[Vertex] = {
    if (sfMap.isOutNode(dfgV.id)) {
      // Map the vertex to Sf object id
      val objId = sfMap.getOutObj(dfgV.id)
      // println( "Visit SF graph " + objId)
      val inObjId = dgVisitor.visit(objId)
      // println("InObjId " + inObjId)

      val inVId = inObjId.map( sfMap.getInNode(_))
      inVId.map( graph.getV(_))
    } else {
      dfgVisitor.visitBackward(dfgV, dfgVisited)
    }
  }
}

class SFReachSet(ids:Set[Int]) {
  def getAll = ids.toArray
}

class SLSFReachSet(graph: DataflowGraph, 
  reachedVertices: scala.collection.immutable.Set[Int],
  reachedSubBus: Map[Int,SubBus], 
  reachedSfObj: Set[Int]) {
  val dfgReach = new ReachSet(graph, reachedVertices, reachedSubBus)
  val sfReach = new SFReachSet(reachedSfObj)
}



class SLSFGraphBackReach(dfg: DataflowGraph, dfgDep: Dependence,
  dg: StateflowDependenceGraph, sfDep: Dependence, 
  sfMap: StateflowDataflowMap)
{

  val dfgVisitor = { 
    val busProcs = Map[Int, BusAction]()
    val busElementEdge = Map[Int, VBusSelect]()
    val inactive = new Inactive(null, null)
    new DataflowBackVisitor(dfg.g, dfg.nodes,
      busProcs, busElementEdge, inactive, dfgDep)
  }
  val dgVisitor = new StateflowDependenceRepeatedVisitor(dg, 
    sfMap, sfDep)

  val visitor = new SLSFGraphVisitor(dfg.g, dfgVisitor, dgVisitor, sfMap)

  def reach(dfgId: Array[Int]) = {
    val bfs = new BFSAlways(visitor.visit)
    val start = dfgId.map(dfg.g.getV(_))
    bfs.initialize(start)
    bfs.run()
    
    val immutableV = scala.collection.immutable.Set(bfs.visited.toSeq:_*).map(_.id)

    val sfIds = dgVisitor.visited.map{v => dg.nodes(v.id)}
    new SLSFReachSet(dfg, immutableV, dfgVisitor.busReached, 
    sfIds )

  }

}

