import me.zhihan.ir._
import me.zhihan.se._
import me.zhihan.ir.conversion._

import org.antlr.runtime._
import org.antlr.runtime.tree._

import org.scalatest.FunSuite

package me.zhihan.ir.CFGTest {
  class CFGTestSuite extends FunSuite {
  test("Construct cfg") {
      val a = """function((),(),main) 
      {
      if (@b) {
      =(y, @x);
      } else {
      =(y, +(@x, @x)); 
      } 
      }"""
      val p = new ParseAndCreateIR()
      val (ast,_) = p.parse(a)
      val (cfg,m) = Utility.createCFGForList(ast.body)
      //println(cfg.graph.toDotString )
      //println(writeGraphviz(cfg.graph, (v => m(v).toString)))
      assert(cfg.graph.V.length == 10)
      assert(cfg.graph.E.length == 10)
   }
  test("CFG for while loop") {
      val a = """function((),(),main) 
      {
      while (@b) {
      =(y, @x);
      }
      }"""
      val p = new ParseAndCreateIR()
      val (ast,_) = p.parse(a)
      val (cfg,m) = Utility.createCFGForList(ast.body)
      // println(cfg.graph.toDotString )
      assert(cfg.graph.V.length == 8)
      assert(cfg.graph.E.length == 8)

      //println(CFGPrint(cfg,m))
   }
  }
}
