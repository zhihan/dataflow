package my.se

// A bus is a hierarchical data structure for propagating 
// dataflow dependence with the dataflow graph. 

import my.se._
import scala.collection.immutable.Set // Set of indices
import scala.collection.immutable.Range 
import scala.annotation.tailrec
import scala.collection.mutable.Map

class BusFactory {
  def newAtomicElement(name:String, width:Int) = AtomicElement(name, width)
  def newElementList() = List[BusElement]()
  def prepend(l:List[BusElement], a:BusElement) = a :: l
  def newBus(name: String, l:List[BusElement]) = Bus(name,l)
}

abstract sealed class BusElement 
{
  def size: Int
}

case class AtomicElement (val name: String, val width: Int) 
extends BusElement
{
  val size = 1
}

case class Bus (val name: String, val children:List[BusElement]) 
extends BusElement
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

  def size = nDescendants +1
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
           if (r.isEmpty || r.max < start) 
             (acc, accIncluded && l.isEmpty)
           else 
             l match {
               case h::t =>
                   h match {
                     case _:AtomicElement => 
                       if (r.contains(start))
                         cIds(start+1, acc+start, r, accIncluded, t)
                       else 
                         cIds(start+1, acc, r, false, t)
                     case b:Bus => {
                       val (s, all) = compactBus(b, start, r)
                       // val nR = r -- (start to start + b.nDescendants)
                       cIds(start + b.size, acc ++ s, r, accIncluded && all, t)
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
  
  def subsetOf(a:Set[Int], b:Set[Int]) = {
    val aLeaves = leavesIdOf(a)
    val bLeaves = leavesIdOf(b)
    aLeaves.subsetOf(bLeaves)
  }

  // Distribute the subset onto its children
  def distribute(s:Set[Int]) = {
    val leaves = leavesIdOf(s)
    @tailrec
    def rec(offset:Int, acc:List[Set[Int]], l:List[BusElement]):List[Set[Int]] = {
      l match {
        case h::t => {
          val myl = leaves.filter( x => (x >= offset && x < offset + h.size)
                                ).map(x => x-offset)
          rec(offset + h.size, myl :: acc, t)
        }
        case Nil => acc.reverse
      }
    }
    rec(1, List[Set[Int]](), children)
  }

  // Collect the elements from children
  def collect(cs:List[Set[Int]]):Set[Int] = {
    @tailrec 
    def rec(offset:Int, acc:Set[Int], s:List[Set[Int]],l:List[BusElement]):
    Set[Int] = {
      l match {
        case h::t => {
          val m = s.head.map(x => x + offset)
          val nextAcc = acc ++ m
          rec(offset + h.size, nextAcc, s.tail, t)
        }
        case Nil => acc
      }
    }
    assert(cs.length == children.length)
    rec(1, Set[Int](), cs, children)
  }

  // Make a SubBus out of a SubBus object who correspond to the i-th child
  // of the current bus. 
  def singleton(i:Int, c:Set[Int]): Set[Int] = {
    @tailrec
    def rec(offset:Int, number:Int, l:List[BusElement]): Set[Int] = {
      number match {
        case x if x==i  => c.map( x=> x+offset)
        case n if n < i => {
          l match {
            case h::t =>
              rec(offset + h.size, n+1, t)
            case _ => throw new RuntimeException("Cannot happen")
          }
        }
        case _ => throw new RuntimeException("Cannot haxpen")
      }
    }
    rec(1, 1, children)
  }

  // For the subset of a descendant, the subset from the 
  // root is simply calculated by adding the dfs numbers.
  def fromDescendant(i:Int, c:Set[Int]): Set[Int] = {
    c.map(x => x + i)
  }

  // Calculate the subset of descendant i
  def toDescendant(i:Int, c:Set[Int]): Set[Int] = {
    val x = intersect(Set(i), c)
    x.map(e => e -i)
  }

}

// Sub bus record, use it to propagate sub-bus reachability
// information in the dataflow algorithm
case class SubBus(val bus: Bus, val elements:Set[Int]) {
  def distribute = {
    val e = bus.distribute(elements)
    bus.children.zip(e)
  }
}


object SubBusOp extends SetOp[SubBus] {
  def comparable(l:SubBus, r:SubBus) = {
    l.bus == r.bus
  }

  def isSubset(l:SubBus, r:SubBus) = {
    assert(comparable(l,r))
    l.bus.subsetOf(l.elements,r.elements)
  }

  def empty(b:SubBus) = SubBus(b.bus, Set[Int]())
  def union(l:SubBus, r:SubBus) = {
    assert(comparable(l,r))
    val e = l.bus.union(l.elements,r.elements)
    SubBus(l.bus, e)
  }  
}

/* SL Bus behavior */
// Note the following data structure should not use index to refer to 
// the graphical objects.
sealed abstract class BusAction
// Regular object should also support pattern matching
case class BusCreate(bus:Bus, children:List[Int]) extends BusAction

case class BusPass(bus:Bus, in:List[Int], out:List[Int]) extends BusAction

// Each BusSelect object has a mapping associated with it
// where selection: (v.id -> sub-bus id) is the selection map 
case class BusSelect(val bus:Bus, val selection:Map[Int,Int]) extends BusAction

// Each BusAssidn object has a mapping associated with it
// where assignment: (v.id -> sub-bus id) is the overriding map 
case class BusAssign(val bus:Bus, val srcInput:Int, 
		     val assignment:Map[Int, Int]) extends BusAction

// Bus selector block becomes virtual, the bus selection action is
// then associated with an edge.
case class VBusSelect(val bus:Bus, val i:Int)

class BusActionFactory {
  def busCreate(bus: Bus, children: Array[Int]) = 
    if (children != null) 
      BusCreate(bus, children.toList) 
    else
      BusCreate(bus, List[Int]())

  
  def busActionMap(procIds: Array[Int], 
		   actions: Array[BusAction] ) = Map[Int,BusAction]() ++ procIds.zip(actions)
    
  def vBusSelect(bus: Bus, element: Int) = VBusSelect(bus, element)

  def vBusSelectMap(edgeIds: Array[Int],
		    busActions: Array[VBusSelect]) = 
		      Map[Int,VBusSelect]() ++ edgeIds.zip(busActions)

}
