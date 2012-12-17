import my.se._
import org.scalatest.FunSuite

package my.se.test.GraphTest {
  class SimpleTestSuite extends FunSuite {

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
  }

}
