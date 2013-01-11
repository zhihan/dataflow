import my.ir._
import org.scalatest.FunSuite

package my.ir.Test {
  class MyIRPrintSuite extends FunSuite {
    test("Assignment can print") {
      val x = Var("x",1)
      val b = Const(FloatValue(2.0))
      val s = Assignment(RefVar(x),  b)
      val p = new Print()
      val out = p.Stmt(s)
      //println(out) // This should print out "x = float(2.0:float)"
      assert(out.length() > 5) 
    }
    test("Procedure can print") {
      val x = Var("x",1)
      val b = Const(FloatValue(2.0))
      val s = Assignment(RefVar(x), Deref(x))
      val y = Assignment(RefVar(x), BinExp(OpMul(),
					   Deref(x),
					   Deref(x)))
      val p = Procedure(List(Noop(""),s,y), "main")
      val printer = new Print()
      val out = printer.Procedure(p)
      //println(out)
      assert(out.length() > 10) 
    }
  }
}
