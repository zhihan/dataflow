// My IR is a simple intermediate representation adapted from
// CGIR and DVIR.

package my.ir

import scala.collection.mutable.Set
import my.utility._ // Gensym

// Binary operations 
// Every case class needs to implements 'is' method
// to facilitate stringtemplate.
sealed abstract class BinaryOperator 
case class OpMul() extends BinaryOperator
{
  def isMul() = true
}
case class OpPlus() extends BinaryOperator
{
  def isPlus() = true
}
case class OpLt() extends BinaryOperator
{
  def isLt() = true
}

/* Variable
 * A variable has a name and an id (integer) 
 */
sealed class Var(varname: String, varid: Int) {
  val name = varname
  val id = varid
  
  // Implement getName to use with StringTemplate
  // For property getter of "p", Scala generates "p()", but
  // ST looks for "getP()"
  def getName = name
}

// Factory method for Var
object Var {
  def apply(varname: String, vid: Int) = new Var(varname, vid)
}


// Base type system
sealed abstract class BaseType 
case class TBool() extends BaseType
{
  def isBool() = true
}
case class Tfloat() extends BaseType
{
  def isFloat() = true
}

sealed abstract class DataType
case class Tbase(baseType:BaseType) extends DataType
{
  def isBase() = true
  def getBaseType() = baseType
}
case class Tarray(base:BaseType, n:Int) extends DataType
{
  def isArray() = true
  def getBaseType = base
  def getN() = n
}

sealed abstract class BaseValue 
case class BoolValue(v:Boolean) extends BaseValue
{
  def getVal = v
  def isBoolValue = true
}
case class FloatValue(v:Double) extends BaseValue
{
  def getVal() = v
  def isFloatValue() = true
}

sealed abstract class Exp

case class Ref(v:Var) extends Exp
{
  def getVar() = v
  def isRef() = true
}
case class Deref(v: Var) extends Exp
{
  def getVar() = v
  def isDeref() = true
}
case class BinExp(op:BinaryOperator,l:Exp, r:Exp) extends Exp
{
  def getOp() = op
  def getLhs() = l
  def getRhs() = r
  def isBinExp() = true
}
case class Const(value:BaseValue) extends Exp
{
  def getValue() = value
  def isConst() = true
}

// Anything appearing as LHS of assignment
sealed abstract class LRef
case class RefVar(v: Var) extends LRef
{
  def isRefVar() = true
  def getVar() = v
}
case class RefArray(v: Var, i: Int) extends LRef
{
  def isRefArray() = true
  def getVar() = v
  def getIndex() = i
}

sealed abstract class Statement
case class Assignment(l:LRef, e:Exp) extends Statement
{
  // String template hooks
  def getLhs() = l  
  def getExpr() = e
  def isAssignment() = true
}

case class Noop(s:String) extends Statement 

case class IfElse(e:Exp, b1:List[Statement], b2:List[Statement]) extends Statement
{
  def isIfElse() = true
  def getCond() = e
  def getB1() = b1.toArray
  def getB2() = b2.toArray
}

case class While(e:Exp, body:List[Statement]) extends Statement
{
  def isWhile() = true
  def getCond() = e
  def getBody() = body.toArray
}

object VarUse {
  // VarUse function 
  def apply(e:Exp):List[Var] =  {
    e match {
      case Ref(v) => List( v )
      case Deref(v) => List ( v ) 
      case BinExp(_, lhs, rhs) => apply(lhs) ::: apply(rhs)
      case Const(_) => List[Var]()
    }
  }
}

object VarDefine {
  def apply(s:Statement):List[Var] = {
    s match {
      case Assignment(RefVar(x), _) =>
	List(x)
      case IfElse(_,b1,b2) => {
	val s = Set[Var]() ++ apply(b1)
	s ++= apply(b2)
	s.toList
      }
      case _ => List()
    }
  }
  def apply(l:List[Statement]):List[Var] = {
    val s = Set[Var]()
    l.foreach{ st => 
      s ++= apply(st) } 
    s.toList
  }
}

case class Procedure(body: List[Statement], name:String)
{
  def getName = name
  def getBody = body.toArray
  def isProcedure = true
}

