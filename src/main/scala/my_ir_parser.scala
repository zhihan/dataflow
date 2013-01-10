package my.ir //

import my.utility._ // Gensym

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import org.antlr.runtime._
import org.antlr.runtime.tree._

class VarScope () {
  val varTable:Map[String, Var] = Map()
  val gensym = new Gensym()
  def newVar(name:String) = {
    if (varTable contains name) {
      throw new IllegalArgumentException("Name exists")
    } else {
      val v = Var(name, gensym())
      varTable(name) = v
      v
    }
  }
  def getVar(name:String) = {
    if (varTable.contains(name)) {
      varTable(name)
    } else { 
      val v = Var(name, gensym()) 
      varTable += (name -> v)
      v
    }
  }
  def copy() = {
    val c = new VarScope()
    c.gensym.id = gensym.id
    c.varTable ++= varTable
    c
  }
}


package conversion {
  class ConvertingVisitor ()
  {
    val adaptor = new CommonTreeAdaptor()
    val varScope = new VarScope()

    def visitVar(t:AnyRef) = 
    {
      val node = t.asInstanceOf[CommonTree]
      val name = node.getText
      varScope.getVar(name)
    }

    def visitType(t: AnyRef) = 
    {
      val node = t.asInstanceOf[CommonTree]
      println(node.getText)
      println("Don't know what to do yet")
    }

    def visitExpr(t: AnyRef):Exp = 
    {
      val node = t.asInstanceOf[CommonTree]
      node.getType() match {
        case CGELLexer.ID => {
	  
	  val v = visitVar(node)
	  Ref(v)
	}
        case CGELLexer.BINARY_OPERATOR => {
          val v1 = visitExpr(adaptor.getChild(t, 0))
          val v2 = visitExpr(adaptor.getChild(t, 1))
	  val op = node.getText() match {
	    case "+" => OpPlus()
	    case "*" => OpMul()
	  }
	  BinExp(op, v1, v2)
        }
        case CGELLexer.FUNCTION_CALL => {
          val n = adaptor.getChildCount(t)
          assert(n >= 1)
          val nameNode = adaptor.getChild(t, 0).asInstanceOf[CommonTree]
          val name = nameNode.getText()
          if (n == 1) {
            Function(name, List[Exp]())
          } else {
            val args = ListBuffer[Exp]()
            for (i <- 1 until n) {
              val x = visitExpr(adaptor.getChild(t, i))
              args.append(x)
            }
            Function(name, args.toList)
          }
        }
        case _ => node.getText() match {
          case "@" => {
	    val v =visitVar(adaptor.getChild(t,0))
	    Deref(v)
	  }
        }
      }
    }

    def visitStatement(t:AnyRef):Statement = {
      val node = t.asInstanceOf[CommonTree]
      node.getType() match {
        case CGELLexer.IF_ELSE => 
          {
            val e = visitExpr(adaptor.getChild(t,0))
            val b1 = visitBlock(adaptor.getChild(t,1))
            val b2 = visitBlock(adaptor.getChild(t,2))
            IfElse(e,b1,b2)
          }
	case CGELLexer.WHILE => 
	  {
	    val e = visitExpr(adaptor.getChild(t, 0))
	    val body = visitBlock(adaptor.getChild(t, 1))
	    While(e, body)
	  }
        case _ =>
        node.getText() match {
          case "var" => {
	    Noop("var_decl")
          }
          case "=" => {
            val lhs = visitVar(adaptor.getChild(t,0))
            val rhs = visitExpr(adaptor.getChild(t,1))
	    Assignment(RefVar(lhs), rhs)
          }
        }
      }
    }

    def visitBlock(t:AnyRef) = {
      val body = t.asInstanceOf[CommonTree]
      val l = ListBuffer[Statement]()
      for (i <- 0 until adaptor.getChildCount(body)) {
        val child = adaptor.getChild(body, i)
        val v = visitStatement(child)
	l append v
      }
      l.toList
    }

    def visitFunction(t:AnyRef) = {
      // Syntax-directed actions
      val body = adaptor.getChild(t,2)
      val l = visitBlock(body)
      val node = t.asInstanceOf[CommonTree]
      val name = node.getText()
      
      Procedure(l, name)

    }
    

    def visit(t:AnyRef) =   
    {
      val isNil = adaptor.isNil(t)
      (visitFunction(t), varScope)
    }
  }

  class ParseAndCreateIR () {
    private def parseSS(s: ANTLRStringStream) = {
      // Parse
      val lexer = new CGELLexer(s)
      val tokens = new CommonTokenStream(lexer)
      val parser = new CGELParser(tokens)

      val parseResult = parser.function()
      val t = parseResult.getTree().asInstanceOf[CommonTree]
      
      val visitor = new ConvertingVisitor()
      visitor.visit(t)
    }

    def parse(a:String) = {
      val input = new ANTLRStringStream(a)
      parseSS(input)
    }
    def parseFile(a:String) = {
      val input = new ANTLRFileStream(a)
      parseSS(input)
    }
  }
}


