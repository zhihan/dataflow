package my.se.test

import my.se._
import org.scalatest.FunSuite

class GraphTest extends FunSuite {

    // Utility function
    def createLinGraph = {
      val v1 = new Vertex(1, "1")
      val v2 = new Vertex(2, "2")
      val v3 = new Vertex(3, "3")
      val f = new GraphFactory()
      val g = f.make(Array(v1,v2,v3))
      g.addEdge(v1,v2)
      g.addEdge(v2,v3)
      g
    }
  
    test("Self loop") {
      val g = createLinGraph
      val v2 = g.getV(2)
      val v1 = g.getV(1)
      g.addEdge(v2, v2)
      assert(g.hasSelfLoop(v2))
      assert(!g.hasSelfLoop(v1))
      assert(g.getSelfLoop(v2).length == 1)
      assert(g.getSelfLoop(v1).isEmpty)
    }

    test("Print graph") {
      val g = createLinGraph
      g.print()
    }

    test("Graph dot string") {
      val g = createLinGraph
      val s = g.toDotString
      assert(s.size > 10)
    }

    test("Graph butID") {
      val g = createLinGraph
      val v = g.getV(1)
      val butV = g.butID(Array(1))
      assert(butV.forall{ _ != 1})
    }

    test("Graph getE") {
      val g = createLinGraph
      val v = g.getE(1)
      assert(v != null)
      try {
        val v = g.getE(100)
        assert(true) // should error out
      } catch {
        case _ :RuntimeException => // nothing
      }

    }

    test("Remove a vertex") {
      val g = createLinGraph  // 1->2->3
      val v2 = g.getV(2)     
      g.removeVertex(v2)      // 1 3
      assert(g.V.length == 2)
      assert(g.E.length == 0)
    }

    test("Reduce a vertex") {
      val g = createLinGraph // 1->2->3
      val v2 = g.getV(2)
      g.reduceVertex(v2)
      assert(g.V.length == 2)  // 1->3
      assert(g.E.length == 1)
      
    }

    test("Deep copy") {
      val g = createLinGraph
      val f = new GraphFactory()
      val g2 = f.deepCopy(g)
      g.reduceVertex(g.getV(2)) // mutate g
      // println(g2.toDotString)
      assert(g2.getV(1) != g.getV(1)) // not Object equivalent
      // assert(g2.getV(1) == g2.getV(1)) 
      assert(g2.V.length == 3)
      assert(g2.E.length == 2)
    }

    test("Tarjan SCC algorithm") {
      val g = new Graph()
      val v1 = g.newVertex("v1")
      val v2 = g.newVertex("v2")
      val v3 = g.newVertex("v3")
      val v4 = g.newVertex("v4")
      g.addEdge(v1, v2)
      g.addEdge(v2, v3)
      g.addEdge(v3, v2)
      g.addEdge(v2, v4)
      
      val scc = tarjanSCC(g)
      assert(scc.length == 1)
      assert(scc(0).length == 2)
    }

    test("Dominator algorithm") {
      val graph = new Graph()
      val a = graph.newVertex("a")
      val b = graph.newVertex("b")
      val c = graph.newVertex("c")
      val d = graph.newVertex("d")
      val e = graph.newVertex("e")
      val f = graph.newVertex("f")
      val g = graph.newVertex("g")
      val h = graph.newVertex("h")
      val i = graph.newVertex("i")
      val j = graph.newVertex("j")
      val k = graph.newVertex("k")
      val l = graph.newVertex("l")
      val m = graph.newVertex("m")
      graph.addEdge(a,b)
      graph.addEdge(a,c)
      graph.addEdge(b,d)
      graph.addEdge(b,g)
      graph.addEdge(c,e)
      graph.addEdge(c,h)
      graph.addEdge(d,f)
      graph.addEdge(d,g)
      graph.addEdge(e,h)
      graph.addEdge(e,c)
      graph.addEdge(f,i)
      graph.addEdge(f,k)
      graph.addEdge(g,j)
      graph.addEdge(h,m)
      graph.addEdge(i,l)
      graph.addEdge(j,i)
      graph.addEdge(k,l)
      graph.addEdge(l,m)
      graph.addEdge(l,b)
      
      // println("The control-flow graph")
      val t = new TarjanDominators()
      val idom =  t.compute(graph, a)
      assert(idom.contains(b))
      assert(!idom.contains(a))
      // println("idom")
      // idom.foreach(e => println(e._1.sid + "->" +e._2.sid))
      val df = t.dominanceFrontier(a, idom, graph)
      assert(df(a).isEmpty)
      assert(df(c).contains(c) && df(c).contains(m))
      assert(df(b).contains(m) && df(b).contains(b))
      assert(df(d).contains(g) && df(d).contains(i) && df(d).contains(l))
    }
  

    test("Customized predecessor") {
      val graph = new Graph
      val v1 = graph.newVertex("x")
      val v2 = graph.newVertex("y")
      val dep = new Dependence(Array(v1.id), Array(v2.id), graph) 
      val inactive =new Inactive(Array[Int](), Array[Int]())
      val inE = graph.E.groupBy( e => e.to.id)
      val p = Graph.customizedPredecessor(inE, graph, v2, inactive, dep)
      assert (p.length == 1)
    }

    test("Reach with dependence") {
      val graph = new Graph
      val v1 = graph.newVertex("x")
      val v2 = graph.newVertex("y")
      val dep = new Dependence(Array(v1.id), Array(v2.id), graph) 
      val inactive =new Inactive(Array[Int](), Array[Int]())
      val reach = new Reachable(graph)
      val r = reach.backward(Array(v2.id), inactive, dep)
      assert (r.length == 2)

      val r2 = reach.forward(Array(v1.id), inactive, dep)
      assert (r2.length == 2)
    }

    test("Dependence to dot") {
      val graph = new Graph
      val v1 = graph.newVertex("x")
      val v2 = graph.newVertex("y")
      val dep = new Dependence(Array(v1.id), Array(v2.id), graph) 
      val s = dep.toDotString
      assert(s.length > 5)
    }

}
