package me.zhihan.se

import scala.collection.immutable.Map
import scala.util.parsing.json._

object GraphJSON {
  def vertex(v:Vertex):JSONObject = 
    JSONObject(Map("vertex" -> 
      JSONObject(Map("id" -> v.id, "sid" -> v.sid))))

  def edge(e:Edge): JSONObject = 
    JSONObject(Map("edge" -> 
      JSONObject(Map("id" -> e.id, "from" -> e.from.id, "to" -> e.to.id ))))

  def graph(g:Graph): JSONObject = {
    val vertices = JSONArray((for (v <- g.V) yield vertex(v)).toList)
    val edges = JSONArray((for (e <- g.E) yield edge(e)).toList)
    JSONObject(Map("graph" ->
      JSONObject(Map("V" -> vertices, "E" -> edges))))
  }


  def toJSON(g:Graph) = graph(g).toString()

  def fromJSON(s:String) = {
    def mapToVertex(m:Map[String,Any]) = {
      val content = m("vertex").asInstanceOf[Map[String, Any]]
      val id = content("id").asInstanceOf[Double].toInt
      val sid = content("sid").asInstanceOf[String]
      new Vertex(id, sid)
    }

    def mapToEdge(m:Map[String, Any], g:Graph) = {
      val content = m("edge").asInstanceOf[Map[String,Any]]
      val id = content("id").asInstanceOf[Double].toInt
      val fromId = content("from").asInstanceOf[Double].toInt
      val toId = content("to").asInstanceOf[Double].toInt
      val from = g.getV(fromId)
      val to = g.getV(toId)
      new Edge(id, from, to)
    }

    val obj = JSON.parseFull(s)
    obj match {
      case Some(objm) => {
        val m = objm.asInstanceOf[Map[String, Any]]
        val content = m("graph").asInstanceOf[Map[String, Any]]
        val vertices = content("V").asInstanceOf[List[Map[String,Any]]]
        val v = (for (x <- vertices) yield mapToVertex(x)).toList
        val g = new Graph()
        g.V ++= v
        val edges = content("E").asInstanceOf[List[Map[String,Any]]]
        val e = (for (x <- edges) yield mapToEdge(x, g)).toList
        g.E ++= e
        g
      }
      case _ => throw new RuntimeException("Wrong inputs")
          
    }


  }
  
}
