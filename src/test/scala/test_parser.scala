import my.ir._
import my.ir.conversion._

import org.antlr.runtime._
import org.antlr.runtime.tree._

import org.scalatest.FunSuite

package my.ir.ParserTest {

  // A simple use of visitor callbacks 

  class PrintAction() extends TreeVisitorAction 
  {
    override def post(o:AnyRef) =
    {
      val t = o.asInstanceOf[CommonTree] 
      println("Visit " +t.toString() + t.getType()+ t.getText())
      t
    }
    override def pre(o:AnyRef) = o

  }


  class ParserTestSuite extends FunSuite {
    test("Parse and then print") {
      val a = """function((),(),main) 
      {
        var(double, y);
      =(y, @x);
      =(y, +(@x, @x)); 
      } """
      val input = new ANTLRStringStream(a)

      // Parse
      val lexer = new CGELLexer(input)
      val tokens = new CommonTokenStream(lexer)
      val parser = new CGELParser(tokens)

      val parseResult = parser.function()
      val t = parseResult.getTree().asInstanceOf[CommonTree]
      
      val visitor = new ConvertingVisitor()
      val (ast,_) = visitor.visit(t)
      // println(ast)
      val printer = new Print()
      val out = printer.Procedure(ast)
      // println(out)
      assert(out.length() > 10)
    }

    test("Parse if statement") {
      val a = """function((),(),main) 
      {
      if (@b) {
      =(y, @x);
      } else {
      =(y, +(@x, @x)); 
      } 
      }"""
      val input = new ANTLRStringStream(a)

      // Parse
      val lexer = new CGELLexer(input)
      val tokens = new CommonTokenStream(lexer)
      val parser = new CGELParser(tokens)

      val parseResult = parser.function()
      val t = parseResult.getTree().asInstanceOf[CommonTree]
      
      val visitor = new ConvertingVisitor()
      val (ast,_) = visitor.visit(t)
      // println(ast)
      val printer = new Print()
      val out = printer.Procedure(ast)
      // println(out)
      assert(out.length() > 10)
    }

    test("Parse while loop") {
      val a = """function((),(),main) 
      {
        while (@b) {
        =(y, @x);
	}  
      }"""
      val input = new ANTLRStringStream(a)

      // Parse
      val lexer = new CGELLexer(input)
      val tokens = new CommonTokenStream(lexer)
      val parser = new CGELParser(tokens)

      val parseResult = parser.function()
      val t = parseResult.getTree().asInstanceOf[CommonTree]
      
      val visitor = new ConvertingVisitor()
      val (ast,_) = visitor.visit(t)
      //println(ast)
      val printer = new Print()
      val out = printer.Procedure(ast)
      // println(out)
      assert(out.length() > 10)
    }    
    test("Parse function call") {
      val a = """function((),(),main) 
      {
        =(y, fcn());
        =(y, fcn(@x));
        =(y, fcn(@x, @x));
      }"""
      
      /*
       * ANTLR parsing
       * 
      val input = new ANTLRStringStream(a)
      val lexer = new CGELLexer(input)
      val tokens = new CommonTokenStream(lexer)
      val parser = new CGELParser(tokens)

      val parseResult = parser.function()
      val t = parseResult.getTree().asInstanceOf[CommonTree]
      println(t.toStringTree()) */
      

      val p = new ParseAndCreateIR()
      val (ast,_) = p.parse(a)
      // println(ast)
      val printer = new Print()
      val out = printer.Procedure(ast)
      // println(out) 
      assert(out.length > 10)
    }
  }
}
