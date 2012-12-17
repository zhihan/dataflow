import my.ir._
import my.ir.sim._
import my.ir.defuse._

import my.ir.conversion._

import org.scalatest.FunSuite

package my.ir.sim.Test {
  class AnalysisSuite extends FunSuite {
    test("Simple simstate analysis") {
      val a =  """function((),(),step) 
      {
        var(double, y);
      =(y, @x);
      =(x, +(@x, @u));
      } """
      val p = new ParseAndCreateIR()
      val (ast,sc) = p.parse(a)
      val step = Step(ast.body)
      val y = List(sc.getVar("y"))
      val x = Analysis.getSimState(step, y )
      val xname = x.map( v => v.name)
      // println(xname)
      assert(xname == List("x"))
    }
    test("Simple output simstate insersion") {
      val a =  """function((),(),step) 
      {
        var(double, y);
      if(@u){
      =(y, @x);
      } else {
      =(x, +(@x, @u));
      }
      } """
      val p = new ParseAndCreateIR()
      val (ast,sc) = p.parse(a)
      val step = Step(ast.body)
      val y = List(sc.getVar("y"))
     
      val x = Analysis.getSimState(step, y)
      assert(x.length == 2)

      val (newStep, newX) = Analysis.addOutputStateVar(step, y, x,sc)
      val xname = newX.map( v => v.name)
      

      val xNew = Analysis.getSimState(newStep, y)
      val x2 = xNew.map( v => v.name)
      assert(xNew.length == newX.length)
      
      val printer = new Print()
      val proc = Procedure(newStep.body, "Step")
      val prog = printer.Procedure(proc)
      //println(prog)
      assert(prog.length > 10)
    }

    test("Simple output generation") {
      val a =  """function((),(),step) 
      {
        var(double, y);
      =(y, @x);
      =(x, +(@x, @u));
      } """
      val p = new ParseAndCreateIR()
      val (ast,sc) = p.parse(a)
      val step = Step(ast.body)
      val y = List(sc.getVar("y"))
     
      val x = Analysis.getSimState(step, y)
      val (output, scope) = Analysis.createOutput(step, x)

      val printer = new Print()
      val proc = Procedure(output.body, "Output")
      val prog = printer.Procedure(proc)
      println(prog)
      assert(prog.length > 10)
    }

    test("Simple update generation") {
      val a =  """function((),(),step) 
      {
        var(double, y);
      =(y, @x);
      =(x, +(@x, @u));
      } """
      val p = new ParseAndCreateIR()
      val (ast,sc) = p.parse(a)
      val step = Step(ast.body)
      val y = List(sc.getVar("y"))
     
      val x = Analysis.getSimState(step, y)

      val (newStep, newX) = Analysis.addOutputStateVar(step, y, x,sc)
 
      val (update, scope) = Analysis.createUpdate(newStep, y)

      val printer = new Print()
      val proc = Procedure(update.body, "Update")
      val prog = printer.Procedure(proc)
      println(prog)
      assert(prog.length > 10)
    }

    test("Simple output and update with simstate insersion") {
      val a =  """function((),(),step) 
      {
        var(double, y);
      if(@u){
      =(y, @x);
      } else {
      =(x, +(@x, @u));
      }
      } """
      val p = new ParseAndCreateIR()
      val (ast,sc) = p.parse(a)
      val step = Step(ast.body)
      val y = List(sc.getVar("y"))
     
      val x = Analysis.getSimState(step, y)
      assert(x.length == 2)

      val (newStep, newX) = Analysis.addOutputStateVar(step, y, x,sc)
      val (output, _) = Analysis.createOutput(newStep, newX)
      val (update, _) = Analysis.createUpdate(newStep, y)
      
      val p1 = new Print()
      val proc1 = Procedure(output.body, "Output")
      val prog1 = p1.Procedure(proc1)
      println(prog1)
      assert(prog1.length > 10)

      val p2 = new Print()
      val proc2 = Procedure(update.body, "Update")
      val prog2 = p2.Procedure(proc2)
      println(prog2)
      assert(prog2.length > 10)
    }

  }
}
