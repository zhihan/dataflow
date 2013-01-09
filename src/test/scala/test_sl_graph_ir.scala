package sl.ir.test

import sl.ir._
import my.se._
import org.scalatest.FunSuite

class VirtualGraphTest extends FunSuite {
  def createVGraph = {
    val gr = new VirtualPortGraph()
    val a = gr.newOutport("1")
    val b = gr.newInport("2")
    val c = gr.newOutport("3")
    val d = gr.newInport("4")
    val e = gr.newOutport("5")
    val f = gr.newInport("6")
    val g = gr.newOutport("7")
    val h = gr.newInport("8")
    val i = gr.newInport("9")
    val j = gr.newOutport("10")
    val k = gr.newOutport("11")
    val l = gr.newInport("12")
    val m = gr.newInport("13")
    gr.addEdge(c.id,b.id)
    gr.addEdge(d.id,c.id)
    gr.addEdge(e.id,d.id)
    gr.addEdge(f.id,e.id)
    gr.addEdge(g.id,f.id)
    gr.addEdge(h.id,g.id)
    gr.addEdge(i.id,g.id)
    gr.addEdge(j.id,h.id)
    gr.addEdge(k.id,i.id)
    gr.addEdge(l.id,j.id)
    gr.addEdge(m.id,k.id)
    gr.addEdge(a.id,l.id)
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
}

