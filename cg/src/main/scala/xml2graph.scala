package me.zhihan.se

import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.DocumentBuilder
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.Element
import java.io.File
import java.util.ArrayList

import scala.collection.mutable.HashMap
import scala.collection.JavaConversions._ 

import me.zhihan.se._

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
    var result = ""
    for (i <- 0 until sid.getLength() ) {
      val e = sid.item(i).getChildNodes()
      val c = e.item(0)
      result = result + c.getNodeValue()
     } 
    result
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

   def parseFile(filename: String) = {
     val vMap = HashMap.empty[Int, Vertex];
     var g = new Graph()

     var V = List[Vertex]()

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
         V = v :: V
         vMap(gid) = v
       }
       val fac = new GraphFactory()
       g = fac.make(V.toArray)

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

