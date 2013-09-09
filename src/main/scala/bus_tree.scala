package my.se

// A bus is a hierarchical data structure for propagating 
// dataflow dependence with the dataflow graph. 

import my.se._
import scala.collection.immutable.Set // Set of indices
import scala.collection.immutable.Range 
import scala.annotation.tailrec

abstract sealed class BusElement 

case class AtomicElement (val name: String, val width: Int) extends BusElement
{
}

case class Bus (val name: String, val children:List[BusElement]) extends BusElement
{
  lazy val width:Int = {
    children.foldLeft (0) {
      (acc:Int, c:BusElement) => acc + (
        c match {
          case a:AtomicElement => a.width
          case b:Bus => b.width
        }) 
    }
  }

  lazy val nChildren = children.length
  lazy val nDescendants:Int = 
    children.foldLeft (0) {
      (acc, c) => acc + (
        c match {
          case _:AtomicElement => 1
          case b:Bus => b.nDescendants + 1
        }
      )
    }
  lazy val size = nDescendants +1

  /* Indexing: bus elements are assumed to be DFS-indexed,
   * this allows use of a single id to find the element */
  def get(n:Int) : BusElement = {
    def find(n:Int, l:List[BusElement]):BusElement = {
      val h = l.head
      (h,n) match {
        case (b:Bus, _) if n < b.size  =>
          b.get(n)
        case (b:Bus, _) =>
          find( n - b.size, l.tail)
        case (a:AtomicElement, 0) =>
          h 
        case (_:AtomicElement, n) =>
          find(n-1, l.tail)
      }
    }

    if (n > nDescendants+1) {
      throw new RuntimeException("Index larger than number of descendants")
    } else {
      n match {
        case 0 => this
        case 1 => children.head
        case _ if n < 0 => throw new RuntimeException("Index less than 0")
        case _ => find(n-1, children)
      }
    }
  }

  def isDescendant(c:Int, p:Int) = {
    var pb = get(p)
    pb match {
      case b: Bus => (c > p && c <= p + b.nDescendants)
      case _: AtomicElement => false
    }
  }

  def leavesId :Set[Int] = {
    @tailrec
    def leafIds(start:Int, acc:Set[Int], l:List[BusElement]): Set[Int] = {
      l match {
        case h::t =>
          h match {
            case _:AtomicElement => leafIds(start+1, acc+start, t )
            case b:Bus => leafIds(start + b.size, acc ++ lId(b, start), t)
          }
        case Nil => acc
      }
    }
    // Local function for recursion
    def lId(b:Bus, n:Int):Set[Int] = leafIds(n+1, Set[Int](), b.children)

    lId(this, 0)
  }

  def descendantsId(start:Int):Range = start+1 to start + nDescendants
    

  // A set of sub bus elements are represented as set of indices
  def leavesIdOf(s:Set[Int]) = {
    @tailrec
    def leafIds(start:Int, acc:Set[Int], 
                sel:(Int)=>Boolean, l:List[BusElement]): Set[Int] = {
      l match {
        case h::t =>
          h match {
            case _:AtomicElement => 
              if (sel(start)) 
                leafIds(start+1, acc+start, sel, t)
              else
                leafIds(start+1, acc, sel, t)
            case b:Bus => 
              if (sel(start))
                leafIds(start + b.size, acc ++ allIds(b, start), sel, t)
              else
                // Continue to recurse into sub structure
                leafIds(start + b.size, acc ++ selIds(b, start, sel), sel, t)
          }
        case Nil => acc
      }
    }


    // Local function for recursion
    def allIds(b:Bus, n:Int):Set[Int] = leafIds(n+1, Set[Int](), 
                                                (_:Int)=> true, b.children)
    def selIds(b:Bus, n:Int, sel:(Int)=>Boolean) = 
      leafIds(n+1, Set[Int](), sel, b.children)

    // Special case:
    // If s contains 0, return all elements
    if (s.contains(0)) 
      leavesId
    else
      leafIds(1, Set[Int](), (x:Int)=> s.contains(x), children )
  }

  
  // Expand the set to all indices
  def expand(s:Set[Int]) = {
    @tailrec
    def allIds(start:Int, acc:Set[Int], 
                sel:(Int)=>Boolean, l:List[BusElement]): Set[Int] = 
      l match {
        case h::t =>
          h match {
            case _:AtomicElement => 
              if (sel(start)) 
                allIds(start+1, acc+start, sel, t)
              else
                allIds(start+1, acc, sel, t)
            case b:Bus => 
              if (sel(start))
                allIds(start + b.size, acc ++ (
                  start to start + b.nDescendants),
                        sel, t)
              else 
                allIds(start + b.size, acc ++ expandBus(b, start, sel), sel, t)
          }
        case Nil => acc
      }
    

    def expandBus(b:Bus, start:Int, sel:(Int)=>Boolean ):Set[Int] = {
      // After recursion, decide whether bus is included
      val res = allIds(start+1, Set[Int](), sel, b.children)
      val all = (start+1 to start + b.nDescendants).forall{
        (x:Int) => res.contains(x)
      }
      if (all) res + start else res
    }

    if (s.contains(0)) 
      (0 to size-1).toSet
    else {
      val res = allIds(1, Set[Int](), (x:Int)=>s.contains(x), children)
      val all = (1 to nDescendants).forall{
        (x:Int) => res.contains(x)
      }
      if (all) res + 0 else res
    }
  }

  // Compact: if all children is listed, only store the root
  def compact(s:Set[Int]) = {
    @tailrec 
    def cIds(start:Int, acc:Set[Int], r:Set[Int], 
         accIncluded:Boolean, l:List[BusElement]): (Set[Int], Boolean) = 
           if (r.isEmpty) 
             (acc, accIncluded && l.isEmpty)
           else 
             l match {
               case h::t =>
                   h match {
                     case _:AtomicElement => 
                       if (r.contains(start))
                         cIds(start+1, acc+start, r-start, accIncluded, t)
                       else 
                         cIds(start+1, acc, r, false, t)
                     case b:Bus => {
                       val (s, all) = compactBus(b, start, r)
                       val nR = r -- (start to start + b.nDescendants)
                       cIds(start + b.size, acc ++ s, nR, accIncluded && all, t)
                     }
                   }
               case Nil =>
                 (acc, accIncluded)
             }

    def compactBus(b:Bus, start:Int, r:Set[Int]): (Set[Int],Boolean) = {
      if (r.contains(start)) 
        (Set(start), true)
      else {
        val (s, all) = cIds(start+1, Set[Int](), r, true, b.children)
        if (all) (Set(start), true) else (s,false)
      }
    }
    
    val (res, _) = compactBus(this, 0, s)
    res
  }

  // Subset operations
  // Set union
  def union(a:Set[Int], b:Set[Int]): Set[Int] = {
    // Use leaf representation for the computation
    val aLeaves = leavesIdOf(a)
    val bLeaves = leavesIdOf(b)
    aLeaves ++ bLeaves
  }

  // Set intersect
  def intersect(a:Set[Int], b:Set[Int]): Set[Int] = {
    // Use leaf representation for the computation
    val aLeaves = leavesIdOf(a)
    val bLeaves = leavesIdOf(b)
    aLeaves & bLeaves
  }
  // Set difference
  def diff(a:Set[Int], b:Set[Int]): Set[Int] = {
    // Use leaf representation for the computation
    val aLeaves = leavesIdOf(a)
    val bLeaves = leavesIdOf(b)
    aLeaves -- bLeaves
  }
  // Set compliment
  def compl(a:Set[Int]) = diff(Set(0), a)
  

}

object Bus {
}
