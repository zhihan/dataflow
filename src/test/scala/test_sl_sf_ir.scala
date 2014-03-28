package sl.ir.sf.test

import sl.ir.sf._
import org.scalatest.FunSuite

class SFIRTest extends FunSuite {
  def createSimpleChart = {
    val onState = State(1, "on")
    val offState = State(2, "off")
    val a = NormalTransition(3, onState, offState, 
      None, None, None, None)
    val b = NormalTransition(4, offState, onState, 
      None, None, None, None)
    val c = DefaultTransition(5, onState)
    new Chart(List(onState, offState),
      List(a, b, c))
  }

  test("Creating SF IR") {
    val chart = createSimpleChart
    val s = chart.toDotString
    assert(s.length > 10)
  }
}
