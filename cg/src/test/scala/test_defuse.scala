import my.ir._
import my.se._
import my.ir.defuse._

import scala.collection.mutable.Map

import org.scalatest.FunSuite

package my.ir.Test {
  class DefUseSuite extends FunSuite {
    test("Test VarUse function") {
      val x = Var("x", 1)
      val y = Var("y", 2)
      val x_p_y = BinExp(OpPlus(),
			  Deref(x), Deref(y))
      val u = VarUse(x_p_y)
      //println("The use in x + y is ")
      // u.foreach( arg => println(arg.name))
      assert(u.length == 2)
    }
    
    test("Test dataflow equation") {
      val u = Var("u",1)
      val y = Var("y",2)
      val x = Var("x",3)

      val input = Assignment(RefVar(u), 
			     Const(FloatValue(0.0)))
      val init = Assignment(RefVar(x), 
			    Const(FloatValue(0.0)))
      
      val output = Assignment(RefVar(y), 
			      BinExp(OpPlus(), 
				      Deref(x), Deref(u)))
      val update = Assignment(RefVar(x), 
			      BinExp(OpPlus(), 
				      Deref(x), Deref(u)))
      
      val g = new Graph()
      val v1 = g.newVertex("1")
      val v2 = g.newVertex("2")
      val v3 = g.newVertex("3")
      val v4 = g.newVertex("4")

      val n1 =  Noop("No-op")

      val e12 = g.addEdge(v1,v2)
      val e23 = g.addEdge(v2,v3)
      val e34 = g.addEdge(v3,v4)
      val e42 = g.addEdge(v4,v2)
  
      val s = new CFGMap
      s.add(v1, init)
      s.add(v2, input)
      s.add(v3, output)
      s.add(v4, update)
      
      var p = DefUse.analyzeLive(g, s, x)
      /*println(" X Liveness: ")
      println(" Entry:")
      println(writeGraphviz(g, (v => BooleanMapSet.label(v, p.entry))))
      println(" Exit:")
      println(writeGraphviz(g, (v => BooleanMapSet.label(v, p.exit))))
      */
      assert(p.entry.size == 3)
      assert(p.exit.size == 4)

      p = DefUse.analyzeLive(g, s, y)
      assert(p.entry.size == 0)
      assert(p.exit.size == 0)
      /*println(" Y Liveness: ")
      println(" Entry:")
      println(writeGraphviz(g, (v => BooleanMapSet.label(v, p.entry))))
      println(" Exit:")
      println(writeGraphviz(g, (v => BooleanMapSet.label(v, p.exit))))
      */
      p = DefUse.analyzeLive(g, s, u)
      assert(p.entry.size == 2)
      assert(p.exit.size == 2)
      /*println(" U Liveness: ")
      println(" Entry:")
      println(writeGraphviz(g, (v => BooleanMapSet.label(v, p.entry))))
      println(" Exit:")
      println(writeGraphviz(g, (v => BooleanMapSet.label(v, p.exit))))
      */
      
    }
  }
}
