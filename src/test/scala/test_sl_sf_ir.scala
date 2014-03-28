package sl.ir.sf.test

import sl.ir.sf._
import org.scalatest.FunSuite

class SFIRTest extends FunSuite {
  // On <==> Off switch
  def createSimpleChart = {
    val onState = State(1, "on")
    val offState = State(2, "off")
    val a = NormalTransition(3, onState, offState, 
      None, None, None, None)
    onState.out.append(a)
    val b = NormalTransition(4, offState, onState, 
      None, None, None, None)
    offState.out.append(b)
    val c = DefaultTransition(5, onState)
    new Chart(List(onState, offState),
      List(a, b, c))
  }

  def createFlowchart = {
    val a = Junction(1)
    val b = Junction(2)
    val c = Junction(3)
    val d = Junction(4)

    val cond = new Condition("u>0")
    val ab = NormalTransition(5, a, b, 
      condition= Some(cond), None, None, None) 
    val ac = NormalTransition(6, a, c,
      None, None, None, None)
    a.out.append(ab)
    a.out.append(ac)

    val bd = NormalTransition(7, b, d,
      None, None, None, None)
    val cd = NormalTransition(8, c, d,
      None, None, None, None)
    val aa = DefaultTransition(9, a)
    
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
    println(s)
    assert(s.length > 10)
  }

}
