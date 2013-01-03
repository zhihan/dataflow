import my.ir._
import my.ir.pst._
import my.ir.conversion._

import org.antlr.runtime._
import org.antlr.runtime.tree._

import org.scalatest.FunSuite

package my.ir.PstTest {
  class PstTestSuite extends FunSuite {
    test("Basic region") {
      val a = """function((),(), basic)
      {
	=(y,@x);
	=(z,@y);
      }"""
      val p = new ParseAndCreateIR()
      val (ast, _) = p.parse(a)
      val r = PstFactory.createPst(ast.body)
      val isBasic = r.regions(r.root.id) match {
	case BasicRegion() => true
	case _ => false
      }
      assert(isBasic)
      
    }

    test("Chain region") {
      val a = """function((),(), chain)
      {
	=(y,@x);
        if(@b) { 
	  =(z,@y);
        } else {
	  =(z,@x);
	}
      }"""
      val p = new ParseAndCreateIR()
      val (ast, _) = p.parse(a)
      val r = PstFactory.createPst(ast.body)
      assert( r.regions(r.root.id) == ChainRegion())
      val children = r.root.children
      assert(children.length == 2)
      assert(children.exists(c =>
	r.regions(c.id) == IfElseRegion()))
      assert(children.exists(c =>
	r.regions(c.id) == BasicRegion()))
    }
  }

}
