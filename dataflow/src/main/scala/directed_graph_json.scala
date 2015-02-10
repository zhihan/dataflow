package me.zhihan.se

import my.se.Vertex
import my.se.Edge
import my.se.Graph

import scala.collection.immutable.Map
import net.liftweb.json._
import net.liftweb.json.JsonDSL._

object GraphJson {
  def vertex(v: Vertex): JObject =
    ("vertex" -> (
      ("id" -> v.id) ~
        ("sid" -> v.sid)))

  def edge(e: Edge): JObject =
    ("edge" -> (
      ("id" -> e.id) ~
        ("from" -> e.from.id) ~
        ("to" -> e.to.id) ))

  def graph(g: Graph): JObject = {
    val vertices = (g.V map vertex).toList
    val edges = (g.E map edge).toList
    ("graph" -> (
      ("V" -> vertices) ~
        ("E" -> edges)))
  }

  def toJson(g: Graph): String = compact(render(graph(g)))

  def fromJson(s: String): Graph = ???
}
