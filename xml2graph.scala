import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.DocumentBuilder
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.Element
import java.io.File
import java.util.ArrayList

import scala.collection.mutable.HashMap
import scala.collection.JavaConversions._ 


class XML2Graph() {
  
  def getID(n: Element) = {
    val id = n.getElementsByTagName("VID")
    val e:Element = id.item(0).asInstanceOf[Element]
    val s = e.getAttribute("val")
    s.toInt
   }

  def getGID(n: Element) = {
    val s = n.getAttribute("id")
    s.toInt
   }
  
  def getSID(n: Element) = {
    val sid = n.getElementsByTagName("SID")
    var result = List[String]()
    for (i <- 0 until sid.getLength() ) {
      val e = sid.item(i).getChildNodes()
      val c = e.item(0)
      result = c.getNodeValue()::result
     } 
    result.toArray
  }
  
  def getElementAttr(n: Element, name: String, attr: String) = {
    val e = n.getElementsByTagName(name)
    val item = e.item(0).asInstanceOf[Element]
    item.getAttribute(attr)
  }

  def getSourceId(n: Element) = {
    val sid = getElementAttr(n, "source", "id")
    sid.toInt
  }

  def getDestinationId(n: Element) = {
    val tid = getElementAttr(n, "destination", "id")
    tid.toInt
  }

   def parse(filename: String) = {
     var V = new ArrayList[Vertex]()
     val vMap = HashMap.empty[Int, Vertex];
     var g = new Graph(V)

     // Add a hash table
     try {
       val f = new File(filename)
       val dF = DocumentBuilderFactory.newInstance()
       val dB = dF.newDocumentBuilder()
       val doc = dB.parse(f)
       val nList = doc.getElementsByTagName("vertex")
       
       for (i <- 0 until nList.getLength() ) {
	 var element = nList.item(i).asInstanceOf[Element]
         val gid = getGID(element)
         val vid = getID(element)
	 val v = new Vertex(gid, 
			    getSID(element))
         V.add(v)
         vMap(gid) = v
       }
       
       g = new Graph(V)

       val eList = doc.getElementsByTagName("edge")
       
       for (i <- 0 until eList.getLength()) {
         var element = eList.item(i).asInstanceOf[Element]
         val sourceId = getSourceId(element)
         val targetId = getDestinationId(element)
         g.addEdge(vMap(sourceId), vMap(targetId))
       }
     } catch {
       case e:Exception =>
	 e.printStackTrace()
     }
     g
   }
}

/** Tests 
 *
 *  
 */

object TestGraphParser {
  def main(args: Array[String]) {
    val p = new XML2Graph()
    val g = p.parse(args.apply(0))
    println("Finished") 
    g.print()
  }
}


object TestReach {
  def main(args: Array[String]) {
    val p = new XML2Graph()
    val g = p.parse(args.apply(0))
    
    g.print()
    println("Reachable from 0")
   
    val reach = new Reachable(g)
    val v = reach.forward(g.V(0))
    
    v.foreach( e => println(e.id))
  }
}


object TestReachFilter {
  def main(args: Array[String]) {
    val p = new XML2Graph()
    val g = p.parse(args.apply(0))
    val filter = args.apply(1)
    val str = filter.slice(1,filter.length()-1).split(',')
    val inactive = new Inactive(Array[Int](), str.map( s => s.toInt))
    
    g.print()
    println("Reachable from 0")
   
    val reach = new Reachable(g)
    val v = reach.forward(g.V(0), inactive)
    
    v.foreach( e => println(e.id))
  }
}
