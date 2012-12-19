package my.utility

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
