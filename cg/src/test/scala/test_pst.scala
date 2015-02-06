import my.ir._
import my.se._
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
      val c = new ComputePst()
      val r = c.createPst(ast.body)
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
      val c = new ComputePst
      val r = c.createPst(ast.body)
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
      //println(cfg.graph.toDotString )
      //println(writeGraphviz(cfg.graph, (v => m.getFirstStatement(v).toString)))
      val c = new ComputePst()
      val (_, r) = c.createPst(cfg.entry,
				   cfg.graph, 
				   cfg.exit ,m)
      assert( r.regions(r.t.id) == ChainRegion())

      val children = r.t.children
      //println(children.length)
      //println(r.t.toDotString)
      assert(children.length == 2)
      assert(children.exists(c =>
	r.regions(c.id) == IfElseRegion()))
      assert(children.exists(c =>
	r.regions(c.id) == BasicRegion()))
    }
  

    test("Chain region for while loop") {
      val a = """function((),(), chain)
      {
	while(@b) {
	  =(y,@x);
	}
      =(z,@y);
      }"""
      val p = new ParseAndCreateIR()
      val (ast, _) = p.parse(a)
      val (cfg, m) = Utility.createCFGForList(ast.body)
      // println(cfg.graph.toDotString )
      val c = new ComputePst
      val (_, r) = c.createPst(cfg.entry,
				   cfg.graph, 
				   cfg.exit ,m)
      // println(r.t.toDotString)
      assert( r.regions(r.t.id) == ChainRegion())
      val children = r.t.children
      // print(children.length)
      assert(children.length == 2)
      assert(children.exists(c =>
	r.regions(c.id) == WhileRegion()))
      assert(children.exists(c =>
	r.regions(c.id) == BasicRegion()))
    } 

   test("Pst while loop from AST") {
      val a = """function((),(), whilst)
      {
	while(@b) {
	  =(y,@x);
	}
       =(z,@y);
      }"""
      val p = new ParseAndCreateIR()
      val (ast, _) = p.parse(a)
     //println("AST")
     //println(ast)
      val c = new ComputePst
      val r = c.createPst(ast.body)
      //println(r.t.toDotString)
      assert( r.regions(r.t.id) == ChainRegion())
      val children = r.t.children
      //print(children.length)
      assert(children.length == 2)
      assert(children.exists(c =>
	r.regions(c.id) == WhileRegion()))
      assert(children.exists(c =>
	r.regions(c.id) == BasicRegion()))
    }

    test("While loop nested") {
      val a = """function((),(), chain)
      {
	while(@b) {
	  while(@c) {
	    =(y,@x);
	  }
	}
      =(z,@y);
      }"""
      val p = new ParseAndCreateIR()
      val (ast, _) = p.parse(a)
      val (cfg, m) = Utility.createCFGForList(ast.body)
      // println(cfg.graph.toDotString )
      val c = new ComputePst
      val (_, r) = c.createPst(cfg.entry,
				   cfg.graph, 
				   cfg.exit ,m)
      // println(r.t.toDotString)
      assert( r.regions(r.t.id) == ChainRegion())
      val children = r.t.children
      // print(children.length)

      // Root region has two children
      // One of them is while region
      assert(children.length == 2)
      assert(children.exists(c =>
	r.regions(c.id) == WhileRegion()))
      // The while region has a child
      // which is also while region
      val w = children.filter(x => 
	r.regions(x.id) == WhileRegion())
      assert(w.length == 1)
      val wh = w.head
      assert(wh.children.length == 1)
      assert(r.regions(wh.children.head.id)
	     == WhileRegion())
    } 
 
   test("While loop with if region") {
      val a = """function((),(), chain)
      {
	while(@b) {
	  if (@c) {
	    =(y,@x);
	  } else {
	    =(y,@x);
	  }
	}
       }"""
      val p = new ParseAndCreateIR()
      val (ast, _) = p.parse(a)
      val (cfg, m) = Utility.createCFGForList(ast.body)

      val c = new ComputePst
      val (_, r) = c.createPst(cfg.entry,
				   cfg.graph, 
				   cfg.exit ,m)

      // Root region is a while region
      assert( r.regions(r.t.id) == WhileRegion())
      val children = r.t.children

      // The while region has a child
      // which is an if-else region
      assert(children.length == 1)
      assert(children.exists(c =>
	r.regions(c.id) == IfElseRegion()))
     } 
 
  }

}
