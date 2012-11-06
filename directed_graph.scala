import java.util.ArrayList
import java.util.Arrays
import scala.collection.mutable.Queue
import scala.collection.immutable.Set
import scala.collection.JavaConversions._ 

class Edge(_id: Int,  _from:Vertex, _to:Vertex) {
  // Remove the mutable data member
  val id = _id
  val from = _from
  val to = _to
}

class Vertex(_id: Int, s: Array[String]) {
  val id = _id
  val sid = s

  // Mutable data members
  var _out = new ArrayList[Edge] ()
  var _in = new ArrayList[Edge] ()

  var mark1 = false

  def in() = _in
  def out() = _out

  // Access methods
  def pre()  = new ArrayList(_in.map(e => e.from))
  def succ() = new ArrayList(_out.map(e => e.to))

  def addOut(e: Edge) {
    _out.add(e)
  }

  def addIn(e: Edge) {
    _in.add(e)
  }

}

class Graph(v: ArrayList[Vertex] ) {
  val V = v
  val E = new ArrayList[Edge]

  def addEdge(src:Vertex, dst:Vertex) {
    val id = E.size()
    val e = new Edge(id, src, dst)
    E.add(e)
    src.addOut(e)
    dst.addIn(e)
  }

  def allSID(sids: Array[String]) = {
    sids.foldLeft ("")((a,b)=> a+b)
  }

  def print() {
    println(" digraph G {")
    V.foreach( v => 
      {
        println(v.id + "[label=\"" + v.id + 
                " sid:"+ allSID(v.sid) +"\"]")
        v.out.foreach(e => println(e.from.id + " -> " + e.to.id))
       }
            )
    println(" } ") 
  }
  
  def toDotString() = {
    var result = " digraph G {\n"
    result += "graph [rankdir=\"LR\"]\n"
    V.foreach( v => 
      {
        result = result + v.id + 
          "[label=\"" + v.id + 
          " sid:"+ allSID(v.sid) +"\"]\n"
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
    val e = vFrom.out().find( e => e.to.id ==to)
    e match {
      case Some(x) => x.id
      case None => throw new RuntimeException("Cannot edge to" + to)
    }
  }

}

// Alternative constructor for Graph
class GraphFactory() {
  def make(v: Array[Vertex]) = {
    def convertVList(v:Array[Vertex]) = {
      val vList = new ArrayList[Vertex]()
      v.foreach( e => vList.add(e)) 
      vList
    }
    
    new Graph(convertVList(v))
  }
}

class BFS(V: ArrayList[Vertex], 
          visit: Vertex => ArrayList[Vertex]){
  // Immutable members
  val callBack = visit
  val vertices = V
  var mark1 = false

  // Mutable members
  var q = Queue[Vertex]()

  private def clearVisit() {
    V.foreach(v => v.mark1 = mark1 )
  }
  def initialize(e: Vertex) {
    clearVisit()
    q.enqueue(e)
    mark1 = e.mark1
  }

  def initialize(e: Array[Vertex]) {
    clearVisit()
    e.foreach(x => q.enqueue(x))
  }


  def run() {
    mark1 = !mark1
    while (! q.isEmpty() ) {
      val e = q.dequeue()
      if (e.mark1 != mark1) {
        // Not visited before
        val next = callBack(e)
        e.mark1 = mark1
        for (c <- next if (c.mark1 != mark1))
          q.enqueue(c)
      }
    }
  }
}

class Inactive(vArray: Array[Int], eArray:Array[Int])
{
  val v = if (vArray != null ) vArray.toSet else Set[Int]()
  val e = if (eArray != null ) eArray.toSet else Set[Int]()
}
          

class Reachable(graph: Graph) {
  val next = (v:Vertex)=> v.succ()
  val prev = (v:Vertex)=> v.pre()

  def forward(start:Vertex):Array[Vertex] = {
    val bfs = new BFS(graph.V, 
                      next)
    
    bfs.initialize(start)
    bfs.run()
    
    val result = for ( v <- graph.V if v.mark1 == bfs.mark1) 
                 yield v
    result.toArray
    
  }

  private def filteredNext(v:Vertex, inactive:Inactive) = {
    val out = v.out()
    val filteredOut = out.filter(e => !inactive.e.contains(e.id))
    val filteredSucc = filteredOut.map(e => e.to)
    val r = filteredSucc.filter(v => !inactive.v.contains(v.id))
    new ArrayList[Vertex](r)
  }

  def forward(start: Vertex, inactive: Inactive): Array[Vertex] = {
    val bfs = new BFS(graph.V, 
                      (v:Vertex)=> filteredNext(v, inactive))
    
    bfs.initialize(start)
    bfs.run()
    
    val result = for ( v <- graph.V if v.mark1 == bfs.mark1) 
                 yield v
    result.toArray
    
  }

  def forward(start:Int, in:Inactive):Array[Vertex] = 
    forward(graph.getV(start), in)


  def backward(start:Int) = {
    val bfs = new BFS(graph.V, 
                      prev)
    
    bfs.initialize(graph.getV(start))
    bfs.run()
    
    val result = for ( v <- graph.V if v.mark1 == bfs.mark1) 
                 yield v
    val vertices = result.toArray
    vertices.map(v => v.id)
  }


  private def filteredPrev(v:Vertex, inactive:Inactive) = {
    val out = v.in()
    val filteredOut = out.filter(e => !inactive.e.contains(e.id))
    val filteredSucc = filteredOut.map(e => e.from)
    val r = filteredSucc.filter(v => !inactive.v.contains(v.id))
    new ArrayList[Vertex](r)
  }

  def backward(start: Int, inactive: Inactive) = {
    val bfs = new BFS(graph.V, 
                      (v:Vertex) => 
		       filteredPrev(v, inactive)
                     )
    
    bfs.initialize(graph.getV(start))
    bfs.run()
    
    val result = for ( v <- graph.V if v.mark1 == bfs.mark1) 
                 yield v
    val vertices = result.toArray
    vertices.map(v => v.id)
    
  }

  def backward(start: Array[Int]) = {
    val bfs = new BFS(graph.V, 
                      prev)
    
    bfs.initialize(graph.getV(start))
    bfs.run()
    
    val result = for ( v <- graph.V if v.mark1 == bfs.mark1) 
                 yield v
    val vertices = result.toArray
    vertices.map(v => v.id)
    
  }

  def backward(start: Array[Int], inactive:Inactive) = {
    val bfs = new BFS(graph.V, 
                      (v:Vertex) => 
                        filteredPrev(v, inactive))
    
    bfs.initialize(graph.getV(start))
    bfs.run()
    
    val result = for ( v <- graph.V if v.mark1 == bfs.mark1) 
                 yield v
    
    val vertices = result.toArray
    vertices.map(v => v.id)
  }

}

