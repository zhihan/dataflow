package sl.ir.test

import my.se._
import org.scalatest.FunSuite

class TreeTest extends FunSuite {
  def createSimpleTree = {
    // t-+----x--+----x1 
    //   |       |
    //   |       +----x2
    //   |
    //   |--- y--------z
    val fac = new TreeNodeFactory()
    
    val t = fac.make
    val x = fac.make
    t.addChild(x)

    val x1 = fac.make
    x.addChild(x1)
    val x2 = fac.make
    x.addChild(x2)

    val y = fac.make
    t.addChild(y)
    val z = fac.make
    
    y.addChild(z)

    (t, (x, x1, x2, y, z))
  }

  test("Print simple tree") {
    val (t,_) = createSimpleTree
    val s = t.toDotString
    // println(s)
    assert(s.length > 20)
  }

  test("Get nonleaf descendants") {
    val (t,_) = createSimpleTree
    val ids = t.getAllNonleafDescendantIDs()
    assert(ids.length == 3)
  }

  test("Get ancestors") {
    val (t,_) = createSimpleTree
    val c1 = t.children.head
    val c2 = c1.children.head
    val a = t.computeAncestors(Array(c2.id))
    assert(a.length == 2)
  }

  test("Batch make") {
    val f = new TreeNodeFactory
    val t = f.makeTree(4, Array(2,3,4), Array(1,1,2))
    val s = t.toDotString
    assert(s.length > 20)
    //println(n.toDotString)
    
  }

  test("Remove partial ancestors") {
    val (t,(x,x1,x2, y,z)) = createSimpleTree
    val tests = Array(x.id, x1.id, y.id, z.id)
    val r = t.removePartiallyContainedAncestors(tests)
    // println(r)
    assert(r.length == 3)
    assert(r.contains(y.id))
    assert(r.contains(x1.id))
    assert(r.contains(z.id))
  }

}

