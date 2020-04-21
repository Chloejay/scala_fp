package scalaFunctions

object Hof{
  //compare with Python for classic wrapper function
//python 
/**def outside_function(f):
    def wrapper(a,b):
        return f(a)+ f(b)
    return wrapper 

def pow(x):
    return x*x
x= outside_function(pow)
print(x(1,2)) //should return 5
*/

//scala 
def outside_function(f: Int => Int):(Int, Int) => Int = {
  def wrapper(a:Int, b:Int):Int= f(a)+f(b)
  wrapper
}
 
//move parameters one level up
def outside_function(f: Int => Int, a:Int, b:Int):Int = {
  def wrapper:Int = f(a)+f(b)
  wrapper
}
//lambda f
val result = outside_function(a=>a*a, 1,2) 
//curry
def outside_function(f:Int => Int, a:Int)(b:Int):Int = {
  def wrapper: Int = f(a)+f(b)
  wrapper
}
val result2= outside_function(a => a*a, 1)(2)

/** in Python, use map, reduce, filter etc method make use of lambda function
list_= list(range(1,100)) 
print(list_)
print(list(map(lambda x: x+1, list_))) 
print(list(filter(lambda x: x%2!=0, list_)))  
from functools import reduce 
print(reduce(lambda x,y: x+y, list_))
*/

/** scala, is more tight and concise, use "_" save bytes*/ 
val list_ = 1 to 100 
//map 
println(list_.map(x => x+ 1))
println(list_ map (_+ 1))
//filter
println(list_.filter(x=> x % 2 != 0))
println(list_ filter (_%2 != 0))
//reduce 
println(list_.reduce((x,y) => x + y))
println(list_ reduce (_+_))

//scala filter case class 
case class Person(name: String, age: Int, city: String)
//pattern matching 
val person_ : PartialFunction[String, Int] = {case "chloe"=> 28; case "emma"=> 24; case _ => 0}
println(person_ ("chloe"))
println(person_ ("doc_who")) 

trait List[+A] {
  def filter(f: A => Boolean): List[A]
}
final case class Listing[A](v: A) extends List[A]{
  def filter(f: A => Boolean): List[A] = ???
}

sealed abstract class Maybe[+A]{
    def isEmpty: Boolean 
    def get: A
}
final case class Some[A](v: A) extends Maybe[A]{
    def isEmpty: Boolean = false
    def get = v 
}
final case object Null extends Maybe[Nothing]{
    def isEmpty: Boolean = true 
    def get = throw new NoSuchElementException("ban!") 
}
//recursive
def ++(v: List[Int]): Int = v match{
    case _ => 0
    case x::ys => x+ ++(ys)
}
}

object HofMain{
  def main(args: Array[String]): Unit = {
    val pow= (a: Int)=> a * a
    val result = Hof.outside_function(pow) 
    println(result(1,2)) 
    val person = List(Person("chloe", 28, "shanghai"), Person("emma", 24, "newyork")) 
    val person2 = Map("chloe"->28, "emma"->24)
    val filter2 = person2.filter((p:(String, Int)) => p._2 > 25)
    val filterCase = person2.filter{case(name, age) => age > 25} 
    println(filter2)
    println(filterCase)
  }
}