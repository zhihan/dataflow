package my.ir.defuse

import scala.collection.mutable.Map
import my.se._ // Graph
import my.ir._ // IR


class BooleanMapSet (mval : Map[Vertex,Boolean]) {
// A BooleanMapSet is a map from vertex to Boolean.
// The vertex is a vertex in the control flow graph.
// 
// Instead of using bitvectors, here we use a map from
// vertices to Booleans for the same effects.

  val m = mval
  def apply(v:Vertex) = m(v)
  def size = {
    val b = m.filter(_._2)
    b.size
  }
}

object BooleanMapSet {
  // Factory method for creating some essential predicates

  def label(v: Vertex, p: BooleanMapSet) = 
    if (p(v)) "[label='*']" else "" 
  
  def newSet(s:BooleanMapSet) (v:Vertex, b:Boolean) = {
    new BooleanMapSet(s.m + (v->b))
  } 

  // Return an empty set
  def emptySet(V:List[Vertex]) = {
    val addOne = (m : Map[Vertex,Boolean], v : Vertex) => m + (v->false)
    val m = V.foldLeft (Map[Vertex,Boolean]()) (addOne)
    new BooleanMapSet(m)
  }
  
  // Return a set of used variables for a 
  def usedSet(g: Graph, s: CFGMap, variable: Var) = {
    def isUsed = (x: Var, stmt: Statement) => 
      stmt match {
	case Assignment(v,e) => VarUse(e).exists(_ == x)
	case IfElse(e, _, _) => VarUse(e).exists(_ == x)
	case _ => false 
      }
    
    def isUsedIn(x:Var, ss:List[Statement]) = {
      ss.exists( s => isUsed(x, s))
    }
    
    def addIf = (m: Map[Vertex,Boolean], v : Vertex) => 
      m + (if (isUsedIn(variable, 
		      s.getStatements(v)))  v -> true else  v-> false )

    val m = g.V.foldLeft (Map[Vertex,Boolean]()) (addIf)
    new BooleanMapSet(m)
  }

  def modifiedSet(g: Graph, s: CFGMap, variable: Var) = {
    def isModified = (x: Var, stmt: Statement) => 
      stmt match {
	case Assignment(RefVar(v),_) => v == x
	case _ => false 
      }
    
    def isModifiedIn(x:Var, ss:List[Statement]) = {
      ss.exists( s => isModified(x, s))
    }

    def addIf = (m: Map[Vertex,Boolean], v : Vertex) => 
      m + (if (isModifiedIn(variable, 
			  s.getStatements(v))) { v -> true } else { v-> false} )

    val m = g.V.foldLeft (Map[Vertex,Boolean]()) (addIf)
    new BooleanMapSet(m)
  }

  // Default factory method creates the default four predicates for dataflow analysis
  def apply(cfg: Graph, 
	    s: CFGMap,
	    x: Var) = {
    val used = usedSet(cfg, s, x)
    val modified = modifiedSet(cfg, s, x)
    val entry = emptySet(cfg.V.toList)
    val exit = emptySet(cfg.V.toList)

    /*
    println(x.name + " used")
    println(writeGraphviz(cfg, (v => BooleanMapSet.label(v, used))))
    println(x.name + " modified")
    println(writeGraphviz(cfg, (v => BooleanMapSet.label(v, modified))))
    */

    new DataflowSets(used, modified,entry,exit)
  }
}

//
class DataflowSets( u: BooleanMapSet, m: BooleanMapSet,
		   e: BooleanMapSet, x: BooleanMapSet) {
  val used = u
  val modified = m
  val entry = e
  val exit = x
}

object DefUse {
  def lfp(p: DataflowSets, 
	  g: Graph, 
	  op: (DataflowSets, Vertex, Graph)=> 
	    (Boolean, DataflowSets) ) = {
	      var cont = true
	      var loopP = p 

	      while(cont) {
		cont  = false // Assume no progress
		for (v <- g.V) {
		  val result = op(loopP, v, g) //Iterate
		  cont = cont || result._1
		  loopP = result._2
		}

	      }
	      loopP
	    }
  
  def live(p: DataflowSets, v:Vertex, g:Graph) = {
    val entry_live = p.entry
    val exit_live = p.exit
    val used = p.used
    val modified = p.modified

    val entry_live_v = used(v) || (exit_live(v) && (!modified(v)))

    var exit_live_v = false
    for (next <- g.succ(v)) {
      exit_live_v = exit_live_v || entry_live(next)
    }

    val changed = (exit_live_v != exit_live(v)) || (entry_live_v != entry_live(v))
    val new_exit_live = BooleanMapSet.newSet(exit_live) (v, exit_live_v)
    val new_entry_live = BooleanMapSet.newSet(entry_live) (v,entry_live_v)

    (changed, new DataflowSets(used, modified, new_entry_live, new_exit_live))

  }

  def analyzeLive(cfg: Graph, 
		  s: CFGMap,
		  x: Var) = {
    val p = BooleanMapSet(cfg,s,x)
    lfp(BooleanMapSet(cfg,s,x), cfg, live)
  }
}
