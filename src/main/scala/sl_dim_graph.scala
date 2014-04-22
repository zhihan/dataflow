/** Intermediate representation for understanding dimension
  propagation */

package sl.ir.dim // Dimension class

import my.se._
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer
import my.utility._
import scala.util.parsing.json.JSON

abstract sealed class Port 

case class Inport(port:Int) extends Port
{
  def json = "{\"input\":" + port + "}"
}

object Inport {
  def fromMap(m: scala.collection.immutable.Map[String, Any]) = {
    val port = m("input").asInstanceOf[Double].toInt
    Inport(port)
  }
}

case class Outport(port:Int) extends Port
{
  def json = "{\"output\":" + port + "}" 
}

object Outport {
  def fromMap(m: scala.collection.immutable.Map[String, Any]) = {
    val port = m("output").asInstanceOf[Double].toInt
    Outport(port)
  }
}

abstract sealed class Block extends HasId
{
  def json: String
}

case class SISOBlock(val input:Inport,
  val output:Outport, val id:Int,
  val blockType: String, val name:String ) extends Block
{
  override def json = 
    "{" + "\"" + blockType + "\":" + "{\n" +
    "\"name\":" + "\"" + name + "\",\n" + 
    "\"inputs\": [" + input.json + "],\n" +
    "\"outputs\": [" + output.json + "]\n" + 
    "\n }" +
    "\n}"
}

object Block {
  def fromMap(m:scala.collection.immutable.Map[String, Any]):Block = {
    val blockType = m.keySet.head
    val content = m(blockType).asInstanceOf[
      scala.collection.immutable.Map[String, Any]]
    val name = content("name").asInstanceOf[String]
    val inputs = content("inputs").asInstanceOf[List[
      scala.collection.immutable.Map[String, Any]]]
    val outputs = content("outputs").asInstanceOf[List[
      scala.collection.immutable.Map[String, Any]]]
    if (inputs.size == 1 && outputs.size == 1) {
      val i = Inport.fromMap(inputs.head)
      val o = Outport.fromMap(outputs.head)
      SISOBlock(i, o, 0, blockType, name)
    } else {
      throw new RuntimeException("Cannot construct block")
    }
  }
}


case class Signal(val source: Outport,
  val destination: Inport)
{
  def json: String = ""
}


class Diagram {
  val blocks = ArrayBuffer[Block]()
  val signals = ArrayBuffer[Signal]()

  def json = {
    val s = blocks.map(_.json)
    s.mkString("")
  }

  /** Parse the json string 
    
    NOTE Scala default implementation parse all number types
    to double.
    */
  def parse(s:String) {
    assert(blocks.isEmpty)
    assert(signals.isEmpty)

    val m = JSON.parseFull(s)
    m match {
      case None => throw new RuntimeException("Cannot parse json")
      case Some(x) => {
        
      }
    }

  }

}
