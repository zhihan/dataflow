// Mutable bijective maps
package my.utility

import scala.collection.mutable.Map

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
