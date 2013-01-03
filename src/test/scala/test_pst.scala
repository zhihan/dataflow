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
      val root = Pst.createPst(ast.body)
      val isBasic = root match {
	case BasicRegion(_) => true
	case _ => false
      }
      assert(isBasic)
      
    }
  }

}
