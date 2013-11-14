package my.se

import java.util.Arrays

import scala.collection.mutable.Queue
import scala.collection.mutable.BitSet
import scala.collection.mutable.Set
import scala.collection.mutable.ArrayBuffer

import scala.collection.JavaConversions._ 
import scala.collection.mutable.HashMap

import scala.collection.mutable.Stack

abstract class LabelOp[Label] {
  type T = Label
  def lessThan(l:Label, r:Label):Boolean
  def max(l:Label, r:Label):Label
}

abstract class SetOp[SetType] {
  type T = SetType
  def isSubset(l:SetType, r:SetType): Boolean
  def union(l:SetType, r:SetType): SetType
  def empty(n:SetType): SetType
}

/** Vertex class consists of an id, a string for sid
 * and in- out- edges. The edges are ArrayBuffer, which are
 * mutable data members.
 */
class Vertex(val id: Int, val sid: String) {
  def deepCopy = new Vertex(id, sid)
}

/* Edge class consists of id, and the two end vertices
 */
class Edge(val id: Int,  val from:Vertex, val to:Vertex) 

/*
 * Graph class implements a simple graph using edgelist.
 */
class Graph() {
  
  var V = ArrayBuffer[Vertex]()
  val E = ArrayBuffer[Edge]()

  var id = 0
  var eid = 0

  def newVertex(name:String) = {
    id += 1
    val v = new Vertex(id, name)
    V  += v
    v
  }

  def addEdge(src:Vertex, dst:Vertex):Edge = {
    eid += 1
    val e = new Edge(eid, src, dst)
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

  def inIndex(src:Vertex, v:Vertex) = {
    // This function makes the assumption that every call of inE returns the edges
    // in a fixed order.
    val in = inE(v)
    val e = in.find(_.from == src)
    in.indexOf(e.get)
  }

  def hasSelfLoop(v:Vertex) = succ(v).contains(v)
  
  def getSelfLoop(v:Vertex) = outE(v).filter( e => e.to == v)

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
        "[label=\"" + 
         v.sid +"\"]\n"
      })
    
    E.foreach( e =>
      result = result + 
	      e.from.id + " -> " + 
	      e.to.id + 
	      "[label=\"" + e.id + "\"]\n")
    result + " }\n" 
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
  
  def getE(which: Int) = {
    val e = E.find( x => x.id == which)
    e match {
      case Some(v) => v
      case None => throw new RuntimeException("Wrong id given")
    }
  }

  def hasE(from:Int, to:Int):Boolean = {
    val vFrom = getV(from)
    val e = outE(vFrom).find( e => e.to.id ==to)
    e match {
      case Some(x) => true
      case None => false
    }
  }

  def getEdge(from:Int, to:Int) = {
    val vFrom = getV(from)
    val e = outE(vFrom).find( e => e.to.id ==to)
    e match {
      case Some(x) => x
      case None => throw new RuntimeException(
        "Cannot find edge from " + from + " to " + to)
    }
  }

  def getEdges(from:Int, to:Int) = {
    ArrayBuffer[Edge]() ++ E.filter(e => (e.from.id ==from) && (e.to.id ==to)) 
  }

  def getEId(from: Int, to: Int) = getEdge(from, to).id
}

/* Inactive class implements a mechanism to filter nodes
 and edges in the BFS search.
 * */
class Inactive(vArray: Array[Int], eArray:Array[Int])
{
  val v = if (vArray != null ) BitSet() ++ vArray else BitSet()
  val e = if (eArray != null ) BitSet() ++ eArray else BitSet()
}

object Graph {
  def filteredPredecessor(g:Graph, v:Vertex, inactive:Inactive) = {
    val out = g.inE(v)
    val filteredOut = out.filter(e => !inactive.e.contains(e.id))
    val filteredSucc = filteredOut.map(e => e.from)
    filteredSucc.filter(v => !inactive.v.contains(v.id))
  }

  def filteredInEdges(g:Graph, v: Vertex, inactive:Inactive) = {
    g.inE(v).filter(e => !inactive.e.contains(e.id))
  }

  def filteredSuccessor(g:Graph, v:Vertex, inactive:Inactive) = {
    val out = g.outE(v)
    val filteredOut = out.filter(e => !inactive.e.contains(e.id))
    val filteredSucc = filteredOut.map(e => e.to)
    filteredSucc.filter(v => !inactive.v.contains(v.id))
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

  def deepCopy(g: Graph) = {
    val gCopy = new Graph()
    // Deep copy must maintain ids
    g.V.foreach( x => gCopy.V +=x.deepCopy) 
    g.E.foreach( e =>
      gCopy.E += new Edge(e.id, 
			  gCopy.getV(e.from.id), 
			  gCopy.getV(e.to.id))
 	      )
    gCopy.id = g.id
    gCopy
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

  // Run the DFS disregarding the visited flag
  def runAlways() {
    while ( !q.isEmpty() ) {
      val e = q.dequeue()
      val next = cb(e)
      visited.add(e)
      for (c <- next) q.enqueue(c)
    }
  }
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
    ArrayBuffer[Vertex]() ++ Graph.filteredSuccessor(graph, v, inactive)
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
    val r = Graph.filteredPredecessor(graph, v, inactive)
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
  
  private def guardedSuccessor(v:Vertex, stop:Set[Vertex]) = 
    if (stop contains v) ArrayBuffer[Vertex]() else graph.succ(v)

  private def guardedPredecessor(v:Vertex, stop:Set[Vertex]) =
    if (stop contains v) ArrayBuffer[Vertex]() else graph.pre(v)
  
  def backward(start:Array[Int], stop:Set[Vertex]) = {
    val bfs = new BFS((v:Vertex) => guardedPredecessor(v, stop))

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

// Abstract class that propagate sets in the dataflow graph
class PropagateSet[SetT](graph: Graph,
			 op: SetOp[SetT],
			 inactive: Inactive)
{
  val m = HashMap[Int,SetT]()

  private def visitBackward(v:Vertex):ArrayBuffer[Vertex] = {
    val pred = Graph.filteredPredecessor(graph, v, inactive)
    val next = ArrayBuffer[Vertex]()
    for ( i <- pred ) {
      // Before visiting the node, the set at i
      val current = m(v.id)
      val before = m.getOrElse(i.id, op.empty(current))
      if (! op.isSubset(current, before)) {
	m(i.id) = op.union(current, before)
        next += i
      }
    }
    next
  }


  def backward(start:Array[Int], startE:Array[SetT]) = {
    val bfs = new BFS(visitBackward)
    // Initialize
    for ((v, s) <- start.zip(startE)) {
      m(v) = s
    }
    bfs.initialize(graph.getV(start))
    bfs.runAlways()
    m
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

  val defaultTreeNodeW: TreeNode=>String = {_=> ""}
  private def treeVisit(t: TreeNode,
                        vw: TreeNode=>String) 
  : String = {
    var result = t.id.toString() + vw(t) +"\n"
    
    t.children.foreach{ n => 
      result += t.id.toString() + 
                " -- " + 
                n.id.toString() + "\n"
    }
    t.children.foreach(n => result += treeVisit(n, vw) )
    result
    
  }
  def tree(t: TreeNode,
            vw: TreeNode=>String) = {
    " graph G {\n" + treeVisit(t,vw) + " } "
  }

}

class tarjan(val g: Graph) {
  // Work struct
  class TarjanWork() {
    var number = -1
    var lowlink = -1
  }

  def min(i:Int, j:Int) = if (i<j) i else j

  val nextF = (v:Vertex) => g.succ(v)
  val stack = Stack[Vertex]() // mutable
  val scc = ArrayBuffer[ArrayBuffer[Vertex]]() // mutable result
  var index = -1 // default value

  val work = (for (v <- g.V) yield (v, new TarjanWork())).toMap
  val parent = HashMap[Vertex, Vertex]()

  def visit(v:Vertex, callback: Vertex => Unit) {
    work(v).number = index
    work(v).lowlink = index
    index += 1

    stack.push(v)
    val next = nextF(v)
    for (i <- next) {
      if (work(i).number == -1) 
        { // never visited
          parent(i) = v
          visit(i, callback)
          work(v).lowlink = min(work(v).lowlink, work(i).lowlink)
        } 
      else if ( (work(i).number < work(v).number) && 
                 stack.contains(i)) 
        { // reset
          work(v).lowlink = work(i).lowlink
        }
    }
    callback(v)

  }

  def processSCC(v:Vertex) {
    // Process SCC computation
    if (work(v).lowlink == work(v).number) {
      // Finish up the node
      val c = ArrayBuffer[Vertex]()
      if (!stack.isEmpty && 
          stack.head == v) {
        // Only one element, need to check whether it has self-loop
        val x = stack.pop()
        if (g.hasSelfLoop(v)) {
          // The SCC contains only x
          c.append(v)
        } 
      } else {
        var break = false
        while (!stack.isEmpty && !break ) {
          val x = stack.pop()
          c.append(x)
          break = (x == v) // Stop if v is reached
        }
      }
      if (!c.isEmpty) {
        scc.append(c)
      }
    }
  }

  def computeSCC() = {
    index = 0
    for (i <- g.V if work(i).number == -1) { visit(i, processSCC)}
    scc
  }

  def dfNumber(root:Vertex) = {
    index = 0
    visit(root, (v:Vertex)=>())
    val dfnum = (for (v <- g.V) yield(v, work(v).number)).toMap
    (dfnum, parent)
  }
}

object tarjanSCC {
  def apply(g:Graph) = {
    val t = new tarjan(g)
    t.computeSCC()
  }
}

class TarjanDominators {
  // A class that wraps the Lengauer-Tarjan algorithm for dominators
  
  def compute(g:Graph, root:Vertex) = {
    val t = new tarjan(g)
    val (dfNum, parent) = t.dfNumber(root)
    // Compute a reverse map for vertices
    val vertex = (for (kv <- dfNum) yield (kv._2, kv._1)).toMap

    val semiDominated = HashMap[Vertex, ArrayBuffer[Vertex]]()
    for (i <- g.V) {
      semiDominated(i) = ArrayBuffer[Vertex]()
    }

    val idom = HashMap[Vertex, Vertex]()
    val samedom = HashMap[Vertex, Vertex]()
    val semi = HashMap[Vertex,Vertex]()

    // ancestor is a mutable version of parent. It builds up the 
    // spanning tree bottom-up. 
    val ancestor = HashMap[Vertex, Vertex]() 

    for (i <- (g.V.length-1) to 1 by -1) {
      val n = vertex(i)
      val p = parent(n)
      val s = computeSemiDominator(n, p, 
                                   semi, ancestor,
                                   dfNum, g)
      semi(n) = s
      semiDominated(s).append(n)
      
      // Link nodes by adding ancestor relations.
      // A node is linked once its semi[] is computed
      ancestor(n) = p

      // All vertices under p has been visited.
      for (v <- semiDominated(p)) {
        val y = ancestorWithLowestSemi(v, semi,
                                       dfNum, ancestor)
        if (semi(y) == semi(v)) {
          idom(v) = p
        } else {
          // Defer to computation of y
          samedom(v) = y
        }
      }
    }
    
    for (i <- 1 to g.V.length-1) {
      val n = vertex(i)
      if (samedom.contains(n)) {
        idom(n) = idom(samedom(n))
      }
    }
    idom
  }

  def ancestorWithLowestSemi(v: Vertex, 
                             semi: HashMap[Vertex,Vertex],
                             dfNum: Map[Vertex,Int],
                             ancestor: HashMap[Vertex,Vertex]) = {
    
    var currentSemi = v 
    var currentV = v    
    while (semi.contains(currentV))  {
      val semiV = semi(currentV)
      val semiU = semi(currentSemi)
      if (dfNum(semiV) < dfNum(semiU)) {
        currentSemi = currentV
      }
      currentV = ancestor(currentV)
    }
    currentSemi
  }
  
  def link(p:Vertex, n:Vertex, ancestor: HashMap[Vertex,Vertex]) {
    ancestor += n->p
  }

  def computeSemiDominator(n: Vertex, 
                           p: Vertex,
                           semi: HashMap[Vertex,Vertex],
                           ancestor: HashMap[Vertex,Vertex],
                           dfNum: Map[Vertex,Int],
                           g:Graph) = {
    var s = p 
    var pred = g.pre(n)
    for (v <- pred) {
      val s2 = 
        if (dfNum(v) <= dfNum(n)) {
          // v is in the dfg spanning tree to n
          v 
        } else {
          // v is on a cross edge to n
          val c = ancestorWithLowestSemi(v, semi, dfNum, ancestor)
          semi(c)
        }
      s = if (dfNum(s2) < dfNum(s)) s2 else s
    }
    s
  }
  
  def dominate(n: Vertex, w: Vertex, 
	       idom:HashMap[Vertex, Vertex]):Boolean = {
    if (!idom.contains(w)) {
      false
    }  else if (idom(w) == n) {
      true
    } else { 
      dominate( n, idom(w), idom)
    } 
  }

  class DominanceF(val g:Graph, val idom:HashMap[Vertex, Vertex]) {
    val df = HashMap[Vertex,Set[Vertex]]()
    for (v <- g.V) {
      df(v) = Set[Vertex]()
    }

    
    def compute(n: Vertex) {
      val s = Set[Vertex]()
      val succ = g.succ(n)
      for (y <- succ) {
	// Immediate successor is DF if 
	// it is not dmoniated by the vertex
	if (idom(y) != n) {
	  s += y
	}
      }
      for (c <- g.V if (idom.contains(c) && idom(c) == n)) {
	// n directly dominate c
	compute(c)
	for (w <- df(c) if (!dominate(n,w, idom) || n==w )) {
	  // if n does not dominate w, w is also a DF of c.
	  s += w
	}
      }
      df(n) = s
    }
  }
  def dominanceFrontier(n: Vertex, idom: HashMap[Vertex,Vertex],
		       g: Graph) = {
    val c = new DominanceF(g, idom)
    c.compute(n)
    c.df
  }
  
}
