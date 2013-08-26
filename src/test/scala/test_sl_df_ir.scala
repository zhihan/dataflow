package sl.ir.test

import sl.ir._
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

  test("Simple DFG reduce var") {
    val cfg = createSimpleGraph
    cfg.reduceVarNodes
    val s = cfg.toDotString
    //println(s)
    assert(cfg.nNodes == 1)
  }

  test("Simple DFG reachability") {
    val dfg = createSimpleGraph
    val y = dfg.getVarNodes("y") // Only one node
    val yId = y.map( v => v.id)
    val result = dfg.backwardReachable(yId.toArray)
    assert(result.length == 4)
    // result.foreach{ vid => println(dfg.nodes( vid ))}
  }

  test("Simple DFG proc reachability") {
    val dfg = createSimpleGraph
    val y = dfg.getVarNodes("y") // Only one node
    val yId = y.map( v => v.id)
    val inactive = new Inactive(Array[Int](), Array[Int]())
    val result = dfg.backwardReachableProcs(yId.toArray, inactive)
    assert(result.length == 1)
    // result.foreach{ vid => println(dfg.nodes( vid ))}
  }

 def createSimpleGraph2 = {
    // A simple dfg for
    //  y = x + u
    //  z = x * u   
    val dfg = new DataflowGraph()
    val y = dfg.newVarNode("y")
    val x = dfg.newVarNode("x")
    val u = dfg.newVarNode("u")
    val plus = dfg.newProcNode("+")
    val z = dfg.newVarNode("z")
    val prod = dfg.newProcNode("*")

    dfg.addEdge(x, plus)
    dfg.addEdge(u, plus)
    dfg.addEdge(plus, y)
    
    dfg.addEdge(x, prod)
    dfg.addEdge(u, prod)
    dfg.addEdge(prod, z)

    dfg
  }



  test("Simple DFG proc fwd/bwd reachability") {
    val dfg = createSimpleGraph2
    val y = dfg.getVarNodes("y")
    val yId = y.map( v => v.id)
    val x = dfg.getVarNodes("x")
    val xId = x.map( v => v.id)
    val inactive = new Inactive(Array[Int](), Array[Int]())
    val result = dfg.reachableProcs(xId.toArray, yId.toArray, inactive)
    assert(result.length == 1)
    val result2 = dfg.reachableVars(xId.toArray, yId.toArray, inactive)
    assert(result2.length == 2)

    val result3 = dfg.reachableProcs(yId.toArray, xId.toArray, inactive)  
    assert(result3.length == 0)    
    val result4 = dfg.reachableVars(yId.toArray, xId.toArray, inactive)  
    assert(result4.length == 0)    
  }

}
