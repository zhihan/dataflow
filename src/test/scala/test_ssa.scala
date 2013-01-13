import my.se._
import my.ir._
import org.scalatest.FunSuite

package my.ir.test.SSATest {
  class Suite extends FunSuite {
    test("Insert phi function") {
      val g = new Graph()
      val m = new CFGMap()
      val i = Var("i",1)
      val j = Var("j",2)
      val k = Var("k",3)
      val v1 = g.newVertex("1")
      m.add(v1, List(
	Assignment(RefVar(i), Const(IntValue(1))),
	Assignment(RefVar(j), Const(IntValue(1))),
	Assignment(RefVar(k), Const(IntValue(0)))))
      val v2 = g.newVertex("2")
      m.add(v2, IfElse(
	BinExp(OpLt(), Ref(k), Const(IntValue(100))),
	List(),
	List()))
      val v3 = g.newVertex("3")
      m.add(v3,  IfElse(
	BinExp(OpLt(), Ref(j), Const(IntValue(20))),
	List(),
	List()))
      val v4 = g.newVertex("4")
      m.add(v4, Call(Function("return", List(Ref(j)))))
      val v5 = g.newVertex("5")
      m.add(v5, List(
	Assignment(RefVar(j), Ref(i)),
	Assignment(RefVar(k), 
		   BinExp(OpPlus(),
			  Ref(k),
			  Const(IntValue(2))))))
      val v6 = g.newVertex("6")
      m.add(v6, List(
	Assignment(RefVar(j), Ref(k)),
	Assignment(RefVar(k), 
		   BinExp(OpPlus(),
			  Ref(k),
			  Const(IntValue(2))))))
      val v7 = g.newVertex("7")
      m.add(v7, Noop("exit"))
      
      g.addEdge(v1, v2)
      g.addEdge(v2, v3)
      g.addEdge(v2, v4)
      g.addEdge(v3, v5)
      g.addEdge(v3, v6)
      g.addEdge(v5, v7)
      g.addEdge(v6, v7)
      g.addEdge(v7, v2)

      /*println(writeGraphviz(g, 
			    vw = {v:Vertex=> 
				  m.getStatements(v).mkString(",")}))
       */
				     

      val t = new TarjanDominators()
      val idom =  t.compute(g, v1)
      // println("I-Dom")
      // idom.foreach{ e => println(e._1.sid + "->" +e._2.sid)}
      val df = t.dominanceFrontier(v1, idom, g)
      /*println("Dom-F")
      df.foreach{ e =>
	print(e._1.sid + ":")
	e._2.foreach{ x => print(x.sid) + " " }
		 println("")
	       }
      */	
      val ssa = new SSA()
      val (newM, phiMap) = ssa.insertPhi(g, m, df)
      // newM.block.foreach{ e=> println(e._1.sid + ":" + e._2)}
      // println("Phi function inserted")
      /* phiMap.foreach{ e => 
	println(e._1.sid + ":" + 
	      e._2.map(_.name).mkString(","))
		   } */
      val s = CFGPrint(g, newM)
      assert(s.length > 10)
      assert(phiMap(v2).length ==2)
      assert(phiMap(v7).length ==2)
    }
  }
}
