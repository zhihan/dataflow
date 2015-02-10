package sl.ir

import sl.ir.graph._
import me.zhihan.se._
import org.scalatest.FunSuite

class VirtualGraphTest extends FunSuite {
  def createVGraph = {
    val gr = new VirtualPortGraph()
    val a = gr.newOutport("1") // 1o
    val b = gr.newInport("2")  // 2i
    val c = gr.newOutport("3") // 3o 
    val d = gr.newInport("4")  // 3i
    val e = gr.newOutport("5") // 4o
    val f = gr.newInport("6")  // 4i
    val g = gr.newOutport("7") // 5o
    val h = gr.newInport("8")  // 5i_1
    val i = gr.newInport("9")  // 5i_2
    val j = gr.newOutport("10") // 6o
    val k = gr.newOutport("11") // 7o
    val l = gr.newInport("12")  // 6i
    val m = gr.newInport("13")  // 7i

    gr.addEdge(c,b) // 3o -> 2i
    gr.addEdge(d,c) // 
    gr.addEdge(e,d) // 4o -> 3i
    gr.addEdge(f,e) // 
    gr.addEdge(g,f) // 5o -> 4i
    gr.addEdge(h,g) //
    gr.addEdge(i,g) //
    gr.addEdge(j,h) // 6o -> 5i_1
    gr.addEdge(k,i) // 7o -> 5i_2
    gr.addEdge(l,j) //
    gr.addEdge(m,k) //
    gr.addEdge(a,l) // 1o -> 6i
    gr
  }
  
  test("Simulink test") {
    val g = createVGraph
    val s = g.toDotString
    // println(s)
    
    val a = g.forwardReachablePairs(1)
    //a.foreach(println(_))
    assert(a.length == 10)
  }

  test("Simulink test new algorithm") {
    val g = createVGraph 
    val src = Array(1)
    val dst = Array(2)

    val pairs = g.reachablePairs(src, dst)
    // lines.foreach(println(_))
    assert( pairs.length == 10)
    // Test that the return arguments are Out-In pairs. 
    for (i <- 0 to 8 by 2) {
      assert( g.isOutport(pairs(i)))
    }
    for (i <- 1 to 9 by 2) {
      assert( !g.isOutport(pairs(i)))
    }

  }

  test("Virtual block graph") {
    val g = new VirtualBlockGraph()
    val names = Array("a", "b")
    val v = g.newBlocks(names)
    g.addEdges(Array(v(0)), Array(v(1)))
    assert(v.size == names.size)

    val s = g.toDotString() 
    assert(s.size > 6) 
  }


  test("Virtual port graph no self-loop"){
    val g = new VirtualPortGraph()
    val v = g.newOutport("a")
    try {
      g.addEdge(v, v)
      assert(true)
    } catch {
      case _:RuntimeException => () 
    }
  }

  test("Virtual port graph no repeat edges") {
    val g = new VirtualPortGraph()
    val s = g.newOutport("a")
    val t = g.newInport("b")
    val e = g.addEdge(s,t)
    val x = g.addEdge(s,t)
    assert( x == e)
  }

  test("Virtual block graph no self-loop"){
    val g = new VirtualBlockGraph()
    val v = g.newBlock("a")
    try {
      g.addEdge(v.id, v.id)
      assert(true)
    } catch {
      case _:RuntimeException => () 
    }
  }

  // Virtual block graph reachability graph
  def testReachGraph = {
    val g = new VirtualBlockGraph()
    val names = Array("a", "b", "c", "d", "e")
    val v = g.newBlocks(names)
    g.addEdge(v(0), v(2))
    g.addEdge(v(1), v(2))
    g.addEdge(v(2), v(3))
    g.addEdge(v(2), v(4))
    (g, v)
  }

  test("virtual block unreachable"){
    val (g, v) = testReachGraph
    val x = g.unreachable(Array(v(0)), Array(v(4)))
    assert(x.length == 2)

    val y = g.unreachable(null, null)
    assert(y.length == 5)
  }

  test("virtual block unreachable fwd"){
    val (g, v) = testReachGraph
    val x = g.fwdUnreachable(Array(v(0)))
    assert(x.length == 1)

    val y = g.fwdUnreachable(null)
    assert(y.length == 5)
  }

  test("virtual block unreachable bwd"){
    val (g, v) = testReachGraph
    val x = g.bwdUnreachable(Array(v(4)))
    assert(x.length == 1)

    val y = g.bwdUnreachable(null)
    assert(y.length == 5)
  }

  test("virtual block src contained in"){
    val (g, v) = testReachGraph
    val x = g.allSrcContainedIn(v, Array(v(2)))
    // x.foreach( e => println(e + " ") )
    // result contains v1, v2, v4, v5
    assert(x.length == 4)

    val y = g.allSrcContainedIn(v, null)
    assert (y.length == 0)

    val z = g.allSrcContainedIn(null, null)
    assert (z.length == 0)
  }

  test("virtual block dst contained in"){
    val (g, v) = testReachGraph
    val x = g.allDstContainedIn(v, Array(v(2)))
    // x.foreach( e => println(e + " ") )
    // result contains v1, v2, v4, v5
    assert(x.length == 4)

    val y = g.allDstContainedIn(v, null)
    assert (y.length == 0)

    val z = g.allDstContainedIn(null, null)
    assert (z.length == 0)
  }

  test("Named tree") {
    val f = new NameTreeNodeFactory()
    val root = f.make("root")
    val a = f.make("a")
    val b = f.make("b")
    val c = f.make("c")
    val d = f.make("d")

    root.addChild(a)
    a.addChild(b)
    b.addChild(c)
    a.addChild(d)

    val s = root.toDotString()
    // println(s)
    assert(s.size > 10)
    a.reduceChild(0)
    root.reduceChild(0) // reduce a
    //println(root.toDotString())

    val u = new NameTreeUtil()
    assert(u.fullpath(root, c) == "/c")
    assert(u.fullpath(root, d) == "/d")
  }
}

