package sl.ir.dim.test

import sl.ir.dim._
import scala.util.parsing.json.JSON
import org.scalatest.FunSuite

class DimGraphTest extends FunSuite {
  test("JSON parsing for inport") {
    val x = Inport(1)
    val s = x.json
    val m = JSON.parseFull(s)
    m match {
      case None => assert(false, "Cannot parse json string")
      case Some(m) => {
        val a = m.asInstanceOf[Map[String, Any]]
        val y = Inport.fromMap(a)
        assert( x == y, "Parsed result match original")
      }
    }
  }
  test("JSON parsing for outport") {
    val x = Outport(1)
    val s = x.json
    val m = JSON.parseFull(s)
    m match {
      case None => assert(false, "Cannot parse json string")
      case Some(m) => {
        val a = m.asInstanceOf[Map[String, Any]]
        val y = Outport.fromMap(a)
        assert( x == y, "Parsed result match original")
      }
    }
  }

  test("JSON parsing for block") {
    val i = Inport(0)
    val o = Outport(0)
    val b = SISOBlock( i, o, 0, "Gain", "K")
    val s = b.json
    val m = JSON.parseFull(s)
    m match {
      case None => assert(false, "Cannot parse json string")
      case Some(mm) => {
        val blockM = mm.asInstanceOf[Map[String, Any]]
        val b2 = Block.fromMap(blockM)
        assert(b == b2, "Parse result match")
      }
    }
  }
}
