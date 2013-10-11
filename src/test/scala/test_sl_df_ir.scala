package sl.ir.test

import sl.ir._
import my.se._
import scala.collection.mutable.Map
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


  test("SL DF with nonvirtual buses for") {
    //
    // |A| -> |       |                  | Bus   |
    //        |       |   |      |       | Select| ---> |Ua|
    // |B| -> | Bus   |==>| Pass | =====>|       |
    //        |Create |   |      |       |       | ---> |Ub|
    // |C| -> |       |                  |       |  
    //
    val dfg = new DataflowGraph()
    val A = dfg.newProcNode("A")
    val a = dfg.newVarNode("a")
    dfg.addEdge(A,a)
    val B = dfg.newProcNode("B")
    val b = dfg.newVarNode("b")
    dfg.addEdge(B,b)
    val C = dfg.newProcNode("C")
    val c = dfg.newVarNode("c")
    dfg.addEdge(C,c)
    val BC = dfg.newProcNode("BC")
    val bc = dfg.newVarNode("bc")
    dfg.addEdge(BC,bc)
    dfg.addEdge(C,c)
    dfg.addEdge(a,BC)
    dfg.addEdge(b,BC)
    dfg.addEdge(c,BC)
    val P = dfg.newProcNode("P")
    val p = dfg.newVarNode("p")
    dfg.addEdge(P,p)
    dfg.addEdge(bc,P)
    val BS1 = dfg.newProcNode("BS1")
    val bs1 = dfg.newVarNode("bs1")
    val bs2 = dfg.newVarNode("bs2")
    val bs3 = dfg.newVarNode("bs3")
    dfg.addEdge(p,BS1)
    dfg.addEdge(BS1,bs1)
    dfg.addEdge(BS1,bs2)
    dfg.addEdge(BS1,bs3)
    val Ua = dfg.newProcNode("Ua")
    dfg.addEdge(bs1, Ua)
    val Ub = dfg.newProcNode("Ub")
    dfg.addEdge(bs2, Ub)
    val Uc = dfg.newProcNode("Uc")
    dfg.addEdge(bs3, Uc)
    
    val inact = new Inactive(Array[Int](), Array[Int]())
    val at = AtomicElement("a",1)
    val bt = AtomicElement("b",1)
    val ct = AtomicElement("c",1)
    val bus = Bus("Bus", List(at,bt,ct))
    val busVars = List(a.id, b.id, c.id)
    val busProc = Map[Int,BusAction](BC.id -> BusCreate(bus,busVars),
				     P.id -> BusPass(bus),
				     BS1.id -> BusSelect(bus))
    val busElemVar = Map[Int, Int](bs1.id -> 1, bs2.id -> 2, bs3.id -> 3)
    val (v,busReached) = dfg.reachBus(Array(A.id,B.id),
                                      inact,
                                      busProc, busElemVar)
    assert(v.contains(dfg.g.getV(Ua.id)))
    assert(v.contains(dfg.g.getV(Ub.id)))
    assert(!v.contains(dfg.g.getV(Uc.id)))
  }
  test("SL DF with nonvirtual buses") {
    //
    // |A| -> |       |                  | Bus   |
    //        |       |   |      |       | Select| ---> |Ua|
    // |B| -> | Bus   |==>| Pass | ==>+  |       |
    //        |Create |   |      |       |       | ---> |Ub|
    // |C| -> |       |                  |       | 
    //
    val dfg = new DataflowGraph()
    val A = dfg.newProcNode("A")
    val a = dfg.newVarNode("a")
    dfg.addEdge(A,a)
    val B = dfg.newProcNode("B")
    val b = dfg.newVarNode("b")
    dfg.addEdge(B,b)
    val C = dfg.newProcNode("C")
    val c = dfg.newVarNode("c")
    dfg.addEdge(C,c)
    val BC = dfg.newProcNode("BC")
    val bc = dfg.newVarNode("bc")
    dfg.addEdge(BC,bc)
    dfg.addEdge(a,BC)
    dfg.addEdge(b,BC)
    dfg.addEdge(c,BC)
    val P = dfg.newProcNode("P")
    val p = dfg.newVarNode("p")
    dfg.addEdge(P,p)
    dfg.addEdge(bc,P)
    val BS1 = dfg.newProcNode("BS1")
    val bs1 = dfg.newVarNode("bs1")
    val bs2 = dfg.newVarNode("bs2")
    dfg.addEdge(p,BS1)
    dfg.addEdge(BS1,bs1)
    dfg.addEdge(BS1,bs2)
    val Ua = dfg.newProcNode("Ua")
    dfg.addEdge(bs1, Ua)
    val Ub = dfg.newProcNode("Ub")
    dfg.addEdge(bs2, Ub)
    
    val inact = new Inactive(Array[Int](), Array[Int]())
    val at = AtomicElement("a",1)
    val bt = AtomicElement("b",1)
    val ct = AtomicElement("c",1)
    val bus = Bus("Bus", List(at,bt,ct))
    val busVars = List(a.id, b.id, c.id)
    val busProc = Map[Int,BusAction](BC.id -> BusCreate(bus,busVars),
                      P.id -> BusPass(bus),
                      BS1.id -> BusSelect(bus))
    val busElemVars = Map[Int, Int](bs1.id -> 1, bs2.id -> 2)
    val (v,busReached) = dfg.backreachBus(Array(Ua.id,Ub.id),
                                          inact,
                                          busProc,
					  busElemVars)
    assert(v.contains(dfg.g.getV(A.id)))
    assert(v.contains(dfg.g.getV(B.id)))
    assert(!v.contains(dfg.g.getV(C.id)))
  }


  test("SL DF with nested nv bus") {
    //
    // |A| -> |       |              
    //        | BC1   |   |      |   
    // |B| ->          ==>| Pass | ==>+=>| BS1 |-->|BS2|---> |Ub|
    //        |   BC2 |   |      |    
    // |C| -> |       |                 
    //
    val dfg = new DataflowGraph()
    val A = dfg.newProcNode("A")
    val a = dfg.newVarNode("a")
    dfg.addEdge(A,a)
    val B = dfg.newProcNode("B")
    val b = dfg.newVarNode("b")
    dfg.addEdge(B,b)
    val BC1 = dfg.newProcNode("BC1")
    val bc1 = dfg.newVarNode("bc1")
    dfg.addEdge(BC1,bc1)
    dfg.addEdge(a,BC1)
    dfg.addEdge(b,BC1)
    
    val C = dfg.newProcNode("C")
    val c = dfg.newVarNode("c")
    dfg.addEdge(C,c)

    val BC2 = dfg.newProcNode("BC2")
    val bc2 = dfg.newVarNode("bc2")
    dfg.addEdge(BC2,bc2)
    dfg.addEdge(bc1,BC2)    
    dfg.addEdge(c,BC2)

    val P = dfg.newProcNode("P")
    val p = dfg.newVarNode("p")
    dfg.addEdge(P,p)
    dfg.addEdge(bc2,P)
    val BS1 = dfg.newProcNode("BS1")
    val bs1 = dfg.newVarNode("bs1")
    dfg.addEdge(p,BS1)
    dfg.addEdge(BS1,bs1)
    val BS2 = dfg.newProcNode("BS2")
    val bs2 = dfg.newVarNode("bs2")
    dfg.addEdge(bs1,BS2)
    dfg.addEdge(BS2,bs2)
    val Ub = dfg.newProcNode("Ub")
    dfg.addEdge(bs2, Ub)
    
    val inact = new Inactive(Array[Int](), Array[Int]())
    val at = AtomicElement("a",1)
    val bt = AtomicElement("b",1)
    val bus1 = Bus("Bus1", List(at,bt))

    val ct = AtomicElement("c",1)
    val bus2 = Bus("Bus2", List(bus1, ct))

    val bus1Vars = List(a.id, b.id)
    val bus2Vars = List(bc1.id, c.id)

    val busProc = Map[Int,BusAction](BC1.id -> BusCreate(bus1,bus1Vars),
                                     BC2.id -> BusCreate(bus2,bus2Vars),
                                     P.id -> BusPass(bus2),
                                     BS1.id -> BusSelect(bus2),
                                     BS2.id -> BusSelect(bus1))
    val busVar = Map[Int, Int](bs1.id -> 1, bs2.id -> 2)
    val (v,busReached) = dfg.backreachBus(Array(Ub.id),
                                          inact,
                                          busProc,
					  busVar)
    assert(!v.contains(dfg.g.getV(A.id)))
    assert(v.contains(dfg.g.getV(B.id)))
    assert(!v.contains(dfg.g.getV(C.id)))
  }
}
