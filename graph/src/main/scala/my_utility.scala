package my.utility


trait HasId 
{
  def id:Int  // A simple trait specifying it has an Id field.
}

// A simple symbol generation system.
//
// Generates 1, 2, 3, ...
class Gensym () {
  var id = 0
  def apply() = {
    id = id+1
    id
  }
  def copy() = {
    val a = new Gensym()
    a.id = id
    a
  }
}
