package sl.ir.test

import sl.ir._
import my.se._
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

