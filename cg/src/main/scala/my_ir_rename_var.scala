package me.zhihan.ir

import me.zhihan.ir._
import scala.collection.mutable.Map

object RenameVar{
  def apply (m: Map[Var, Var], e:Exp) :Exp = {
    e match {
      case Ref(v) => if (m contains v) Ref(m(v)) else Ref(v)
      case UniExp(op, e) => UniExp(op, apply(m, e))
      case Function(name, args) => Function(name, 
                                            args.map(x=> apply(m, x)))
      case BinExp(op, lhs, rhs) => BinExp(op, 
					  apply (m, lhs), 
					  apply (m, rhs))
      case Deref(v) => if (m contains v) Deref(m(v)) else Deref(v)
      case Const(_)  => e 
    }
  }

  def apply( m:Map[Var,Var], lhs:LRef): LRef = {
    lhs match {
      case RefVar(v) => if (m contains v) RefVar(m(v)) else lhs
      case RefArray(v,i) => if (m contains v) RefArray(m(v), i) else lhs
    }
  }

  def apply(m:Map[Var,Var], s:Statement) :Statement = {
    s match {
      case Assignment(lref, e) => Assignment(apply(m,lref), 
					     apply(m,e))
      case IfElse(e, b1, b2) => IfElse(apply(m,e), 
				       apply(m,b1), 
				       apply(m,b2))
      case While(e, b) => While(apply(m, e),
				apply(m, b))
      case Noop(_) => s
      case Call(Function(n, args)) => 
	Call(Function(n, args.map(x => apply(m,x))))
    }
  }

  def apply(m:Map[Var,Var], l:List[Statement]) : List[Statement] = {
    l.map(s => apply(m, s)) 
  }
  
  // For all variables in the map (x -> y), generate the list of
  // assignments.
  //  y = x;
  def copyTo(m:Map[Var,Var]) = {
    m.map( kv =>
      Assignment(RefVar(kv._2),Deref(kv._1)) 
      ).toList
  }

  // For all variables in the map ( x->y) generate the list of 
  // assignments, e.g., 
  //  x = y;
  def copyFrom(m:Map[Var,Var]) = {
    m.map( kv =>
      Assignment(RefVar(kv._1),Deref(kv._2))).toList
  }
  
}

object RenameVarInFunctionArgs {
  // Rename a var in function arguments
  def apply(f: Function, varMap: Map[Var,Var]) = {
    val newArgs = for (a <- f.args) yield RenameVar(varMap, a)
    Function(f.name, newArgs)
  }
}
