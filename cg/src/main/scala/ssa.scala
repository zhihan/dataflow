package me.zhihan.ir

import me.zhihan.se._
import me.zhihan.ir._

import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Stack

import java.io._  // Debug

class SSA {
  def Phi(v:Var, 
	  length: Int) = {
    val args = (for (i <- 1 to length) yield Deref(v)).toList
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

  class RenameVar(val g:Graph, 
                  val m:CFGMap,
                  val idom: HashMap[Vertex,Vertex]) {
    // In this class we use id to refer to the 'original' var instead of 'Var'.
    // 

    val (origVar, stacks) = initStack()  // id -> Current stack of var
    val count = initCount()   // id -> Current count of var
    val newM = m.copy // Resulting new statements

    // Initialization
    private def initStack() = {
      val s = HashMap[Int, Stack[Var]]()
      val o = HashMap[Int, Var]() 
      // Only consider defined variables in the graph
      val vars = VarDefine(g,m)  
      for (v <- vars ) {
        // Push the original var onto the stack
        s(v.id) = Stack[Var](v)

        // Original var cannot have identical ids. 
        assert( !o.contains(v.id)) 
        o(v.id) = v
      }
      (o, s)
    }

    private def initCount() = {
      val c = HashMap[Int, Int]()
      // Only consider defined variables in the graph
      val vars = VarDefine(g,m)  
      for (v <- vars ) {
        // Push the original var onto the stack
        c(v.id) = 0
      }
      c
    }

    private def isPhiAssign(s: Statement) =  {
      s match {
        case Assignment(_,Function("phi", _)) => true
        case _ => false
      }
    }

    private def renameVarWithTopStack(s:Statement) = {
      // Rename the variables with the corresponding top
      // of the stack
      val replacement = for ( (k,v) <- stacks) yield (origVar(k) -> v.top)
      RenameVar(replacement, s)
    }

    private def renameDefinition(m:Map[Var,Var], s:Statement) = {
      s match {
        case Assignment(lhs, rhs)=> Assignment(RenameVar(m,lhs), rhs)
        // (XXX) handle arrays
        case _ => s
      }
    }

    private def renamePhiFunctionOperand(s:Statement, i:Int) = {
      s match {
        case Assignment(lhs,Function("phi", rhs)) => {
          val a = rhs(i)
          val newRhs:List[Exp] = a match {
            case Deref(v) => {
              rhs.updated(i, Deref(stacks(v.id).top))
            }
            case _ =>  {
              println(s)
              println(a)
              throw new RuntimeException("Phi function input is not var")
            }
          }
          Assignment(lhs, Function("phi", newRhs))
        }
        case _ =>s
      }
    }


    def visit(v:Vertex) {
      // Use the new statements here as the operands in the phi
      // functions may have been replaced previously

      val S = newM.getStatements(v)
      val newStatements = S.map( s => {
        // Rename use of variables
        val newS = if (!isPhiAssign(s)) {
          renameVarWithTopStack(s)
        } else {
          s 
        }

        // Rename variable defintion, including phi functions
        val varDefined = VarDefine(newS)
        val renameDefinedVar = Map[Var,Var]()
        varDefined.foreach{ a => 
          count(a.id) += 1
          val newVar = Var( origVar(a.id).name + count(a.id), a.id)
           stacks(a.id).push(newVar)
           renameDefinedVar += (a -> newVar)
         }
        renameDefinition(renameDefinedVar, newS) 
      })
      
      newM.update(v, newStatements)

      // Rename the phi function arguments in the successors
      val successors= g.succ(v)
      successors.foreach{y =>
         val j = g.inIndex(v, y)
         val S = newM.getStatements(y)
         // Rename the rhs of phi functions.
         newM.update(y, S.map(s => renamePhiFunctionOperand(s, j)))
                       }

      // Visit nodes dominated by v
      val children = g.V.filter(x => idom.contains(x) && idom(x) == v)
      children.foreach{ y=> visit(y)}
      
      // Recover stack
      S.foreach{ s=>
        val varDefined = VarDefine(s)
        varDefined.foreach { a =>
          stacks(a.id).pop
        }
      }
      
    }
    
  }
  def renameVar(graph:Graph,
                v: Vertex,
                m:CFGMap,
                idom: HashMap[Vertex,Vertex]) = {
    val r = new RenameVar(graph, m, idom)

    r.visit(v)
    
    r.newM
  }
                  

}
