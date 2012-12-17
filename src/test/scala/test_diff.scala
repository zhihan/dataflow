import my.ir._
import my.ir.conversion._
import my.ir.diff._

import org.scalatest.FunSuite

package my.ir.diff.Test {
  class DiffTestSuite extends FunSuite {
    test("Differentiate function") {
      val a =  """function((),(),step) 
      {
        var(double, y);
      =(y, @x);
      =(x, +(@x, @u));
      } """
      val p = new ParseAndCreateIR()
      val (ast,sc) = p.parse(a)
      // println("AST = ")
      // println(ast)
      assert(ast.body.length == 3)

      val x = sc.getVar("x")
      val u = sc.getVar("u")

      val (df,scope) = Diff(ast.body, sc, x, List(u))
      val proc = Procedure(df, "dfdx")
      val print = new Print()
      println(print.Procedure(proc))
   }
  }
}
