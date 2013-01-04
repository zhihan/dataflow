package my.ir
import my.se._
import scala.collection.mutable.Map

class CFGMap {
  // A vertex in the CFG correspond to a "block" which is 
  // a list of simple statement.
  val block = Map[Vertex, List[Statement]]()
  val vertex = Map[Statement, Vertex] ()

  def add(v:Vertex, s:Statement) {
    val st = List(s)
    block += v->st
    vertex += s->v
  }

  def add(v:Vertex, ss:List[Statement]) {
    block += v->ss
    ss.foreach( s=>
	       vertex += s->v)
  }

  def update(v:Vertex, ss:List[Statement]) {
    assert(block.contains(v))
    block(v) = ss
    
    ss.foreach( s => {
      assert(vertex.contains(s))
      vertex(s) = v
    })
  }
  
  def getVertex(s:Statement) = vertex(s)
  def getStatements(v:Vertex) = block(v)
  def getFirstStatement(v:Vertex) = block(v).head
 
}

object Utility {
  
  /* Create a control-flow graph for a given list of 
   * statements
   */ 
  def copyGraph(from:Graph, to:Graph) = {
    val m = Map[Vertex,Vertex]()
    from.V.foreach { 
      v => 
	val vCopy = to.newVertex(v.sid)
	m += (v -> vCopy)
    }
    from.E.foreach {
      e => 
	val fromCopy = m(e.from)
	val toCopy = m(e.to)
	to.addEdge(fromCopy, toCopy)
    }
    m
  }

  def createAndEmbedBlock(l:List[Statement],
			  g:Graph, 
			  m:CFGMap,
			  lastEntry:Vertex ) = {
    val (g1, m1) = createCFGForList(l)
    val copyMap = copyGraph(g1.graph, g)
    copyMap.foreach{
      e =>
	m.add( e._2,  m1.getStatements(e._1))
    }
    g.addEdge(lastEntry, copyMap(g1.entry))
    copyMap(g1.exit)
  }
  
  def createCFGForList(l:List[Statement]):
  (SeseGraph, CFGMap) = {
    val g = new Graph()
    val entryV = g.newVertex("entry")
    val entryStmt = Noop("entry")
    
    var lastEntry = entryV
    val m = new CFGMap()
    m.add(entryV, entryStmt)

    for (stmt <- l) {
      stmt match {
	case IfElse(_, b1, b2) =>{
	  val e1 = createAndEmbedBlock(b1, g, m, lastEntry)
	  val e2 = createAndEmbedBlock(b2, g, m, lastEntry)
	  val exitV = g.newVertex("merge")
	  val exitS = Noop("merge")
	  m.add(exitV, exitS)
	  g.addEdge(e1, exitV)
	  g.addEdge(e2, exitV)
	  lastEntry = exitV
	}
	  
	case _ => {
	  val currentV = g.newVertex("")
	  m.add(currentV, stmt)
	  g.addEdge(lastEntry, currentV)
	  lastEntry = currentV
	}
      }
    }

    val exitV = g.newVertex("exit")
    g.addEdge(lastEntry, exitV)
    val exitStmt = Noop("exit")

    m.add(exitV, exitStmt)

    val result = new SeseGraph(g, entryV, exitV)
    (result, m)
  }
}

