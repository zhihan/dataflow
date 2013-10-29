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
    val a = dfg.newInputNode("a")
    val b = dfg.newInputNode("b")
    val plus = dfg.newProcNode("+")
    
    dfg.addEdge(x, a)
    dfg.addEdge(u, b)
    dfg.addEdge(a, plus)
    dfg.addEdge(b, plus)
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
    cfg.reduceNonProcNodes
    val s = cfg.toDotString
    // println(s)
    assert(cfg.nNodes == 1)
  }

  test("Simple DFG reachability") {
    val dfg = createSimpleGraph
    val y = dfg.getVarNodes("y") // Only one node
    val yId = y.map( v => v.id)
    val result = dfg.backwardReachable(yId.toArray)
    assert(result.length == 6)
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
   val p1 = dfg.newInputNode("p1")
   val p2 = dfg.newInputNode("p2")
   val z = dfg.newVarNode("z")
   val prod = dfg.newProcNode("*")
   val m1 = dfg.newInputNode("m1")
   val m2 = dfg.newInputNode("m2")
   
   dfg.addEdge(x, p1)
   dfg.addEdge(p1, plus)
   dfg.addEdge(u, p2)
   dfg.addEdge(p2, plus)
   dfg.addEdge(plus, y)
    
   dfg.addEdge(x, m1)
   dfg.addEdge(m1, prod)
   dfg.addEdge(u, m2)
   dfg.addEdge(m2, prod)
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
    // |A| -> |       |                  | P2    | ---> |Ua|
    //        |       |   |      |       |       | 
    // |B| -> | Bus   |==>| Pass | =====>|       | ---> |Ub|
    //        |Create |   |      |       |       | 
    // |C| -> |       |                  |       | ---> |Uc| 
    //
    val dfg = new DataflowGraph()
    val (_,ap,a) = dfg.createNodes("A", 0, 1)
    val (_,bp,b) = dfg.createNodes("B", 0, 1)
    val (_,cp,c) = dfg.createNodes("C", 0, 1)

    val (bci,bcp,bc) = dfg.createNodes("BC", 3, 1)

    dfg.addEdge(a(0), bci(0))
    dfg.addEdge(b(0), bci(1))
    dfg.addEdge(c(0), bci(2))

    val (pi, pp, p) = dfg.createNodes("P", 1, 1)
    dfg.addEdge(bc(0), pi(0))

    val (p2i, p2p, p2) = dfg.createNodes("P2", 1, 1)
    dfg.addEdge(p(0), p2i(0))

    val (uai, uap, _) = dfg.createNodes("Ua", 1, 0)
    val uae = dfg.addEdge(p2(0), uai(0))
    val (ubi, ubp, _) = dfg.createNodes("Ub", 1, 0)
    val ube = dfg.addEdge(p2(0), ubi(0))
    val (uci, ucp, _) = dfg.createNodes("Uc", 1, 0)
    val uce = dfg.addEdge(p2(0), uci(0))

    val inact = new Inactive(Array[Int](), Array[Int]())
    val at = AtomicElement("a",1)
    val bt = AtomicElement("b",1)
    val ct = AtomicElement("c",1)
    val bus = Bus("Bus", List(at,bt,ct))
    val busVars = bci.map( x => x.id).toList
    val busProc = Map[Int,BusAction](bcp.id -> BusCreate(bus,busVars),
				     pp.id -> BusPass(bus),
				     p2p.id -> BusPass(bus))
    val busElemEdge  = Map(uae.id -> VBusSelect(bus,1), 
			 ube.id -> VBusSelect(bus,2), 
			 uce.id -> VBusSelect(bus,3))
    // println(dfg.toDotString)
    // println(busElemEdge)
    val (v,busReached) = dfg.reachBus(Array(ap.id,bp.id),
                                      inact,
                                      busProc, busElemEdge)
    /*
    for (x <- v) {
      print(" " + x.sid)
      if (busReached.contains(x.id)) {
	print("{") 
	val elems = busReached(x.id)
	print(elems + "}")
      }
    } */
    assert(v.contains(dfg.g.getV(uap.id)))
    assert(v.contains(dfg.g.getV(ubp.id)))
    assert(!v.contains(dfg.g.getV(ucp.id)))
  }
  
  test("SL DF with nv selector") {
    val dfg = new DataflowGraph()
    val (_,ap,a) = dfg.createNodes("A", 0, 1)
    val (_,bp,b) = dfg.createNodes("B", 0, 1)
    val (bci,bcp,bc) = dfg.createNodes("BC", 2, 1)
    dfg.addEdge(a(0), bci(0))
    dfg.addEdge(b(0), bci(1))
    val (bsi, bsp, bs) = dfg.createNodes("BS", 1, 1)
    dfg.addEdge(bc(0), bsi(0))
    val (ubi, ubp, _) = dfg.createNodes("Ub", 1, 0)
    val ube = dfg.addEdge(bs(0), ubi(0))
    
    val inact = new Inactive(Array[Int](), Array[Int]())
    val at = AtomicElement("a",1)
    val bt = AtomicElement("b",1)
    val bus = Bus("Bus", List(at,bt))
    val busVars = bci.map( x => x.id).toList
    val busProc = Map[Int,BusAction](bcp.id -> BusCreate(bus,busVars),
				     bsp.id -> BusSelect(bus, 
							 Map(bs(0).id -> 2)))
    val busElemEdge  = Map[Int, VBusSelect]()
    //println(dfg.toDotString)
    
    val (v,busReached) = dfg.backreachBus(Array(ubp.id),
                                          inact,
                                          busProc, busElemEdge)
    assert(!v.contains(dfg.g.getV(ap.id)))
    assert(v.contains(dfg.g.getV(bp.id)))
   
  }
   
  test("SL DF with nonvirtual buses") {
    //
    // |A| -> |       |                  | Bus   |
    //        |       |   |      |       | Select| ---> |Ua|
    // |B| -> | Bus   |==>| Pass | ==>   |       |
    //        |Create |   |      |       |       | ---> |Ub|
    // |C| -> |       |                  |       | 
    //
    val dfg = new DataflowGraph()
    val (_,ap,a) = dfg.createNodes("A", 0, 1)
    val (_,bp,b) = dfg.createNodes("B", 0, 1)
    val (_,cp,c) = dfg.createNodes("C", 0, 1)

    val (bci,bcp,bc) = dfg.createNodes("BC", 3, 1)

    dfg.addEdge(a(0), bci(0))
    dfg.addEdge(b(0), bci(1))
    dfg.addEdge(c(0), bci(2))

    val (pi, pp, p) = dfg.createNodes("P", 1, 1)
    dfg.addEdge(bc(0), pi(0))

    val (p2i, p2p, p2) = dfg.createNodes("P2", 1, 1)
    dfg.addEdge(p(0), p2i(0))

    val (uai, uap, _) = dfg.createNodes("Ua", 1, 1)
    val uae = dfg.addEdge(p2(0), uai(0))
    val (ubi, ubp, _) = dfg.createNodes("Ub", 1, 1)
    val ube = dfg.addEdge(p2(0), ubi(0))
    val (uci, ucp, _) = dfg.createNodes("Uc", 1, 1)
    val uce = dfg.addEdge(p2(0), uci(0))

    val inact = new Inactive(Array[Int](), Array[Int]())
    val at = AtomicElement("a",1)
    val bt = AtomicElement("b",1)
    val ct = AtomicElement("c",1)
    val bus = Bus("Bus", List(at,bt,ct))
    val busVars = bci.map( x => x.id).toList
    val busProc = Map[Int,BusAction](bcp.id -> BusCreate(bus,busVars),
				     pp.id -> BusPass(bus),
				     p2p.id -> BusPass(bus))
    val busElemEdge = Map(uae.id -> VBusSelect(bus,1), 
			 ube.id -> VBusSelect(bus,2), 
			 uce.id -> VBusSelect(bus,3))
    val (v,busReached) = dfg.backreachBus(Array(uap.id,ubp.id),
                                          inact,
                                          busProc,
					  busElemEdge)
    assert(v.contains(dfg.g.getV(ap.id)))
    assert(v.contains(dfg.g.getV(bp.id)))
    assert(!v.contains(dfg.g.getV(cp.id)))
  }


  test("SL DF with nested nv bus") {
    //
    // |A| -> |       |              
    //        | BC1   |   |      |   
    // |B| ->          ==>| Pass | ==>+=>| BS1 | ---> |Ub|
    //        |   BC2 |   |      |    
    // |C| -> |       |                 
    //
    val dfg = new DataflowGraph()
    val (_,ap,a) = dfg.createNodes("A", 0, 1)
    val (_,bp,b) = dfg.createNodes("B", 0, 1)
    val (bci, bcp, bc) = dfg.createNodes("BC", 2, 1)
    dfg.addEdge(a(0), bci(0))
    dfg.addEdge(b(0), bci(1))
    
    val (_,cp,c) = dfg.createNodes("C", 0, 1)

    val (bc2i, bc2p, bc2) = dfg.createNodes("BC2", 2, 1)
    dfg.addEdge(bc(0), bc2i(0))
    dfg.addEdge(c(0), bc2i(1))

    val (pi, pp, p) = dfg.createNodes("P", 1, 1)
    dfg.addEdge(bc2(0), pi(0))

    val (bsi, bsp, bs) = dfg.createNodes("BS", 1, 1)
    dfg.addEdge(p(0),bsi(0))

    val (ubi, ubp, _) = dfg.createNodes("Ub", 1, 0)
    val e1 = dfg.addEdge(bs(0), ubi(0))
    
    val inact = new Inactive(Array[Int](), Array[Int]())
    val at = AtomicElement("a",1)
    val bt = AtomicElement("b",1)
    val bus1 = Bus("Bus1", List(at,bt))

    val ct = AtomicElement("c",1)
    val bus2 = Bus("Bus2", List(bus1, ct))

    val bus1Vars = bci.map( x => x.id).toList
    val bus2Vars = bc2i.map( x => x.id).toList

    val busProc = Map[Int,BusAction](bcp.id -> BusCreate(bus1,bus1Vars),
                                     bc2p.id -> BusCreate(bus2,bus2Vars),
                                     pp.id -> BusPass(bus2),
                                     bsp.id -> BusPass(bus2))
    val busE = Map(e1.id -> VBusSelect(bus2, 3))
    // println(dfg.toDotString)
    // println(busE)
    val (v,busReached) = dfg.backreachBus(Array(ubp.id),
                                          inact,
                                          busProc,
					  busE)
    assert(!v.contains(dfg.g.getV(ap.id)))
    assert(v.contains(dfg.g.getV(bp.id)))
    assert(!v.contains(dfg.g.getV(cp.id)))
  }

  test("SL DF with exchange nv bus") {
    //
    // |A| -> |       |              
    //        | BC1   |                   |    |
    // |B| -> |       |  -----b-------->  |BC2 | --b->|Ub|
    //                             |C| -> |    | 
    val dfg = new DataflowGraph()
    val (_,ap,a) = dfg.createNodes("A", 0, 1)
    val (_,bp,b) = dfg.createNodes("B", 0, 1)
    val (bci, bcp, bc) = dfg.createNodes("BC", 2, 1)
    dfg.addEdge(a(0), bci(0))
    dfg.addEdge(b(0), bci(1))
 
    val (_,cp,c) = dfg.createNodes("C", 0, 1)

    val (bc2i, bc2p, bc2) = dfg.createNodes("BC2", 2, 1)
    val sb1 = dfg.addEdge(bc(0), bc2i(0))
    dfg.addEdge(c(0), bc2i(1))

    val (ubi, ubp, _) = dfg.createNodes("Ub", 1, 0)
    val e1 = dfg.addEdge(bc2(0), ubi(0))

    val inact = new Inactive(Array[Int](), Array[Int]())
    val at = AtomicElement("a",1)
    val bt = AtomicElement("b",1)
    val bus1 = Bus("Bus1", List(at,bt))

    val ct = AtomicElement("c",1)
    val bus2 = Bus("Bus2", List(bt, ct))

    val bus1Vars = bci.map( x => x.id).toList
    val bus2Vars = bc2i.map( x => x.id).toList

    val busProc = Map[Int,BusAction](bcp.id -> BusCreate(bus1,bus1Vars),
                                     bc2p.id -> BusCreate(bus2,bus2Vars))
    val busE = Map[Int, VBusSelect](sb1.id -> VBusSelect(bus1, 2),
                                   e1.id -> VBusSelect(bus2, 1))
    //println(dfg.toDotString)
    //println(busE)
    val (v,busReached) = dfg.backreachBus(Array(ubp.id),
                                          inact,
                                          busProc,
					  busE)
    assert(!v.contains(dfg.g.getV(ap.id)))
    assert(v.contains(dfg.g.getV(bp.id)))
    assert(!v.contains(dfg.g.getV(cp.id)))
  }
}
