package my.ir
import my.se._
import scala.collection.mutable.Map
import scala.collection.mutable.Set

class CFGMap {
  // A vertex in the CFG correspond to a "block" which is 
  // a list of simple statement.
  val block = Map[Vertex, List[Statement]]()

  def copy(): CFGMap = {
    val a = new CFGMap()
    for ((k,v) <- block) {
      a.block += (k -> v)
    }
    a
  }

  def add(v:Vertex, s:Statement) {
    val st = List(s)
    block += v->st
  }

  def add(v:Vertex, ss:List[Statement]) {
    block += v->ss
  }

  def update(v:Vertex, ss:List[Statement]) {
    assert(block.contains(v))
    block(v) = ss
  }

  def insertFront(v:Vertex, s:Statement) {
    assert(block.contains(v))
    block(v) = s :: block(v)
  }

  def getStatements(v:Vertex) = block(v)
  def getFirstStatement(v:Vertex) = block(v).head
 
  def dump(): String = {
    val print = new Print("myir")
    "{" + (for ((k,v) <- block) yield (k.id.toString + "->" + 
                                       (for (elem <- v) yield print.Stmt(elem)).mkString(" "))).mkString("\n") + "}"
  }

  def apply() = "Error"

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
	  val ifV = g.newVertex("if")
	  g.addEdge(lastEntry, ifV)
	  m.add(ifV, stmt)

	  val e1 = createAndEmbedBlock(b1, g, m, ifV)
	  val e2 = createAndEmbedBlock(b2, g, m, ifV)
	  val exitV = g.newVertex("merge")
	  val exitS = Noop("merge")
	  m.add(exitV, exitS)
	  g.addEdge(e1, exitV)
	  g.addEdge(e2, exitV)
	  lastEntry = exitV
	}
	  
	case While(_, b) => {
	  // Create head node at top, this way 
	  // the while block only branches, and the header
	  // node merges two branches, add an additional
	  // loop exit node for loop exit
	  val headV = g.newVertex("whileHead")
	  val headS = Noop("whileHead")
	  g.addEdge(lastEntry, headV)
	  m.add(headV, headS)

	  val whileV = g.newVertex("while")
	  g.addEdge(headV, whileV)
	  m.add(whileV, stmt)
	  
	  val loopEnd = createAndEmbedBlock(b, g, m, whileV)
	  g.addEdge(loopEnd, headV)
	  
	  val exitV = g.newVertex("whileExit")
	  val exitS = Noop("whileExit")
	  m.add(exitV, exitS)
	  g.addEdge(whileV, exitV)
	  lastEntry = exitV
	}

	case Noop(_) => {
	  val currentV = g.newVertex("")
	  m.add(currentV, stmt)
	  g.addEdge(lastEntry, currentV)
	  lastEntry = currentV
	}

	case Assignment(_, _) => {
	  val currentV = g.newVertex("")
	  m.add(currentV, stmt)
	  g.addEdge(lastEntry, currentV)
	  lastEntry = currentV
	}
	case Call(_) => {
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

object VarUse {
  // VarUse function 
  def apply(e:Exp):List[Var] =  {
    e match {
      case Ref(v) => List( v )
      case Deref(v) => List ( v ) 
      case UniExp(_, e) => apply(e)
      case Function(_,e) => e.flatMap(x => apply(x))
      case BinExp(_, lhs, rhs) => apply(lhs) ::: apply(rhs)
      case Const(_) => List[Var]()
    }
  }

  def apply(s:Statement):List[Var] = {
    s match {
      case Assignment(_, e) => apply(e)
      case IfElse(_,_,_) => List()
      case While(_,_) => List()
      case Noop(_) => List()
      case Call(Function(_,args)) => args.flatMap(x => apply(x)) 
    }
  }

  def apply(l:List[Statement]):List[Var] = {
    val vars = Set[Var]()
    l.foreach{ st => 
      vars ++= apply(st) } 
    vars.toList
  }
  
  def apply(g:Graph, m:CFGMap):List[Var] = {
    val vars = Set[Var]()
    g.V.foreach{ v => 
      vars ++= apply(m.getStatements(v))}
    vars.toList
  }

}

object VarDefine {
  // The list of variables defined in the statement
  // corresponding to CFG vertices.
  //
  // NOTE
  // For if-statement and while-statement return empty
  // because their body is in the CFG.
  //
  def apply(s:Statement):List[Var] = {
    s match {
      case Assignment(RefVar(x), _) =>
	List(x)
      case Assignment(RefArray(a,_), _) =>
	List(a)
      case IfElse(_, b1, b2) => {
	List()
      }
      case While(_,_) => List()
      case Noop(_) => List()
      case Call(_) => List()
    }
  }
  def apply(l:List[Statement]):List[Var] = {
    val s = Set[Var]()
    l.foreach{ st => 
      s ++= apply(st) } 
    s.toList
  }

  def apply(g:Graph, m:CFGMap): List[Var] = {
    val vars = Set[Var]()
    g.V.foreach{ v => 
      vars ++= apply(m.getStatements(v))}
    vars.toList
  }

}

object CFGPrint {
  val p = new Print("myir")
  private def vLabel(v: Vertex, m:CFGMap) = {
    val statements = m.getStatements(v)
    val stmtStr = statements.map( s=> s match {
      case While(e,_) => p.Exp(e)
      case IfElse(e,_,_) => p.Exp(e)
      case _=> 
      {
	val sstr = p.Stmt(s)
	val s1 = sstr.replace("\r", "")
	s1.replace("\n", "\\l")
      }	
    })
    
    "[shape=\"box\"]" +
    "[label=\"" + stmtStr.mkString("\\l") + "\\l\"]"
  }

  def apply(g:Graph, m:CFGMap):String =
    writeGraphviz(g, { v:Vertex => vLabel(v,m) })

  def apply(g:SeseGraph, m:CFGMap):String = 
    apply(g.graph, m)
}

