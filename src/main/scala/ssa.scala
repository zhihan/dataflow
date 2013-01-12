package my.ir

import my.se._
import my.ir._

import scala.collection.mutable.Set
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class SSA {
  def Phi(v:Var, 
	  length: Int) = {
    val args = (for (i <- 1 to length) yield Ref(v)).toList
    Assignment(RefVar(v), Function("phi", args))
  }

  

  def insertPhi(graph:Graph, 
		m:CFGMap,
		df:HashMap[Vertex, Set[Vertex]]) = {
    // Compute the def sites of the given graph
    def computeDefSites(graph:Graph,
			m: CFGMap) = {

      val defSites = HashMap[Var, ArrayBuffer[Vertex]]()
      val variables = Set[Var]()
      graph.V.foreach{ v => 
	val defined = VarDefine(m.getStatements(v))
        variables ++= defined
        defined.foreach { x => 
	  if ( defSites.contains(x)){
	    defSites(x).append(v)
	  } else {
	    defSites(x) = ArrayBuffer(v)
	  }
		       }
		    }
      (defSites, variables)
    }
    
    val (defSites, variables) = computeDefSites(graph,m)
    val newM = m.copy
    val phiMap = HashMap[Vertex,ArrayBuffer[Var]]() ++ 
    (for (v <-graph.V) yield (v, ArrayBuffer[Var]()))
 
    variables.foreach{ a =>
      val w = defSites(a)
      while (! w.isEmpty) {
	val n = w.remove(0) // Remove head
	df(n).foreach{ y =>
	  if (!phiMap(y).contains(a)) {

	    // Insert a = phi(a, ...) into y 
	    val newAssign = Phi(a, graph.pre(y).length)
	    newM.insertFront(y, newAssign)
	    phiMap(y).append(a)

	    // Originally defined variables
	    val origDefined = VarDefine(m.getStatements(y))
	    if (!origDefined.contains(a)) {
	      // a is not originally defined in y
	      // it is defined by a phi function
	      w.append(y)
	    }
	  }
		    }
      }		      
    }
    (newM, phiMap)
  }
}
