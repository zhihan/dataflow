package se.bus.test

import my.se._

import org.scalatest.FunSuite

class BusTest extends FunSuite {
  test("Bus width") {
    // Create a bus and try width
    val a = AtomicElement("a",1)
    val b = AtomicElement("b",2)
    val bus = Bus("ab", List(a,b))
    assert(bus.width == 3)

    val busb = Bus("abb", List(bus, b))
    assert(busb.width == 5)
  }

  test("Bus children") {
    val  a = AtomicElement("a",1)
    val b = AtomicElement("b",2)
    val bus = Bus("ab", List(a,b))
    assert(bus.nChildren == 2)
  }

  test("Bus descendants") {
    val  a = AtomicElement("a",1)
    val b = AtomicElement("b",2)
    val bus = Bus("ab", List(a,b))
    assert(bus.nDescendants == 2)
    val busb = Bus("abb", List(bus, b))
    assert(busb.nDescendants == 3)
  }
}
