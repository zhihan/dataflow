package sl.ir.sf

import sl.ir.sf._
import sl.ir._
import me.zhihan.se._
import scala.collection.mutable.ArrayBuffer

import org.scalatest.FunSuite

class SFIRTest extends FunSuite {
  // On <==> Off switch
  def createSimpleChart = {
    val f = new StateflowFactory()
    val onState = f.state("on")
    val offState = f.state("off")
    val a = f.normalTransition(onState, offState, 
      None, None, None, None)
    onState.out.append(a)
    val b = f.normalTransition(offState, onState, 
      None, None, None, None)
    offState.out.append(b)
    val c = f.defaultTransition(onState)
    val chart = new Chart(List(onState, offState),
      List(a, b, c), List())
    (f.objects, chart)
  }

  def createFlowchart = {
    val f = new StateflowFactory()
    
    val b = f.junction()
    val a = f.junction()
    val c = f.junction()
    val d = f.junction()

    val cond = new Condition("u>0")
    val ab = f.normalTransition(a, b, 
      condition= Some(cond), None, None, None) 
    val ac = f.normalTransition(a, c,
      None, None, None, None)
    a.out.append(ab)
    a.out.append(ac)

    val bd = f.normalTransition(b, d,
      None, None, None, None)
    val cd = f.normalTransition(c, d,
      None, None, None, None)
    val aa = f.defaultTransition(a)
    
    val chart = new Chart(List[StateLike](a,b,c,d), 
      List(ab,ac,bd,cd, aa), List())
    (f.objects, chart)
  }

  def createTwoState = {
    val f = new StateflowFactory()
    
    val b = f.junction()
    val a = f.junction()
    val c = f.junction()
    val d = f.junction()

    val cond = new Condition("u>0")
    val ab = f.normalTransition(a, b, 
      condition= Some(cond), None, None, None) 
    val ac = f.normalTransition(a, c,
      None, None, None, None)
    a.out.append(ab)
    a.out.append(ac)

    val bd = f.normalTransition(b, d,
      None, None, None, None)
    val cd = f.normalTransition(c, d,
      None, None, None, None)
    val aa = f.defaultTransition(a)
    
    val chart = new Chart(List[StateLike](a,b,c,d), 
      List(ab,ac,bd,cd, aa), List[Data]())
    (f.objects, chart)
  }

  def createTwoStatesAndData = {
    val f = new StateflowFactory()
    val onState = f.state("on")
    val offState = f.state("off")
    val onData = f.data("onData")
    val offData = f.data("offData")
    val onIn = f.data("onIn")
    val offIn = f.data("offIn")

    val chart = new Chart(List[StateLike](onState, offState),
      List[Transition](), List[Data](onData, offData, onIn, offIn))

    // Dependence
    val depSrc = Array[Int](onState.id, onData.id, 
      offState.id, offData.id, onState.id, onIn.id,
      offState.id, offIn.id )
    val depDst = Array[Int](onData.id, onState.id, 
      offData.id, offState.id, onIn.id, onState.id, 
      offIn.id, offState.id )
  
    val dep = new SFDependence(depSrc, depDst)

    val dataIds = Array[Int](onData.id, offData.id)
    val inIds = Array[Int](onIn.id, offIn.id)
    (f.objects, chart, dep, dataIds, inIds)
  }

  def createSLGraph = {
    val g = new DataflowGraph()
    val y1 = g.newVarNode("y1")
    val y2 = g.newVarNode("y2")

    val u1 = g.newInputNode("u1")
    val u2 = g.newInputNode("u2")

    val c1 = g.newVarNode("c1")
    val c2 = g.newVarNode("c2")

    val use = g.newInputNode("useY1")
    g.addEdge(y1, use)
    g.addEdge(c1, u1)
    g.addEdge(c2, u2)
    (g, Array(u1,u2), Array(y1,y2), use)
  }

  test("Creating SF IR") {
    val (_, chart) = createSimpleChart
    val s = chart.toDotString
    assert(s.length > 10)
  }

  test("Creating dependence graph") {
    val (objs, chart) = createSimpleChart
    val dg = new StateflowDependenceGraph(objs)
    // println(chart.toDotString)
    val s = dg.toDotString
    assert(s.length > 10)
  }

  test("Creating Flowchart IR") {
    val (_, chart) = createFlowchart
    val s = chart.toDotString
    // println(s)
    assert(s.length > 10)
  }

  test("Creating dependence graph2") {
    val (objs, chart) = createFlowchart
    val dg = new StateflowDependenceGraph(objs)
    // println(chart.toDotString)
    // println(dg.toDotString)
    val s = dg.toDotString
    assert(s.length > 10)
  }

  test("StateflowObject tests") {
    val f = new StateflowFactory()
    val a = f.state("a")
    assert(StateflowObject.isStateLike(a))

    val j = f.junction()
    assert(StateflowObject.isStateLike(j))

    val t = f.defaultTransition(a)
    assert(!StateflowObject.isStateLike(t))
  }

  test("Hybrid SF/SL graph search") {
    val (objs, chart, sfDep, dataIds, inIds) = createTwoStatesAndData
    val dg = new StateflowDependenceGraph(objs)
    val dep = sfDep.getDependence(dg.objToId, dg.g)
    val (dfg, in, out, use) = createSLGraph
    val inVIds = in.map(_.id) 
    val outVIds = out.map(_.id)

    val sfMap = new StateflowDataflowMap(inIds, inVIds, dataIds, outVIds)
    val reach = new SLSFGraphBackReach(dfg, new Dependence(null,null, dfg.g),
      dg, dep, sfMap)
    val reachSet = reach.reach(Array(use.id))
    val vars = reachSet.dfgReach.getVars
    assert(vars.size == 2)
  }
}
