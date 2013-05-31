package my.se

import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.JavaConversions._ 

import my.utility._ // Gensym

/* TreeNode
 * A Tree node in the program structure tree
 *
 * */
class TreeNode( i: Int, c: List[TreeNode]) {
  val id = i
  var children = c  // mutable

  // Get all descendant 
  private def getAllDescendants():List[TreeNode] = {
    val m = children.map( c => c.getAllDescendants() )
    this :: m.foldLeft(List[TreeNode]()) ((b,a) => b ::: a)
  }

  // Get all descedants of a a set of nodes
  private def getDescendants(s: Set[Int]):List[TreeNode] = {
    if (s.contains(id)) {
      getAllDescendants()
    } else {
      val m = children.map( c => c.getDescendants(s) )
      m.foldLeft(List[TreeNode]()) ((b,a) => b ::: a)
    }
  }

  //Get a node by its ID
  private def getNodeByID(which: Int):Option[TreeNode] = {
    if (id == which) {
      Some(this)
    } else {
      // Initialize 
      var found:Option[TreeNode] = None
      children.foreach(c => 
        c.getNodeByID(which) match {
          case Some(f) => {found = Some(f) }
          case None => {}
        }
      )
      found
    }
  }

  def getDescendantIDs(s: Array[Int]) = {
    if (s == null) {
      Array[Int]()
    } else {
      val result = this.getDescendants(s.toSet)
      val ids = result.map(v => v.id)
      ids.toArray
    }
  }

  def getAllDescendantIDs() = {
    val result = this.getAllDescendants()
    val ids = result.map(v => v.id)
    ids.toArray
  }

  def isChild(which: Int): Boolean = {
    val found = children.find( x => x.id==which) 
    found match {
      case Some(f) => true
      case None => false
    }
  }

  def isDescendant(which: Int) = {
    val t = getNodeByID(which)
    t match {
      case Some(v) => true
      case None => false
    }
  }

  def getChildrenIDs() = {
    val ids = children.map(c => c.id)
    ids.toArray
  }

  def addChild(t: TreeNode) {
    children = t::children
  }

  def getNode(which:Int) = {
    val t = getNodeByID(which)
    t match {
      case Some(v) => v
      case None => {throw new RuntimeException("Wrong ID")}
    }
  }

  private def dotString():String = {
    var result = id.toString() + "[label=\"" + id + "\"]\n"

    children.foreach(n => result = result + 
                     id.toString() + " -- " + n.id.toString() + "\n")
    children.foreach(n => result = result + n.dotString() )
    result
  }

  def toDotString() = " graph G {\n" + dotString() + " } "

  private def ancestorSubset(vec:Set[Int]): (Set[Int], Set[Int]) = {
    def excludeDescendants(v:TreeNode, s:Set[Int]) = {
      val c = v.getAllDescendantIDs()
      s &~ c.toSet
    }
    // If root is in vec
    if (vec.contains(id)) {
      (Set(id), excludeDescendants(this, vec))
    } else {
      var ancestors = Set[Int]()
      var remainders = vec
      children.foreach( c => {
        val (a,r) = c.ancestorSubset(remainders)
        ancestors = ancestors ++ a
        remainders = r
      })
      (ancestors, remainders)
    }
  }

  def computeAncestorSubset(vec:Array[Int]) = {
    if (vec == null) 
      Array[Int]()
    else {
      val s = vec.toSet
      val (a,r) = ancestorSubset(s)
      if (r.isEmpty) {
	a.toArray
      } else {
        println(vec)
	throw new RuntimeException("No subset found")
      }
    }
  }

  /** Remove nodes whose has descendant that is not in the set */
  private def removePartialAncestors(s:Set[Int]): Set[Int] = {
    def allDescendantContainedIn(i:Int, s:Set[Int]):Boolean = {
      val v = getNode(i)
      val c = v.getAllDescendantIDs()
      val cSet = c.toSet
      cSet.subsetOf(s)
    }
    s.filter(x => allDescendantContainedIn(x, s))
  }
  
  /** Remove nodes whose has descendant that is not in the set */
  def removePartiallyContainedAncestors(vec:Array[Int]):Array[Int] = {
    if (vec == null) 
      Array[Int]()
    else {
      val s = vec.toSet
      val r:Set[Int] = removePartialAncestors(s)
      r.toArray
    }
  }

  private def computeParentPair: List[(Int,Int)] = {
    val m = (for (c <- children) yield (c.id, this.id) )
    m ++ children.flatMap( c => c.computeParentPair)
  }

  private def computeParentMap = computeParentPair.toMap
  
  /** Compute the ancestors of a node using the parent map*/
  private def ancestors(m:Map[Int,Int], i:Int) = {
    def addParent(l:Set[Int], k:Int): Set[Int] = {
      if (m.contains(k)) 
        addParent(l + m(k), m(k))
      else
        l
    }
    addParent(Set[Int](), i)
  }
  
  def computeAncestors(vec:Array[Int]):Array[Int] = {
    val m = computeParentMap
    vec.flatMap(v => ancestors(m,v)).toSet.toArray
  }
}


class TreeNodeFactory () {
  val gensym = new Gensym()

  def make  = {
    new TreeNode(gensym(), List[TreeNode]())
  }
}

