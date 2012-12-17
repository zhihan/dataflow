package my.ir.sim


/* Simulink domain makes several special assumptions
 *
 * 1) Every block method is a procedure call
 * 2) All variables are declared global
*/ 

import my.ir._
import my.se._
import my.ir.defuse._
import scala.collection.mutable.Map
import scala.collection.mutable.Set

abstract class BlockMethod
case class Step (body:List[Statement]) extends BlockMethod
{
}

case class Output(body:List[Statement]) extends BlockMethod
{
}

case class Update(body:List[Statement]) extends BlockMethod
{
}

case class Jacobian(body:List[Statement]) extends BlockMethod
{
}


// Analyze a function for 
object Analysis {
  
  def getSimState(s: Step, y:List[Var]) = {
    val (stepCFG, map) = Utility.createCFGForList(s.body)

    // Add a dummy initialize function
    map += (stepCFG.entry -> Noop(""))
    val g= stepCFG.graph
    val tops = g.succ(stepCFG.entry)
    assert(tops.length ==1 ) // entry node has one successor
    val top = tops.head
    // Add a loop transition 

    val scope = new VarScope()
    val yOutMap = Map[Var,Var]()
    y.foreach { v =>
      val newV = scope.newVar(v.name + "_dummy")
      yOutMap += (v -> newV)
    }
    val use = RenameVar.copyTo(yOutMap)
     
    val useExit = Utility.createAndEmbedBlock(use, 
					      g, 
					      map, 
					      stepCFG.exit)
    g.addEdge(useExit, top)

    val varsDefined = VarDefine(s.body)
    val simstate = varsDefined.filter( x => {
      val p = DefUse.analyzeLive(g, map, x)
      // A variable if live if it is live-out at the exit
      // and live-in at the top of the loop

      p.exit(stepCFG.exit) && p.entry(top)
    })
    simstate
  }

  def addOutputStateVar(s:Step, 
			y:List[Var], 
			x:List[Var],
			globalScope: VarScope): (Step,List[Var]) = {
    val ySet = Set[Var]() ++ y
    val xSet = Set[Var]() ++ x
    val yState = (ySet & xSet).toList
    val ySMap = Map[Var,Var]() 
    yState.foreach{ s =>
      val sGlobal = globalScope.newVar(s.name + "_state")
      ySMap += (s->sGlobal)		   
    }

    val init = RenameVar.copyFrom(ySMap)
    val store = RenameVar.copyTo(ySMap)
    
    val newX = (xSet -- ySet).toList ::: yState.map(ySMap(_))
    (Step( init ::: s.body ::: store), newX)
  }

  def createOutput(s:Step, x:List[Var]) = {
    val scope = new VarScope()
    val localMap = Map[Var,Var]()
    val mod = Set[Var]() ++ VarDefine(s.body)

    x.foreach{ s =>  
      if (mod contains s) {
	val sLocal = scope.newVar(s.name+"_local")
	localMap += (s -> sLocal)
      }
    } 
    val init = RenameVar.copyTo(localMap)
    val body = RenameVar(localMap, s.body)
    
    // Dead code elimination
    var newBody = init ::: body
    localMap.foreach{ kv =>
      val xLocal = kv._2
      newBody = DeadCode.removeDeadAssignment(newBody, xLocal)		     
    }

    val output = Output(newBody)
    (output, scope)
  }

  // 
  def createUpdate(s:Step, y:List[Var]) = {
    val scope = new VarScope()
    val localMap = Map[Var,Var]()
    val mod = Set[Var]() ++ VarDefine(s.body)

    y.foreach{ s => 
      if (mod contains s) {
	val sLocal = scope.newVar(s.name+"_local")
	localMap += (s -> sLocal)
      } 
    }
    val body = RenameVar(localMap, s.body)

    // Dead code elimination
    var newBody = body
    localMap.foreach{ kv =>
      val xLocal = kv._2
      newBody = DeadCode.removeDeadAssignment(newBody, xLocal)		     
    }

    val update = Update(newBody)
    (update, scope)
  }
}
