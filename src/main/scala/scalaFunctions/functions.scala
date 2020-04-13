package scalaFunctions

object basicCollections{
//basic collections methods 
//Array
val numbers= Array(1,2,3,4)
println(numbers(1)) 
//List 
val list= List(1,2,3,4)
//Set
val set= Set(1,2,3,4) 
//Tuple
val tuple= ("number",1234)
println(tuple._1)
println(tuple._2)

//simple tuple 
1->10
//range 
(1 until 10 by 2) 
(10 until 1 by -2) 
//for - comprehension
for (i<- 10 until 1  by -1) {println (s"$i is descending int")} 
for (i<- 1 until 10 by 2) {println(s"$i is odd int!")}
//Maps
Map(1->10) 
Map("name"->"Chloe", "age"->28)  
Map(1 -> Map("foo" -> "bar"))

//Option 
trait Option[T]{
    def isDefined:Boolean
    def get: T 
    def getOrElse(t: T): T 
}
//Option methods
val numbers= Map("name"->"Chloe", "age"->28)
numbers.get("name")//Some(Chloe)
numbers.get("age") //Some(28) 

val seqNum= Seq(1,2,3) 
val pow= (n:Int)=>n*n 
val result = seqNum map pow 

//foreach is similar to map, but return nothing 
val pow= seqNum.foreach(n => println(n*n)) 
//filter, remove wrong elements 
val filter= seqNum.filter((n:Int) => n%2 == 0)

//use filter for PartialFunction 
case class Person(name: String, age:Int)
val age = List(Person("chloe", 28), Person("emma", 24)) 
age.filter{case Person(name, age) => age>25} 

//zip 
List("Chloe", "Emma").zip(List(28,24))  
Seq(1,2).zip(Seq(1,2)) 

//partition 
val numbers= List(1,2,3,4)
numbers.partition(_ %2 != 0)//(List[Int], List[Int]) = (List(1, 3),List(2, 4))
//find
numbers.find((n:Int)=>n>4) //only return thr first elements of the qualified return 
//drop 
numbers.drop(2)//drop the first two elements, return the new val
numbers.dropWhile(_%2 == 0)
//foldLeft and foldRight
numbers.foldLeft(0){(a:Int, b:Int)=>println( "a"+a+"b"+b); a+b} //0 here is the starting pos 
//flatten 
List(List(1, 2), List(3, 4)).flatten //List[Int] = List(1, 2, 3, 4)

//flatMap, combines map and flatten
val nestedNumbers= List(List(1,2), List(10,20))
nestedNumbers.flatMap(n => n.map(_*2)) 

val raw_data = List(1,4,9,2)
val raw_data2 = List(2, 10, 11, 2)

val result= raw_data flatMap {a =>
raw_data2 flatMap {b => 
    if (a != b) Seq(a,b)
    else Seq() 
}}
//for expr 
val forResult = for {
  a <- raw_data
  b <- raw_data2
  if a != b
} yield (a, b)

//Map 
val people = Map("chloe"->28, "emma"->24)
people.filter((friends: (String, Int)) => friends._2 > 25) 
// case works as placeholder 
people.filter({case(n, a) => a>25})

def f(a:String) = "f("+ a +")"
def g(a:String) = "g(" + a + ")"
//compose 
val compose = f _ compose g _ 
compose("chloe") //String = f(g(chloe))

val f= (i: Int)=>i.toString 
val f2=(i: String)=> i+i 
val h= f2 compose f
h(0) 

//andThen, similar to compose 
val andThen = f _ andThen g _
andThen("emma") 

//Curry, partial function (subclass function)
// isDefinedAt() be used as condition to tell if function accept the given args 
val one : PartialFunction[Int, String] = {case 1 => "one"; case 2 => "two"}  
one.isDefinedAt(1)//true
one.isDefinedAt(2)//true

val friends: PartialFunction[String, Int] = {case "chloe" => 28; case "emma" => 24; case _ => 0} 
friends.isDefinedAt("chloe")
friends.isDefinedAt("emma")
friends("chloe")

//PartialFunction <-> composed 
val wildcard: PartialFunction[String, Int] = { case _ => 0} 
val partial = friends orElse wildcard 
//
val f: PartialFunction[Int, String] = {case i if i%2 == 0 => "even"}
val f2: (Int=> String)= f orElse {case _ => "odd"}
f2(1) 

def outside_function(f: Int => Int): (Int, Int) => Int = {
    def wrapper(a: Int, b:Int):Int = f(a) + f(b)
    wrapper
}
def pow(a: Int) = a *a 
val result = outside_function(pow)
println(result(1,2))//5 
val result_opt = outside_function((a: Int) => a*a)
val result_opt2 = outside_function(a => a*a)
}