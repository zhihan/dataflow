package sl.ir.dim // Src classes 

import me.zhihan.se._
import scala.collection.mutable.Map
import scala.collection.mutable.ArrayBuffer

import me.zhihan.utility._

import net.liftweb.json._
import net.liftweb.json.JsonDSL._

abstract sealed class Port 

case class Inport(iport: Int, val id: Int) extends Port
{
  def json: JObject =
    ("id" -> id) ~
  ("iport" -> iport)

  def jsonString: String = compact(render(json))
}

object Inport {
  implicit val formats = DefaultFormats
  def fromObj(obj: JValue) = obj.extract[Inport]
}

case class Outport(oport:Int, val id:Int) extends Port 
{
  def json: JObject =
    ("id" -> id) ~ ("oport" -> oport)
  def jsonString: String =
    compact(render(json))
}

object Outport {
  implicit val formats = DefaultFormats
  def fromObj(obj: JValue) = obj.extract[Outport]
}

case class Block(
  val inputs: List[Inport],
  val outputs: List[Outport],
  val id: Int,
  val blockType: String,
  val name: String)
{
  def json: JObject = 
    ("blockType" -> blockType) ~
  ("name" -> name) ~
  ("inputs" -> inputs.map{_.json}) ~
  ("outputs" -> outputs.map{_.json}) ~
  ("id" -> id)

  def jsonString: String = compact(render(json))
}

/** Companion object of block class */
object Block {
  implicit val formats = DefaultFormats
  def fromObj(obj: JValue) = obj.extract[Block]
}

/**
  Each signal connect a source port to a destination
  inport. 
  */
case class Signal(val sourceId: Int,
  val destinationId: Int, val name:String)
{
  def json: JObject =
    ("sourceId" -> sourceId) ~
  ("destinationId" -> destinationId) ~
  ("name" -> name)
}

object Signal {
  implicit val formats = DefaultFormats
  def fromObj(obj: JValue) = obj.extract[Signal]
}


class Diagram {
  val blocks = ArrayBuffer[Block]()
  val signals = ArrayBuffer[Signal]()

  /** Compute an id to port map on demand. The callee need to make sure
    the blocks are not mutated. */
  def portMap = {
    val m = Map[Int, Port]()
    for (b <- blocks) {
      for (i <- b.inputs) {
        m += i.id -> i
      }
      for (o <- b.outputs) {
        m += o.id -> o
      }
    }
    m
  }

  /**
    Serialize the object to a JSON string
    */
  def json: JObject = {
    val blks = blocks.map(_.json).toList
    val sigs = signals.map(_.json).toList
    ("blocks" -> blks) ~
    ("signals" -> sigs)
  }

  def jsonString: String =
    compact(render(json))

}

object Diagram {
  case class DiagramJson(
    val blocks: List[Block],
    val signals: List[Signal]) {}

  implicit val formats = DefaultFormats

  def fromObj(obj: JValue) = {
    val bj = obj.extract[DiagramJson]
    val diagram = new Diagram()
    diagram.blocks ++= bj.blocks
    diagram.signals ++= bj.signals
    diagram
  }

}


