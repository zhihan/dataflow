package me.zhihan.se

import scala.collection.immutable.Set
import scala.collection.mutable.HashMap
import scala.collection.JavaConversions._ 

import me.zhihan.se._



// Set of integer as labels
class IntLabelOperation extends LabelOp[Set[Int]] {
  def lessThan(p1:Set[Int], p2:Set[Int]) = {
    p1 subsetOf p2
  }
  
  def max(p1: Set[Int], p2:Set[Int]) = {
    p1 union p2
  }
}


class IntLabel(graph:Graph, 
               op: IntLabelOperation) 
extends PropagateLabel[Set[Int]](graph,op) {
  type ValueT = Set[Int]
  type MapT = HashMap[Int, ValueT]

  def emptyLabel():HashMap[Int,ValueT] = {
    val out = HashMap[Int,Set[Int]]()
    graph.V.foreach( v => 
      out(v.id) = Set[Int]()
                  )
    out
  }

  def setLabel(l:MapT, i:Int, v:Array[Int]) {
    l(i) = v.toSet
  }

  def getLabel(l:MapT, i:Int): Array[Int] = {
    l(i).toArray
  }

}

