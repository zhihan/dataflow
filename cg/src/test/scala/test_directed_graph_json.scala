package me.zhihan.se.json

import me.zhihan.se._
import scala.util.parsing.json._
import org.scalatest.FunSuite


class GraphJSONTest extends FunSuite {
  def createGraph = {
    val g = new Graph()
    val v1 = g.newVertex("a")
    val v2 = g.newVertex("b")
    val e = g.addEdge(v1, v2)
    g
  }
test("Serializing to JSON") {
    val g = createGraph
    val s = GraphJSON.toJSON(g)
    assert(s.length > 10)
  }

test("ParseJSON") {
    val g = createGraph
    val s = GraphJSON.toJSON(g)
    val g2 = GraphJSON.fromJSON(s)
    val s2 = GraphJSON.toJSON(g2)
    assert(s == s2)
  }
}
