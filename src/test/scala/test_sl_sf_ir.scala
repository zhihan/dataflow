package sl.ir.sf.test

import sl.ir.sf._
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
    new Chart(List(onState, offState),
      List(a, b, c))
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
    
    new Chart(List[StateLike](a,b,c,d), List(ab,ac,bd,cd, aa))
  }

  test("Creating SF IR") {
    val chart = createSimpleChart
    val s = chart.toDotString
    assert(s.length > 10)
  }

  test("Creating Flowchart IR") {
    val chart = createFlowchart
    val s = chart.toDotString
    // println(s)
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

}
