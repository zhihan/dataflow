package me.zhihan.utility

import org.scalatest.FunSuite 
import scala.collection.immutable.Set

class MapTest extends FunSuite {
  test("Bijective bi-map") {
    val m = new BiMap[Int,String]()
    m += (1->"1")
    m += (2->"2")

    assert(m.app(1) == "1")
    assert(m.rapp("1") == 1)

    assert(m.app(2) == "2")
    assert(m.rapp("2") == 2)

  }

  test("Surjective bi-map") {
    val m = new SurMap[String, Int]()
    m += ("1" -> 1)
    m += ("one" -> 1)
    m += ("2" -> 2)
    
    assert(m.app("1") == 1)
    assert(m.app("one") == 1)
    assert(m.rapp(1).contains("one") && m.rapp(1).contains("1"))
    
    assert(m.app("2") == 2)
    assert(m.rapp(2) == Set("2"))
  }
  
  
}
