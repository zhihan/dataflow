import me.zhihan.ir._
import me.zhihan.ir.conversion._
import me.zhihan.ir.diff._

import org.scalatest.FunSuite

package me.zhihan.ir.diff.Test {
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
      //println(print.Procedure(proc))
      assert(df.length > 1)
      
    }
  test("Differentiate functions") {
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

      val dFcns = Diff.createDiffFunctions(ast.body, sc, List(x,u))
      
      for ( (df, sc, x, u) <- dFcns ) {
        val proc = Procedure(df, "d_f_d_" + x.name )
        val print = new Print()
        //println(print.Procedure(proc))
        assert(df.length > 1)
      }
    }

  test("Differentiate simple expressions") {
      val a =  """function((),(),step) 
      {
        var(double, y);
      var(double, x);
      =(y, const(double, 1.0));
      =(y, <(@x, const(double, 1.0)));
      =(y, <=(@x, const(double, 1.0)));
      =(y, >(@x, const(double, 1.0)));
      =(y, >=(@x, const(double, 1.0)));
      =(y, ==(@x, const(double, 1.0)));
      =(y, /(const(double,1.0), @x));
      =(y, -(@x, const(double, 1.0)));
      } """

      val p = new ParseAndCreateIR()
      val (ast,sc) = p.parse(a)
      // println("AST = ")
      // println(ast)


      val x = sc.getVar("x")
      
      val dFcns = Diff.createDiffFunctions(ast.body, sc, List(x))
      
      for ( (df, sc, x, u) <- dFcns ) {
        val proc = Procedure(df, "d_f_d_" + x.name )
        //val print = new Print()
        //println(print.Procedure(proc))
        assert(df.length > 1)
      }
    }
  test("Differentiate intrinsic functions") {
      val a =  """function((),(),step) 
      {
        var(double, y);
      var(double, x);
      =(y, sin(@x));
      =(y, cos(@x));
      =(y, exp(@x));
      =(y, log(@x));
      } """

      val p = new ParseAndCreateIR()
      val (ast,sc) = p.parse(a)
      // println("AST = ")
      // println(ast)


      val x = sc.getVar("x")
      
      val dFcns = Diff.createDiffFunctions(ast.body, sc, List(x))
      
      for ( (df, sc, x, u) <- dFcns ) {
        val proc = Procedure(df, "d_f_d_" + x.name )
        // val print = new Print()
        // println(print.Procedure(proc))
        assert(df.length > 1)
      }
    }
  }
}
