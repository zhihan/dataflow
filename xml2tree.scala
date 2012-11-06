import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.DocumentBuilder
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.Element
import java.io.File

class XML2Tree() {
  def getID(n: Element) = {
    val s = n.getAttribute("Id")
    s.toInt
   }

  def getType(n: Element) = {
    val region = n.getElementsByTagName("region")
    val e: Element = region.item(0).asInstanceOf[Element]
    e.getAttribute("type")
  }

  def parseNode(n: Element) : TreeNode = {
    val id = getID(n)
    val t = getType(n)
    var children = List[TreeNode]() 
    val cNode = n.getElementsByTagName("children")
    if (cNode.getLength() > 0) {
      val cElement = cNode.item(0).asInstanceOf[Element]
      val nList = cElement.getChildNodes()
      for (i <- 0 until nList.getLength()) {
	if (nList.item(i).getNodeType() == Node.ELEMENT_NODE) {
	  val e:Element = nList.item(i).asInstanceOf[Element]
	  children =  parseNode(e) :: children
	}
      }
    }
    new TreeNode(id, children, t)
  }

  def parse(filename: String) = {
    var t = new TreeNode(0, List[TreeNode](), "")
    try {
      val f = new File(filename)
      val dF = DocumentBuilderFactory.newInstance()
      val dB = dF.newDocumentBuilder()
      val doc = dB.parse(f)
      val graph = doc.getElementsByTagName("digraph")
      val nList = graph.item(0).getChildNodes()

      var i = 0
      while (nList.item(i).getNodeType() != Node.ELEMENT_NODE) {
	i +=1
      }
      
      t = parseNode(nList.item(i).asInstanceOf[Element])
    } catch {
      case e:Exception =>
	e.printStackTrace()
    }
    t
  }
}

object TestTreeParser {
  def main(args: Array[String]) {
    val p = new XML2Tree()
    val t = p.parse(args.apply(0))
    println("Finished")
  }
}

object TestGetChildren {
  def main(args: Array[String]) {
    val p = new XML2Tree()
    val t = p.parse(args.apply(0))
    val filter = args.apply(1)
    val str = filter.slice(1,filter.length()-1).split(',')
    val s = str.map( s => s.toInt)

    val n = t.getDescendantIDs(s)

    n.foreach( v => println(v))
  }

}

