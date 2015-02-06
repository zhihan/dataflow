package sl.ir.dim.test

import sl.ir.src._
import my.utility._
import scala.util.parsing.json.JSON
import org.scalatest.FunSuite

class DimGraphTest extends FunSuite {
  test("JSON parsing for inport") {
    val gensym = new Gensym()
    val x = Inport(1, gensym())
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
    val gensym = new Gensym()
    val x = Outport(1, gensym())
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
    val gensym = new Gensym()
    val i = Inport(0, gensym())
    val o = Outport(0, gensym())
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

  def simpleBd = {
    val bgen = new Gensym()
    val sgen = new Gensym()
    val i = Inport(0, sgen())
    val o = Outport(0, sgen())
    val b = SISOBlock(i, o, bgen(), "Gain", "K")
    val i2 = Inport(0, sgen())
    val o2 = Outport(0, sgen())
    val b2 = SISOBlock(i2, o2, bgen(), "Gain", "K2")
    val sig = Signal(o, i2, "a")

    val bd = new Diagram()
    bd.blocks.append(b)
    bd.blocks.append(b2)
    bd.signals.append(sig)
    bd
  }

  test("JSON parsing for block diagram") {
    val bd = simpleBd
    val s = bd.json
    assert(s.length > 10)

    val bd2 = new Diagram()
    bd2.fromString(s)
    val s2 = bd2.json

    assert(s == s2)
  }
}
