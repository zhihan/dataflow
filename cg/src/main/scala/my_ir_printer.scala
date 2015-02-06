package my.ir
import org.stringtemplate.v4._

 //
class Print (format:String = "matlab")
{
 
 val lib = format match {
   case "matlab" => 
     new STGroupFile(
       getClass.getResource("/MATLAB.stg").getFile())
   case "myir" =>
     new STGroupFile(
       getClass.getResource("/myir.stg").getFile())
 }

 def Var(v:Var) = 
   {
     
     val st = lib.getInstanceOf("var")
     st.add("v", v)
     st.render()
   }
 
 def Exp(e:Exp) = 
   {
    val st = lib.getInstanceOf("expression")
    st.add("e", e)
    st.render()
  }
 
 def DataType(d:DataType) = 
   {
    val st = lib.getInstanceOf("datatype")
    st.add("te", d)
    st.render()
  }
 
 def Stmt(e:Statement) = 
   {
    val st = lib.getInstanceOf("stmt")
    st.add("s", e)
    st.render()
  }

  def Procedure(p:Procedure) = 
  {
    val st = lib.getInstanceOf("procedure")
    st.add("p", p)
    st.render()
  }
}

