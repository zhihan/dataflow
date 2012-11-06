import java.util.ArrayList
import java.util.Arrays
import scala.collection.mutable.Queue
import scala.collection.immutable.Set
import scala.collection.JavaConversions._ 

class Edge(_id: Int,  _from:Vertex, _to:Vertex) {
  var active = true
  val from = _from
  val to = _to
}

class Vertex(_id: Int, s: Array[String]) {
  val id = _id
  val sid = s

  // Mutable data members
  var _succ = new ArrayList[Edge] ()
  var _pre = new ArrayList[Edge] ()

  var mark1 = false

  // Access methods
  def pre()  = _pre.map(e => e.from)
  def succ() = _succ.map(e => e.to)

  def activePre() = {
    new ArrayList[Vertex](_pre.filter(e => e.active).map(e => e.from))
  }

  def activeSucc() = 
    new ArrayList[Vertex](_succ.filter(e => e.active).map(e => e.to))

  def addSucc(v: Vertex) {
    val id = _succ.size;
    _succ.add(new Edge(id, this, v))
  }

  def addPre(v: Vertex) {
    val id = _pre.size;
    _pre.add(new Edge(id, v, this))
  }

  def disableIn(id: Int) {
    val e = _pre.get(id)
    e.active = false 
  }

  def resetIn() {
    _pre.foreach(e => e.active = true)
  }
}

class Graph(v: ArrayList[Vertex] ) {
  val V = v


  def addEdge(src:Vertex, dst:Vertex) {
    src.addSucc(dst)
    dst.addPre(src)
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
        v.succ().foreach(n => println(v.id + " -> " + n.id))
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

        v.succ().foreach(n => 
          result = result + 
            v.id + " -> " + n.id + "\n")
      }
    )
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

  def disableIn(which:Int, idx:Int) {
    getV(which).disableIn(idx)
  }

  def resetActive() {
    V.foreach( v => v.resetIn() )
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

class Reachable(graph: Graph) {
  val next = (v:Vertex)=> v.activeSucc()
  val prev = (v:Vertex)=> v.activePre()

  def forward(start:Vertex):Array[Vertex] = {
    val bfs = new BFS(graph.V, 
                      next)
    
    bfs.initialize(start)
    bfs.run()
    
    val result = for ( v <- graph.V if v.mark1 == bfs.mark1) 
                 yield v
    result.toArray
    
  }

  def forward(start: Vertex, in: Array[Int]): Array[Vertex] = {
    val inactive  = in.toSet
    val bfs = new BFS(graph.V, 
                      (v:Vertex) => 
                      new ArrayList[Vertex] (
			v.succ().filter( e=> !inactive.contains(e.id))
		      )
                     )
    
    bfs.initialize(start)
    bfs.run()
    
    val result = for ( v <- graph.V if v.mark1 == bfs.mark1) 
                 yield v
    result.toArray
    
  }

  def forward(start:Int, in:Array[Int]):Array[Vertex] = 
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

  def backward(start: Int, in: Array[Int]) = {
    val inactive  = in.toSet
    val bfs = new BFS(graph.V, 
                      (v:Vertex) => 
                        new ArrayList[Vertex] (
			  v.pre().filter( e=> !inactive.contains(e.id))
			  )
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
  def backward(start: Array[Int], in: Array[Int]) = {
    val inactive  = in.toSet
    val bfs = new BFS(graph.V, 
                      (v:Vertex) => 
                        new ArrayList[Vertex] (
			  v.pre().filter( e=> !inactive.contains(e.id))
			  )
                     )
    
    bfs.initialize(graph.getV(start))
    bfs.run()
    
    val result = for ( v <- graph.V if v.mark1 == bfs.mark1) 
                 yield v
    
    val vertices = result.toArray
    vertices.map(v => v.id)
  }

}



          
