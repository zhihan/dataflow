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
      val isBasic = r.regions(r.t.id) match {
	case BasicRegion() => true
	case _ => false
      }
      assert(isBasic)
      
    }

    test("Chain region from AST") {
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
      assert( r.regions(r.t.id) == ChainRegion())
      val children = r.t.children
      assert(children.length == 2)
      assert(children.exists(c =>
	r.regions(c.id) == IfElseRegion()))
      assert(children.exists(c =>
	r.regions(c.id) == BasicRegion()))
    }

    test("Chain region from CFG") {
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
      val (cfg, m) = Utility.createCFGForList(ast.body)
      val (_, r) = PstFactory.createPst(cfg.entry,
				   cfg.graph, 
				   cfg.exit ,m)
      assert( r.regions(r.t.id) == ChainRegion())
      val children = r.t.children
      print(children.length)
      assert(children.length == 2)
      assert(children.exists(c =>
	r.regions(c.id) == IfElseRegion()))
      assert(children.exists(c =>
	r.regions(c.id) == BasicRegion()))
    }
  }

}
