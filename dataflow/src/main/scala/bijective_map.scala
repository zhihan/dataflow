// Mutable bijective maps
package me.zhihan.utility

import scala.collection.mutable.Map

import scala.collection.immutable.Set

class BiMap[A,B] {
  val fwd = Map[A,B]()
  val bwd = Map[B,A]()

  def get(a:A) = fwd.get(a)
  def app(a:A) = fwd(a)
  
  def rget(b:B) = bwd.get(b)
  def rapp(b:B) = bwd(b)

  def +=(kv:(A,B)) {
    fwd += kv
    bwd += kv.swap
  }

  override def toString = "Bijective map: " + fwd.toString
}

class SurMap[A,B] {
  val fwd = Map[A,B]()
  val bwd = Map[B,Set[A]]()

  def get(a:A) = fwd.get(a)
  def app(a:A) = fwd(a)

  def rget(b:B) = bwd.get(b)
  def rapp(b:B) = bwd(b)

  def +=(kv:(A,B)) {
    fwd += kv
    if (!bwd.contains(kv._2)) {
      bwd += (kv._2 -> Set(kv._1))
    } else {
      bwd(kv._2) = bwd(kv._2) + kv._1
    }
  }
}
