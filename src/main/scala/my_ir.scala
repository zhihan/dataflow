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
case class OpMinus() extends BinaryOperator
{
  def isMinus() = true
}
case class OpDivide() extends BinaryOperator
{
  def isDivide() = true
}
case class OpLt() extends BinaryOperator
{
  def isLt() = true
}
case class OpGt() extends BinaryOperator
{
  def isGt() = true
}
case class OpLe() extends BinaryOperator
{
  def isLe() = true
}
case class OpGe() extends BinaryOperator
{
  def isGe() = true
}
case class OpEq() extends BinaryOperator
{
  def isEq() = true
}

sealed abstract class UnaryOperator
case class OpNeg() extends UnaryOperator 
{
  def isNeg() = true
}

/* Variable
 * A variable has a name and an id (integer) 
 */
sealed case class Var(varname: String, varid: Int) {
  val name = varname
  val id = varid
  
  // Implement getName to use with StringTemplate
  // For property getter of "p", Scala generates "p()", but
  // ST looks for "getP()"
  def getName() = name
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
case class TInt() extends BaseType
{
  def isInt() = true
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
case class IntValue(v:Int) extends BaseValue
{
  def getVal() = v
  def isIntValue() = true
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
case class UniExp(op:UnaryOperator, e:Exp) extends Exp 
{
  def getOp() = op
  def getE() = e
  def isUniExp() = true
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

case class Function(name:String, args:List[Exp]) extends Exp
{
  def getName() = name
  def getArgs() = args.toArray
  def isFunction = true
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

case class Call(f:Function) extends Statement
{
  def isCall() = true
  def getFunction() = f
}

case class Procedure(body: List[Statement], name:String)
{
  def getName = name
  def getBody = body.toArray
  def isProcedure = true
}

