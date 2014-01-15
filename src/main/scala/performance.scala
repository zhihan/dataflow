package my.performance

import my.se._
import scala.util.Random

object GraphGenerator {
  def random(n:Int) = {
    
  }

  def randomGraph(n:Int, m:Int) = {
    val g = new Graph()
    for (i <- 0 until n) {
      val _ = g.newVertex("")
    }
    val random = new Random()
    val vMap = g.V.map{ v => (v.id, v) }.toMap
    for (i <- 0 until m ) {
      val from = random.nextInt(n)+1
      val to = random.nextInt(n)+1
      g.addEdge(vMap(from), vMap(to))
    }
    g
  }

  def hashInE(g:Graph) = {
    
  }
}
