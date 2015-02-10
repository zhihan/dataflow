package me.zhihan.ir

import me.zhihan.ir._
import me.zhihan.se._
import me.zhihan.ir.defuse._
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

object DeadCode {

  def filterSimpleStatement(l: List[Statement],
		      toRemove: Set[Statement]):List[Statement] = {
    val out = ListBuffer[Statement]()
    l.foreach { s=>
      s match {
	case IfElse(e, b1, b2) => {
	  val b1Reduced = filterSimpleStatement(b1, toRemove)
	  val b2Reduced = filterSimpleStatement(b2, toRemove)
	  out += IfElse(e, b1, b2)
	}
	case _ => if (toRemove contains s) {} else { out += s} 
      }
    }
    out.toList
  }


  def getDeadAssignment(l: List[Statement],
			x: Var) = {
    val (seseG,map) = Utility.createCFGForList(l) 
    val p = DefUse.analyzeLive(seseG.graph, map, x)
    val dead = Set[Statement]()

    def deadAssign(outP:BooleanMapSet, v:Vertex, 
		   m:CFGMap) = {
      m.getStatements(v).forall( s =>
	s match {
	case Assignment(RefVar(l),_) => (l==x) && !outP(v)
	case _ => false
 	})
    }

    seseG.graph.V.foreach { v =>
      if (deadAssign(p.exit, v, map)) 
	dead ++= map.getStatements(v) else {}
    }
    dead
  }

  def removeDeadAssignment(l: List[Statement],
			   x: Var) = {
    val dead = getDeadAssignment(l, x)
    filterSimpleStatement(l, dead)
  }
}
