package my.sl.ir.test

import my.sl.ir._
import org.scalatest.FunSuite

class DfGraphTest extends FunSuite {
  def createSimpleGraph = {
    // A simple dfg for y = x + u
    val dfg = new DataflowGraph()
    val y = dfg.newVarNode("y")
    val x = dfg.newVarNode("x")
    val u = dfg.newVarNode("u")
    val plus = dfg.newProcNode("+")
    
    dfg.addEdge(x, plus)
    dfg.addEdge(u, plus)
    dfg.addEdge(plus, y)
    dfg
  }
  test("print simple DFG") {
    val cfg = createSimpleGraph
    val s = cfg.toDotString
    // println(s)
    assert(s.length > 20)
  }
}
