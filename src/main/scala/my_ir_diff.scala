package my.ir.diff
import my.ir._

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

// Expression auto differentiation
class Diff(dvar : Map[Var,Var]) {
  val dv = dvar
  def applyBinExp(e:BinExp) = e match {
    case BinExp(OpMul(), lhs, rhs) => BinExp(OpPlus(), 
                                             BinExp(OpMul(), 
						    apply(lhs), 
						    rhs), 
                                             BinExp(OpMul(), 
						    lhs, 
						    apply(rhs))
                                           )
    case BinExp(OpPlus(), lhs, rhs) => BinExp(OpPlus(), 
					      apply(lhs), 
					      apply(rhs))
    case BinExp(OpMinus(), lhs, rhs) => BinExp(OpMinus(), 
					      apply(lhs), 
					      apply(rhs))
    case BinExp(OpDivide(), lhs, rhs) => 
      BinExp(OpPlus(),
             BinExp(OpDivide(),
                    apply(lhs),
                    rhs),
             BinExp(OpMul(),
                    BinExp(OpDivide(),
                           lhs,
                           BinExp(OpMul(),
                                  rhs, rhs)),
                    apply(rhs)))
    case BinExp(OpLt(), lhs, rhs) => Const(FloatValue(0.0)) 
    
  }

  def apply(e: Exp): Exp = e match {
    case Deref(v) => Deref(getDv(v))
    case Const(_)  => Const(FloatValue(0.0))
    case Ref(v) => e 
    case BinExp(_,_,_) => applyBinExp(e.asInstanceOf[BinExp])
    case Function(fcn, args) => 
      fcn match {
        case "sin" => Function("cos", args)
        case "cos" => UniExp(OpNeg(), Function("sin",args))
        case _ => Const(FloatValue(0.0))
      }
    case UniExp(OpNeg(), e) => UniExp(OpNeg(), apply(e))
  }
  
  def getDv(v:Var) = if (dv.contains(v)) dv(v) else Var("d_" + v.name, 0)  
}

// Auto differentiation utility
object Diff {

  def diffStatements(l:List[Statement], diff:Diff) = 
    l.foldLeft(List[Statement]()) ( (l, s)=> l ::: diffStatement(s, diff))

  def diffStatement(s:Statement, diff:Diff):List[Statement] = {
    s match {
      case Assignment(RefVar(v), e)=> 
	List(s, Assignment(RefVar(diff.getDv(v)), diff(e)))
      case IfElse(e, b1, b2) => {
	val db1 = diffStatements(b1, diff)
	val db2 = diffStatements(b2, diff)
	List(IfElse(e, db1, db2))
      }
      
      case Noop(_) => List(s)
      case _ => List(s) // Don't know what to do yet

    }
  }
  
  // Apply auto differentiation to a function body
  def apply(l:List[Statement], sc:VarScope,
	    x:Var, p:List[Var]) = {
    val scope = sc.copy()

    // Create local var for all mod
    val localMap = Map[Var,Var]()
    val mod = VarDefine(l)
    mod.foreach{ s =>
      val sLocal = scope.newVar(s.name + "_j")
      localMap += (s -> sLocal)
    }
    val init = RenameVar.copyTo(localMap)

    val newX = if (localMap contains x) localMap(x) else x
    val newP = p.map( v => if (localMap.contains(v)) localMap(v) else v)
	    
    val renamedBody = RenameVar(localMap, l)

    // Create derivative var
    val dx = scope.newVar("d_" + newX.name)
    val dvar = Map(newX -> dx)
    val init_dx = Assignment(RefVar(dx), Const(FloatValue(1.0)))

    // Initialize du
    var init_du = ListBuffer[Statement]()
    newP.foreach{ u =>
      val du = scope.newVar("d_" + u.name)
      dvar += (u -> du)	      
      init_du += Assignment(RefVar(du), Const(FloatValue(0.0))) 
    }
    val diff = new Diff(dvar)

    // Differentiate the body
    val diffL = diffStatements(renamedBody, diff)
    
    // Assemble the statements together
    val body = init ::: (init_dx :: init_du.toList) ::: diffL 

    (body, scope)
  }

  def createDiffFunctions(l:List[Statement],
                          sc:VarScope,
                          vars:List[Var]) = 
  {
    for { x <- vars
         u = vars.filter (_ != x)
         (df, scope) = apply(l, sc, x, u) 
       }
    yield (df, scope, x, u)
    
  }

}
