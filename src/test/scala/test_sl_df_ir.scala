package my.sl.ir.test

import my.sl.ir._
import my.se._
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


  test("simple DFG reachability") {
    val dfg = createSimpleGraph
    val y = dfg.getVarNodes("y") // Only one node
    val yId = y.map( v => v.id)
    val result = dfg.backwardReachable(yId.toArray)
    assert(result.length == 4)
    // result.foreach{ vid => println(dfg.nodes( vid ))}
  }

  test("simple DFG proc reachability") {
    val dfg = createSimpleGraph
    val y = dfg.getVarNodes("y") // Only one node
    val yId = y.map( v => v.id)
    val inactive = new Inactive(Array[Int](), Array[Int]())
    val result = dfg.backwardReachableProcs(yId.toArray, inactive)
    assert(result.length == 1)
    // result.foreach{ vid => println(dfg.nodes( vid ))}
  }

}
