package se.bus.test

import my.se._

import org.scalatest.FunSuite

class BusTest extends FunSuite {

  def testBus = {
    // (abb) -+--- (ab) +---- a
    //        |         +---- b
    //        +--- b
    //
    val a = AtomicElement("a",1)
    val b = AtomicElement("b",2)
    val bus = Bus("ab", List(a,b))
    val c = AtomicElement("b",2)
    Bus("abb", List(bus, b))

  }

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
    val busb =  Bus("abb", List(bus, b))

    assert(busb.get(0) == busb)
    assert(busb.get(1) == bus)
    assert(busb.get(2) == a)
    assert(busb.get(3) == b)
    assert(busb.get(4) == c)
  }

  test("Bus descendant using indexing") {
    val busb = testBus

    assert(busb.isDescendant(1,1) == false)
    assert(busb.isDescendant(2,0) == true)
    assert(busb.isDescendant(3,1) == true)
    assert(busb.isDescendant(4,1) == false)
  }
  
  test("Bus leaves indexing") {
    val busb = testBus

    assert(busb.leavesId == Set(2,3,4))
  }

  test("Bus leaves indexing for subset") {
    val busb = testBus

    assert(busb.leavesIdOf(Set(0)) == Set(2,3,4))    
    assert(busb.leavesIdOf(Set(1,4)) == Set(2,3,4))
    assert(busb.leavesIdOf(Set(1)) == Set(2,3))
    assert(busb.leavesIdOf(Set(2,3)) == Set(2,3))    
    assert(busb.leavesIdOf(Set(4)) == Set(4))
  }

  test("Bus leaves indexing for expand") {
    val busb = testBus

    assert(busb.expand(Set(1,4)) == Set(0,1,2,3,4))
    assert(busb.expand(Set(2,3,4)) == Set(0,1,2,3,4))
    assert(busb.expand(Set(1)) == Set(1,2,3))
    assert(busb.expand(Set(1,2)) == Set(1,2,3))
    assert(busb.expand(Set(4)) == Set(4))
    assert(busb.expand(Set(0)) == Set(0,1,2,3,4))
  }

  test("Bus leaves indexing for compact") {
    val busb = testBus

    assert(busb.compact(Set(1,4)) == Set(0))
    assert(busb.compact(Set(2,3,4)) == Set(0))
    assert(busb.compact(Set(1)) == Set(1))
    assert(busb.compact(Set(1,2)) == Set(1))
    assert(busb.compact(Set(4)) == Set(4))
  }

  test("Uion bus elements") {
    // Bus structure 
    val busb = testBus
    // disjoint
    assert(busb.compact(busb.union(Set(1), Set(4))) == Set(0))
    // a is superset of b
    assert(busb.compact(busb.union(Set(1), Set(2))) == Set(1))
    // a overlap with b
    assert(busb.compact(busb.union(Set(2,3), Set(3,4))) == Set(0))
  }

  test("Diff bus elements") {
    // Bus structure 
    val busb = testBus

    // a does not overlap with b
    assert(busb.compact(busb.diff(Set(1), Set(4))) == Set(1))
    // a is superset of b
    assert(busb.compact(busb.diff(Set(1), Set(2))) == Set(3))
    // a overlap with b
    assert(busb.compact(busb.diff(Set(2,3), Set(3,4))) == Set(2))
    // a is equal to b
    assert(busb.compact(busb.diff(Set(2,3), Set(1))) == Set())
    // a is subset of b
    assert(busb.compact(busb.diff(Set(2,3), Set(0))) == Set())

  }

  test("Intersect bus elements") {
    // Bus structure 
    val busb = testBus

    // a does not overlap with b
    assert(busb.compact(busb.intersect(Set(1), Set(4))) == Set())
    // a is superset of b
    assert(busb.compact(busb.intersect(Set(1), Set(2))) == Set(2))
    // a overlap with b
    assert(busb.compact(busb.intersect(Set(2,3), Set(3,4))) == Set(3))
    // a is equal to b
    assert(busb.compact(busb.intersect(Set(2,3), Set(1))) == Set(1))
    // a is subset of b
    assert(busb.compact(busb.intersect(Set(2,3), Set(0))) == Set(1))

  }

  test("Distribute subset to children") {
    // Bus structure 
    val busb = testBus
    val c = busb.children
    val ac = busb.distribute(Set(0))
    assert(ac(1) == Set(0))
    c(0) match {
      case b:Bus => assert(b.compact(ac(0)) == Set(0))
      case _ => assert(true)
    }
  }

  test("Distribute subset to children 2") {
    val busb = testBus
    val c = busb.children
    val ac = busb.distribute(Set(1,2))
    assert(ac(1) == Set())
    c(0) match {
      case b:Bus => assert(b.compact(ac(0)) == Set(0))
      case _ => assert(true)
    }
  }
  
  test("Collect subset from children") {
    // Bus structure 
    val busb = testBus
    val all = busb.collect(List(Set(0), Set(0)))
    assert(busb.compact(all) == Set(0))

    val ac = busb.collect(List(Set(1), Set(0)))
    assert(busb.compact(ac) == Set(2,4))
  }
}

class BusGraphTest extends FunSuite {
  def testBus = {
    // (abb) -+--- (ab) +---- a
    //        |         +---- b
    //        +--- b
    //
    val a = AtomicElement("a",1)
    val b = AtomicElement("b",2)
    val bus = Bus("ab", List(a,b))
    val c = AtomicElement("b",2)
    Bus("abb", List(bus, b))

  }

  test("Backward") {
    //             #==> v2
    //  v0 => v1 ==
    //             #==> v3
    val g = new Graph()
    val v0 = g.newVertex("v0")
    val v1 = g.newVertex("v1")
    val v2 = g.newVertex("v2")
    val v3 = g.newVertex("v3")
    g.addEdge(v0, v1)
    g.addEdge(v1, v2)
    g.addEdge(v1, v3)

    val bbus = testBus
    val a = SubBus(bbus, Set(2))
    val b = SubBus(bbus, Set(3))
    val inactive = new Inactive(Array[Int](), Array[Int]())

    val p = new PropagateSet[SubBus](g, SubBusOp, inactive)
    val m = p.backward(Array(v2.id, v3.id), 
		       Array(a, b))
    assert(bbus.compact(m(v0.id).elements) == Set(1))
  }
}
