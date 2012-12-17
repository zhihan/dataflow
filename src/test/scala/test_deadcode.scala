import my.ir._

import my.ir.conversion._

import org.scalatest.FunSuite

package my.ir.Test {
  class DeadCodeSuite extends FunSuite {
    test("Detect dead code") {
      val a =  """function((),(),step) 
      {
        var(double, y);
      =(y, @x);
      =(x, +(@x, @u));
      } """
      val p = new ParseAndCreateIR()
      val (ast,sc) = p.parse(a)
      assert(ast.body.length == 3)

      val y = sc.getVar("y")
      val out = DeadCode.removeDeadAssignment(ast.body, y )
      
      assert(out.length == 2)
      //val prog = Procedure(out, "reduced")
      //val print = new Print()
      //val str = print.Procedure(prog)
      //println(str)
      
    }
  }
}
