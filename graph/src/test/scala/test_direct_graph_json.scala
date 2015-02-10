package me.zhihan.se

import scala.util.parsing.json._
import org.scalatest.FunSuite

class GraphJsonTest extends FunSuite {
  def createGraph = {
    val g = new Graph()
    val v1 = g.newVertex("a")
    val v2 = g.newVertex("b")
    val e = g.addEdge(v1, v2)
    g
  }
  test("Serializing to json") {
    val g = createGraph
    val s = GraphJson.toJson(g)
    assert(s.length > 10)
  }

  test("Parse json file") {
    val g = createGraph
    val s = GraphJson.toJson(g)
    val g2 = GraphJson.fromJson(s)
    val s2 = GraphJson.toJson(g2)
    assert(s == s2)
  }
}
