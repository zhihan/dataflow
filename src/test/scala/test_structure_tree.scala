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

  test("Get descendants") {
    val (t, _) = createSimpleTree
    val d = t.getDescendantIDs(Array(t.id))
    assert(d.length == 6)

    val ids = t.children.toArray.map(_.id)
    val d2 = t.getDescendantIDs(ids)
    assert(d2.length == 5)

    val d3 = t.getDescendantIDs(null)
    assert(d3.length == 0)
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

  test("Children tests") {
    val (t, _) = createSimpleTree
    val c1 = t.children.head
    assert(t.isChild(c1.id))
    assert(!t.isChild(-1))


    val children = t.getChildrenIDs()
    assert(children.length == 2)
  }

  test("Is descendant") {
    val (t, _) = createSimpleTree
    val c1 = t.children.head
    assert(t.isDescendant(c1.id))
    assert(!c1.isDescendant(t.id))

  }

  test("Batch make") {
    val f = new TreeNodeFactory
    val t = f.makeTree(4, Array(2,3,4), Array(1,1,2))
    val s = t.toDotString
    assert(s.length > 20)
    //println(n.toDotString)
    
  }

  test("Ancestor subsets") {
    val (t,(x,x1,x2, y,z)) = createSimpleTree
    val tests = Array(x.id, x1.id, y.id, z.id)
    val as = t.computeAncestorSubset(tests)
    assert(as.length == 2)
    assert(as.contains(x.id))
    assert(as.contains(y.id))

    val as2 = t.computeAncestorSubset(null)
    assert(as2.length == 0)
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

    val r2 = t.removePartiallyContainedAncestors(null)
    assert(r2.length == 0)
  }

}

