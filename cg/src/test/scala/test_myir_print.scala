import my.ir._
import my.ir.conversion._
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

    test("Print file myir format") {
      val p = new ParseAndCreateIR()
      val filename = getClass.getResource("/simple.cgel").getFile()
      //println(filename)
      val (ast,_) = p.parseFile(filename)
      //println(ast)
      val printer = new Print("myir")
      val out = printer.Procedure(ast)
      //println(out)
      assert(out.length > 10)
    }
    test("Print myir format if") {
      val p = new ParseAndCreateIR()
      val filename = getClass.getResource("/if.cgel").getFile()
      //println(filename)
      val (ast,_) = p.parseFile(filename)
      //println(ast)
      val printer = new Print("myir")
      val out = printer.Procedure(ast)
      //println(out)
      assert(out.length > 10)
    }

    test("Print myir format while") {
      val p = new ParseAndCreateIR()
      val filename = getClass.getResource("/while.cgel").getFile()
      //println(filename)
      val (ast,_) = p.parseFile(filename)
      //println(ast)
      val printer = new Print("myir")
      val out = printer.Procedure(ast)
      //println(out)
      assert(out.length > 10)
    }

    test("Print myir format function-call") {
      val p = new ParseAndCreateIR()
      val filename = getClass.getResource("/fcn.cgel").getFile()
      //println(filename)
      val (ast,_) = p.parseFile(filename)
      //println(ast)
      val printer = new Print("myir")
      val out = printer.Procedure(ast)

      // We can parse the out as string
      val (ast2,_) = p.parse(out)

      // println(out)
      assert(out.length > 10)
    }
  }
}
