package sl.ir.dim

import me.zhihan.utility._

import net.liftweb.json._
import net.liftweb.json.JsonDSL._

import org.scalatest.FunSuite

class DimGraphTest extends FunSuite {
  test("JSON parsing for inport") {
    val gensym = new Gensym()
    val x = Inport(1, gensym())
    val s = x.jsonString
    val m = parse(s)
    val y = Inport.fromObj(m)
    assert( x == y, "Parsed result match original")
  }

  
  test("JSON parsing for outport") {
    val gensym = new Gensym()
    val x = Outport(1, gensym())
    val s = x.jsonString
    val m = parse(s)
    val y = Outport.fromObj(m)
    assert( x == y, "Parse result match")
  }

  test("JSON parsing for block") {
    val gensym = new Gensym()
    val i = Inport(0, gensym())
    val o = Outport(0, gensym())
    val b = Block( List(i), List(o), 0, "Gain", "K")
    val s = b.jsonString
    val m = parse(s)
    val b2 = Block.fromObj(m)
    assert(b == b2, "Parse result match")
  }

  def simpleBd = {
    val bgen = new Gensym()
    val sgen = new Gensym()
    val i = Inport(0, sgen())
    val o = Outport(0, sgen())
    val b = Block(List(i), List(o), bgen(), "Gain", "K")
    val i2 = Inport(0, sgen())
    val o2 = Outport(0, sgen())
    val b2 = Block(List(i2), List(o2), bgen(), "Gain", "K2")
    val sig = Signal(o.id, i2.id, "a")

    val bd = new Diagram()
    bd.blocks.append(b)
    bd.blocks.append(b2)
    bd.signals.append(sig)
    bd
  }

  test("JSON parsing for block diagram") {
    val bd = simpleBd
    val s = bd.jsonString
    assert(s.length > 10)

    val m = parse(s)
    val bd2 = Diagram.fromObj(m)
    val s2 = bd2.jsonString
    assert(s == s2)
  }
}
