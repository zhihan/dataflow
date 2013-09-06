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

    val c = AtomicElement("b", 2)
    val busb = Bus("abc", List(bus, c))
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
    val c = AtomicElement("b", 2)
    val busb = Bus("abc", List(bus, c))
    assert(busb.nDescendants == 4)
  }

  test("Bus indexing") {
    val a = AtomicElement("a",1)
    val b = AtomicElement("b",2)
    val bus = Bus("ab", List(a,b))
    val c = AtomicElement("b",2)
    val busb = Bus("abb", List(bus, b))

    assert(busb.get(0) == busb)
    assert(busb.get(1) == bus)
    assert(busb.get(2) == a)
    assert(busb.get(3) == b)
    assert(busb.get(4) == c)
  }

  test("Bus descendant using indexing") {
    val a = AtomicElement("a",1)
    val b = AtomicElement("b",2)
    val bus = Bus("ab", List(a,b))
    val c = AtomicElement("b",2)
    val busb = Bus("abb", List(bus, b))

    assert(busb.isDescendant(1,1) == false)
    assert(busb.isDescendant(2,0) == true)
    assert(busb.isDescendant(3,1) == true)
    assert(busb.isDescendant(4,1) == false)
  }
  
  test("Bus leaves indexing") {
    val a = AtomicElement("a",1)
    val b = AtomicElement("b",2)
    val bus = Bus("ab", List(a,b))
    val c = AtomicElement("b",2)
    val busb = Bus("abb", List(bus, b))

    assert(busb.leavesId == Set(2,3,4))
  }

  test("Bus leaves indexing for subset") {
    val a = AtomicElement("a",1)
    val b = AtomicElement("b",2)
    val bus = Bus("ab", List(a,b))
    val c = AtomicElement("b",2)
    val busb = Bus("abb", List(bus, b))

    assert(busb.leavesIdOf(Set(1,4)) == Set(2,3,4))
    assert(busb.leavesIdOf(Set(1)) == Set(2,3))
    assert(busb.leavesIdOf(Set(4)) == Set(4))
  }

  test("Bus leaves indexing for expand") {
    val a = AtomicElement("a",1)
    val b = AtomicElement("b",2)
    val bus = Bus("ab", List(a,b))
    val c = AtomicElement("b",2)
    val busb = Bus("abb", List(bus, b))

    assert(busb.expand(Set(1,4)) == Set(0,1,2,3,4))
    assert(busb.expand(Set(2,3,4)) == Set(0,1,2,3,4))
    assert(busb.expand(Set(1)) == Set(1,2,3))
    assert(busb.expand(Set(1,2)) == Set(1,2,3))
    assert(busb.expand(Set(4)) == Set(4))
    assert(busb.expand(Set(0)) == Set(0,1,2,3,4))
  }

  test("Bus leaves indexing for compact") {
    val a = AtomicElement("a",1)
    val b = AtomicElement("b",2)
    val bus = Bus("ab", List(a,b))
    val c = AtomicElement("b",2)
    val busb = Bus("abb", List(bus, b))

    assert(busb.compact(Set(1,4)) == Set(0))
    assert(busb.compact(Set(2,3,4)) == Set(0))
    assert(busb.compact(Set(1)) == Set(1))
    assert(busb.compact(Set(1,2)) == Set(1))
    assert(busb.compact(Set(4)) == Set(4))
  }
}
