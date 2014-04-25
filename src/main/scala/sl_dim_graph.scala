/** Intermediate representation for understanding dimension
  propagation */

package sl.ir.dim // Dimension class

import my.se._
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer
import my.utility._
import scala.util.parsing.json.JSON
import scala.collection.immutable.{Map => IMap}

abstract sealed class Port 

case class Inport(port:Int, override val id:Int) extends Port with HasId
{
  def json = "{\"input\":" + port + "," + 
    "\"id\":" + id + "}"
}

object Inport {
  def fromMap(m: IMap[String, Any]) = {
    val port = m("input").asInstanceOf[Double].toInt
    val id = m("id").asInstanceOf[Double].toInt
    Inport(port, id)
  }
}

case class Outport(port:Int, override val id:Int) extends Port with HasId
{
  def json = "{\"output\":" + port + "," +
    "\"id\":" + id + "}"
}

object Outport {
  def fromMap(m: scala.collection.immutable.Map[String, Any]) = {
    val port = m("output").asInstanceOf[Double].toInt
    val id = m("id").asInstanceOf[Double].toInt
    Outport(port, id)
  }
}


abstract sealed class Block extends HasId
{
  def json: String
  def inputs: List[Inport]
  def outputs: List[Outport]
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
    "}" +
    "\n}"

  override def inputs = List(input)
  override def outputs = List(output)
}

object Block {
  // Create a block from map
  def fromMap(m:IMap[String, Any]):Block = {
    val blockType = m.keySet.head
    val content = m(blockType).asInstanceOf[
      IMap[String, Any]]
    val name = content("name").asInstanceOf[String]
    val inputs = content("inputs").asInstanceOf[List[
      IMap[String, Any]]]
    val outputs = content("outputs").asInstanceOf[List[
      IMap[String, Any]]]
    if (inputs.size == 1 && outputs.size == 1) {
      val i = Inport.fromMap(inputs.head)
      val o = Outport.fromMap(outputs.head)
      SISOBlock(i, o, 0, blockType, name)
    } else {
      // Not SISO
      throw new RuntimeException("Cannot construct block")
    }
  }
}


case class Signal(val source: Outport,
  val destination: Inport, val name:String)
{
  def json: String = 
    "{\"" + "signal" + "\":" + "{\n" +
      "\"name\":" + "\"" + name + "\",\n" +
      "\"source\":" + source.id + ",\n" +
      "\"destination\":" + destination.id + "\n" +
      "}\n" + "}"

}

object Signal {
  def fromMap(m:IMap[String, Any]) = {
    val c = m("signal").asInstanceOf[IMap[String, Any]]
    val name = c("name").asInstanceOf[String]
    val source = c("source").asInstanceOf[Double].toInt
    val destination = c("destination").asInstanceOf[Double].toInt
    (name, source, destination)
  }
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

  def json = {
    val s = blocks.map(_.json)
    val sig = signals.map(_.json)

    "{\"diagram\":" + "{\n" +
      "\"blocks\":" + "[" +
      s.mkString(",\n") + "]\n,\n" +
      "\"signals\":" + "[" +
      sig.mkString(",\n") + "]\n" +
      "}\n" + "}"
  }

  def fromMap(bdM: IMap[String, Any])  {
    val content = bdM("diagram").asInstanceOf[IMap[String,Any]]
    val blockMaps = content("blocks").asInstanceOf[List[IMap[String, Any]]]
    blockMaps.foreach{ bM =>
      val b = Block.fromMap(bM)
      blocks.append(b)
    }

    val pm = portMap
    val signalMaps = content("signals").asInstanceOf[List[IMap[String, Any]]]
    signalMaps.foreach { sM =>
      val (name, src, dst) = Signal.fromMap(sM)
      (portMap(src),portMap(dst)) match {
        case (o:Outport, i:Inport) => signals.append(Signal(o,i,name))
        case (_,_) => throw new RuntimeException("Invalid signal") 
      }
    }
  }

  /** Parse the json string 
    
    NOTE Scala default implementation parse all number types
    to double.
    
    */
  def fromString(s:String) {
    assert(blocks.isEmpty)
    assert(signals.isEmpty)

    val m = JSON.parseFull(s)
    m match {
      case None => throw new RuntimeException("Cannot parse json")
      case Some(x) => {
        val bdM = x.asInstanceOf[IMap[String,Any]]
        fromMap(bdM)
      }
    }
  }

}

