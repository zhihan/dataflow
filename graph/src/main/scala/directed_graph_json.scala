package me.zhihan.se

import my.se.Vertex
import my.se.Edge
import my.se.Graph

import scala.collection.immutable.Map
import net.liftweb.json._
import net.liftweb.json.JsonDSL._

object GraphJson {
  def vertex(v: Vertex): JObject =
      ("id" -> v.id) ~
        ("sid" -> v.sid)

  def edge(e: Edge): JObject =
    ("id" -> e.id) ~ ("from" -> e.from.id) ~ ("to" -> e.to.id) 

  def graph(g: Graph): JObject = {
    val vertices = (g.V map vertex).toList
    val edges = (g.E map edge).toList
    ("V" -> vertices) ~ ("E" -> edges)
  }

  def toJson(g: Graph): String = compact(render(graph(g)))
  implicit val formats = DefaultFormats

  case class VertexJson(val id: Int, val sid:String) {}

  case class EdgeJson(val id: Int, val from:Int, val to:Int)

  case class GraphJson(val V: List[VertexJson],
    val E: List[EdgeJson]) {}
 
  def fromJson(s: String): Graph = {
    def vertex(vj: VertexJson) = {
      new Vertex(vj.id, vj.sid)
    }

    def edge(ej: EdgeJson, g:Graph): Edge = {
      val from = g.getV(ej.from)
      val to = g.getV(ej.to)
      new Edge(ej.id, from, to)
    }
 
    val obj = parse(s)
    val gObj = obj.extract[GraphJson]
    val vertices = gObj.V map vertex

    val g = new Graph()
    g.V ++= vertices

    val edges = gObj.E map { edge(_, g)}
    g.E ++= edges
    g
  }
}
