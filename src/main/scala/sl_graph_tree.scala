package sl.ir

import my.se._
import sl.ir._
import scala.collection.mutable.Map


/* Tree of virtual subsystems
 *
 */

class SystemInfo(val name: String)
{
}

class SystemNode(val t:TreeNode)
{
  def id = t.id
  def addChild(c: SystemNode) {
    t.addChild(c.t)
  }


  def toDotString(nameMap:Map[Int,SystemInfo]) = {
    writeGraphviz.tree(t, 
                  {x:TreeNode => 
      "[label=\"" + nameMap(x.id).name + "\"]"}
    )
  }
  
  def computeAncestorSubset(vec:Array[Int]) = {
    val cIds = t.getAllDescendantIDs 
    val vecSub = vec.toSet.intersect(cIds.toSet).toArray
    t.computeAncestorSubset(vecSub)
  }
}

class SystemNodeFactory {
  val tf = new TreeNodeFactory()
  val sysInfo = Map[Int, SystemInfo]()

  def make(n:String):SystemNode = {
    val newTree = tf.make
    val node = new SystemNode(newTree) 
    sysInfo += (node.id -> new SystemInfo(n))
    node
  }

  def make(names:Array[String]):Array[SystemNode] = {
    names.map(x => this.make(x))
  }
  
}

