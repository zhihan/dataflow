import my.ir._
import my.se._
import my.ir.conversion._

import org.antlr.runtime._
import org.antlr.runtime.tree._

import org.scalatest.FunSuite

package my.ir.CFGTest {
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
      // println(cfg.graph.toDotString )
      //println(writeGraphviz(cfg.graph, (v => m(v).toString)))
      assert(cfg.graph.V.length == 9)
      assert(cfg.graph.E.length == 9)
   }
  }
}
