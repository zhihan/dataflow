package my.se

import java.util.Arrays

import scala.collection.mutable.Queue
import scala.collection.mutable.Set
import scala.collection.mutable.ArrayBuffer

import scala.collection.JavaConversions._ 
import scala.collection.mutable.HashMap


abstract class LabelOp[Label] {
  type T = Label
  def lessThan(l:Label, r:Label):Boolean
  def max(l:Label, r:Label):Label
}

/** Vertex class consists of an id, a string for sid
 * and in- out- edges. The edges are ArrayBuffer, which are
 * mutable data members.
 */
class Vertex(_id: Int, s: String) {
  val id = _id
  val sid = s
}

/* Edge class consists of id, and the two end vertices
 */
class Edge(_id: Int,  _from:Vertex, _to:Vertex) {
  val id = _id
  val from = _from
  val to = _to
}

/*
 * Graph class implements a simple graph using edgelist.
 */
class Graph() {
  
  var V = ArrayBuffer[Vertex]()
  val E = ArrayBuffer[Edge]()

  var id = 0

  def newVertex(name:String) = {
    id += 1
    val v = new Vertex(id, name)
    V  += v
    v
  }

  def addEdge(src:Vertex, dst:Vertex):Edge = {
    val id = E.size()
    val e = new Edge(id, src, dst)
    E.add(e)
    e 
  }

  /** Remove a vertex, its source and destination will be disconnected */
  def removeVertex(v:Vertex) {
    val out = outE(v)
    val in = inE(v)
    out.foreach( e=> E.remove(E.indexOf(e)))
    in.foreach( e=> E.remove(E.indexOf(e)))
    V.remove(V.indexOf(v))
  }

  /** Remove a vertex and add an edge that by-pass this vertex.
   *  After the reduction its source and destination will be connected. */
  def reduceVertex(v:Vertex) {
    val dstV = succ(v)
    val srcV = pre(v)
    removeVertex(v)

    srcV.foreach( src =>
      dstV.foreach( dst =>
	addEdge(src, dst)))
  }

  def outE(v: Vertex) = ArrayBuffer[Edge]() ++ (E.filter (e => v.eq(e.from) ))
  def inE(v:Vertex) = ArrayBuffer[Edge]() ++ (E.filter  (e => v.eq(e.to) ))

  def succ(v:Vertex) = {
    val out = E.filter (e => v.eq(e.from) )
    val r = out.map( e => e.to)
    ArrayBuffer[Vertex]() ++ r
  }

  def pre(v:Vertex) = {
    val out = E.filter (e => v.eq(e.to) )
    val r = out.map( e => e.from)
    ArrayBuffer[Vertex]() ++ r
  }

  def addEdge(src:Int, dst:Int): Edge = {
    val from = getV(src)
    val to = getV(dst)
    addEdge(from, to)
  }


  def print() {
    println(" digraph G {")
    V.foreach( v => 
      {
        println(v.id + "[label=\"" + v.id + 
                " sid:"+ v.sid +"\"]")
        inE(v).foreach(e => println(e.from.id + " -> " + e.to.id))
      }
            )
    println(" } ") 
  }
  
  def toDotString  = {
    var result = " digraph G {\n"
    result += "graph [rankdir=\"LR\"]\n"
    V.foreach( v => 
      {
        result = result + v.id + 
        "[label=\"" + v.id + 
        " sid:"+ v.sid +"\"]\n"
      })
    
    E.foreach( e =>
      result = result + 
	      e.from.id + " -> " + 
	      e.to.id + 
	      "[label=\"" + e.id + "\"]\n")
    result + (" } ") 
  }
  
  private def fromID(ids: Array[Int]) = {
    val idSet = ids.toSet
    V.filter( v => idSet.contains(v.id) ).toArray 
  }

  def butID(ids: Array[Int]) = {
    val idSet = ids.toSet
    val result = V.filter( v=> !idSet.contains(v.id) ).toArray
    result.map(v => v.id)
  }
  
  def getV(which: Int) = {
    val v = V.find( x => x.id == which)
    v match {
      case Some(v) => v
      case None => throw new RuntimeException("Wrong id given")
    }
  }
  
  def getV(which: Array[Int]): Array[Vertex] = {
    which.map(x => getV(x))
  }

  def getE(from: Int, to: Int) = {
    val vFrom = getV(from)
    val e = outE(vFrom).find( e => e.to.id ==to)
    e match {
      case Some(x) => x.id
      case None => throw new RuntimeException(
        "Cannot find edge from " + from + " to " + to)
    }
  }

}

class SeseGraph(g: Graph, en:Vertex, ex:Vertex) 
{
  val graph = g
  val entry = en
  val exit = ex
}

// Alternative constructor for Graph
class GraphFactory() {
  // XXX This needs to be changed
  def make(v: Array[Vertex]) = {
    val g = new Graph()
    g.V ++= v
    g
  }
}
/* Breadth-First search
 *
 * Used by Reachable class to compute reachability.
 */
class BFS(callback: Vertex => ArrayBuffer[Vertex]){
  // Immutable members
  val cb = callback
  val visited = Set[Vertex]()

  // Mutable members
  var q = Queue[Vertex]()

  private def clearVisit() {
    visited.clear()
  }
  def initialize(e: Vertex) {
    clearVisit()
    q.enqueue(e)
  }

  def initialize(e: Array[Vertex]) {
    clearVisit()
    e.foreach(x => q.enqueue(x))
  }


  def run() {
    while (! q.isEmpty() ) {
      val e = q.dequeue()
      if (!visited.contains(e)) {
        // Not visited before
        val next = cb(e)
        visited.add(e)
        for (c <- next if !visited.contains(c))
          q.enqueue(c)
      }
    }
  }
}

/* Inactive class implements a mechanism to filter nodes
 and edges in the BFS search.
 * */
class Inactive(vArray: Array[Int], eArray:Array[Int])
{
  val v = if (vArray != null ) vArray.toSet else Set[Int]()
  val e = if (eArray != null ) eArray.toSet else Set[Int]()
}

/* Reachable
 * Compute graph reachability using BFS search.
 *
 * The default method performs a simple BFS search. When an
 * Inactive object is given, the inactive object is used to
 * stop searches at specified nodes and edges.
 */
class Reachable(graph: Graph) {
  val successor = (v:Vertex)=> graph.succ(v)
  val predecessor = (v:Vertex)=> graph.pre(v)

  def forward(start:Vertex):Array[Vertex] = {
    val bfs = new BFS(successor)
    
    bfs.initialize(start)
    bfs.run()
    bfs.visited.toArray
    
  }
  def forward(start:Int):Array[Int] = {
    val f = forward(graph.getV(start))
    f.map(v => v.id)
  }

  def forward(start: Array[Int]) = {
    val bfs = new BFS(successor)
    
    bfs.initialize(graph.getV(start))
    bfs.run()
   
    val vertices = bfs.visited.toArray
    vertices.map(v => v.id)
    
  }

  private def filteredSuccessor(v:Vertex, inactive:Inactive) = {
    val out = graph.outE(v)
    val filteredOut = out.filter(e => !inactive.e.contains(e.id))
    val filteredSucc = filteredOut.map(e => e.to)
    val r = filteredSucc.filter(v => !inactive.v.contains(v.id))
    ArrayBuffer[Vertex]() ++ r
  }

  private def errorOutIfInSet(v: Int, s: scala.collection.Set[Int]):Unit = {
    if (s contains v) {
      throw new RuntimeException("Error: " + v + " is in the set.")
    }
  }
  private def errorOutIfInSet(v: Vertex, s:scala.collection.Set[Int]):Unit =
    errorOutIfInSet(v.id, s)


  def forward(start: Vertex, inactive: Inactive): Array[Vertex] = {
    val bfs = new BFS((v:Vertex)=> filteredSuccessor(v, inactive))

    // Should I error out if start is in inactive?
    errorOutIfInSet(start, inactive.v)

    bfs.initialize(start)
    bfs.run()
    bfs.visited.toArray
    
  }

  def forward(start:Int, in:Inactive):Array[Vertex] = 
    forward(graph.getV(start), in)

  def forward(start: Array[Int], inactive:Inactive) = {
    val bfs = new BFS((v:Vertex) => 
                        filteredSuccessor(v, inactive))
    
    start.foreach( errorOutIfInSet(_, inactive.v))
    
    bfs.initialize(graph.getV(start))
    bfs.run()
    val vertices = bfs.visited.toArray
    vertices.map(v => v.id)
  }

  
  // Backward reachability

  def backward(start:Int) = {
    val bfs = new BFS(predecessor)
    
    bfs.initialize(graph.getV(start))
    bfs.run()
    
    val vertices = bfs.visited.toArray
    vertices.map(v => v.id)
  }

  def backward(start: Array[Int]) = {
    val bfs = new BFS(predecessor)
    
    bfs.initialize(graph.getV(start))
    bfs.run()
   
    val vertices = bfs.visited.toArray
    vertices.map(v => v.id)
    
  }

  private def filteredPredecessor(v:Vertex, inactive:Inactive) = {
    val out = graph.inE(v)
    val filteredOut = out.filter(e => !inactive.e.contains(e.id))
    val filteredSucc = filteredOut.map(e => e.from)
    val r = filteredSucc.filter(v => !inactive.v.contains(v.id))
    ArrayBuffer[Vertex]() ++ r
  }

  def backward(start: Int, inactive: Inactive) = {
    val bfs = new BFS((v:Vertex) => 
			filteredPredecessor(v, inactive)
                    )
    errorOutIfInSet(start, inactive.v)
    
    bfs.initialize(graph.getV(start))
    bfs.run()
    
    val v = bfs.visited.toArray
    v.map(v => v.id)
    
  }


  def backward(start: Array[Int], inactive:Inactive) = {
    val bfs = new BFS((v:Vertex) => 
                        filteredPredecessor(v, inactive))
    
    start.foreach( errorOutIfInSet(_, inactive.v))
    
    bfs.initialize(graph.getV(start))
    bfs.run()
    val vertices = bfs.visited.toArray
    vertices.map(v => v.id)
  }

}

/** PropagateLabel
 * 
 * Propagate label set in the dataflow diagram until no more label is
 * found. 
 */
class PropagateLabel[LabelT] (graph: Graph, 
			      op:LabelOp[LabelT]) {
  val successor = (v:Vertex)=> graph.succ(v)
  val predecessor = (v:Vertex)=> graph.pre(v)
  
  def propagate(start:Array[Vertex], 
                l:HashMap[Int,LabelT],
                getNext:Vertex=>ArrayBuffer[Vertex]) {

    def update(v:Vertex):ArrayBuffer[Vertex] = {
      val n = getNext(v)
      val result = new ArrayBuffer[Vertex]()
      n.foreach( x =>
        if (!op.lessThan(l(v.id), l(x.id))) {
          l(x.id) = op.max(l(v.id), l(x.id))
          result.add(x)
        }
	      )
      result
    }

    val q = Queue[Vertex]()
    start.foreach(x => q.enqueue(x))

    while (!q.isEmpty()) {
      val e = q.dequeue()
      val next = update(e)
      for (c <- next) q.enqueue(c)
    }
    
  }

  def forward(start:Array[Int], l:HashMap[Int,LabelT]) {
    propagate(graph.getV(start), l, successor)
  }
  def backward(start:Array[Int], l:HashMap[Int,LabelT]) {
    propagate(graph.getV(start), l, predecessor)
  }
}

/**
 * WriteGraphviz write the graph to a graphviz string. A customed
 * vertex writer and edge writer can be provided to write the
 * additional node properties and edge properties.
 * 
 */
object writeGraphviz {
  // Create a v-to-id and e-to-id map
  private def createVMap(g: Graph) = {
    var vIdMap = Map[Vertex, Int]()
    var i: Int = 1

    for ( v <- g.V ) {
      vIdMap += (v -> i)
      i +=1 
    }

    vIdMap
  }

  private def createEMap(g: Graph) = {
    var eIdMap = Map[Edge, Int]()
    var i: Int = 1

    for ( e <- g.E ) {
      eIdMap += (e -> i)
      i +=1 
    }

    eIdMap
  }

  private def vListString(g: Graph,vIdMap: Map[Vertex,Int],
                          vw: Vertex=>String
                        ) = {
    var s = ""
    for ( v <- g.V) {
      s = s + vIdMap(v) + vw(v) + "\n"
    }
    s
  }

  private def eListString(g: Graph, vIdMap: Map[Vertex,Int], 
                        ew: Edge => String ) = {
    var s = ""
    for ( e <- g.E) {
      s = s + vIdMap(e.from) + " -> " + vIdMap(e.to) + ew(e) + "\n"
    }
    s
  }
  
  val defaultVW: Vertex=>String = { _ => "" }
  val defaultEW: Edge=>String = { _=>"" } 

  def apply(g: Graph, 
            vw: Vertex=>String = defaultVW, 
            ew: Edge=>String = defaultEW): String = {
    val header = "digraph G {\n"
    val vIdMap = createVMap(g)
    header + vListString(g, vIdMap, vw) + eListString(g,vIdMap, ew) + "}\n"
  }
}

