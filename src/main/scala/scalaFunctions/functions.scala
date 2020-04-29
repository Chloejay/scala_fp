package scalaFunctions

import scala.collection.mutable.ListBuffer
import scala.collection.parallel.CollectionConverters._
// import scala.collection.parallel.immutable._

object dataStruct extends App{ 

  val arr = Array(1,2,3,4)
  val mutableArr = ListBuffer(1,2,3,4) 
  val numbers = List(1,2,3,4)
  val set = Set("a","b","c","d") //immutable 
  import scala.collection.mutable.{Set=> MutableSet} 
  val mutableSet = MutableSet("aa","bb","cc","dd") 
  val tuple = ("number",1234)
  val seqNum = Seq(1,2,3)
  val iter = Iterable(1, 2, 3, 4, 5)
  println(arr(1)) 
  // mutableArr(2) = 0 <-> mutableArr.update(2,0)
  //tuple 
  1->10
  (1 to 10).toList
  //range 
  (1 until 10 by 2) 
  (10 until 1 by -2) 
  // case class Tuple(t: T, t2: T)

  //4comprehension
  for (i<- 10 until 1  by -1) {println (s"$i is descending int")} 
  for (i<- 1 until 10  by 2)  {println(s"$i is odd int!")}
  Map(1->10) 
  Map("name"->"Chloe", "age"->28)  
  Map(1 -> Map("foo" -> "bar"))
  
  // trait Option[T]{
      // def isDefined:Boolean
      // def get: T 
      // def getOrElse(t: T): T 
  // }
  //Option getter 
  val people = Map(("name", "Chloe"), ("age", 28))// -> 
  people.get("name")
  people.get("age") 
  people.getOrElse("city", "default space is earth!")
  people += ("hobby"->"biking", "lang"-> "bytes")
  people ++= Map("hobby"->"biking", "lang"-> "bytes")
  people("lang") = "Scala"
  people.contains("lang") //true 
  people.take(2) 
  people.drop(4)
  for ((k,v) <- people if (v == "Chloe")) yield (k,v)

  val ppl = Map("c"-> "sh", "n"-> "li") 
  for ((name, city)<- ppl) println(s"$name lives $city") 
  
  val pow = n => n*n 
  val result = seqNum map pow  
  val identity = seqNum.foreach(println) 
  val pow = seqNum.foreach(n=> println(n*n))
  //predicate
  val filter = seqNum.filter(n => n%2 == 0)
  
  //PartialFunction 
  case class Person(name: String, age:Int)
  val age = List(Person("chloe", 28), Person("emma", 24)) 
  age.filter{case Person(name, age) => age>25} 
  
  //zip 
  List("Chloe", "Emma").zip(List(28,24))  
  for {a<- List("Chloe", "Emma"); b<- List(28,24)} yield (a,b) //or expand yield 
  Seq(1,2).zip(Seq(1,2)) 
  
  set contains("a")
  val set2 = set - "a"
  set ++ set2

  mutableSet -= "aa"
  mutableSet += ("ee") 
  mutableSet.clear()

  val add3 = 3 :: numbers
  val add3_ = numbers :+ 3
  
  numbers match {
    case f::s::l => List(s,l)
    case _ => None 
  }
  numbers map { _ * 10} filter { _ > 10}
  //partition
  numbers.partition(_ %2 != 0) 
  //find
  numbers.find(n => n>4) //only return the first element of the qualified return 
  //drop 
  numbers.drop(2)//drop the first two elements
  numbers.dropWhile(_ % 2 == 0)
  //foldLeft and foldRight
  numbers.foldLeft(0){_ + _} 
  numbers.foldRight(-10){_ + _ }
  //flatten 
  List(numbers).flatten //map-> flatten <=> flatMap
  
  iter dropRight(2)
  iter takeRight(2)
  
  //flatMap
  val nestedNumbers = List(List(1,2), List(10,20))
  nestedNumbers.flatMap(n => n.map( _ * 2))  
  
  val raw_data  = List(1,4,9,2)
  val raw_data2 = List(2,10,11,2)
  
  val result = raw_data flatMap {a =>
  raw_data2 map {b => 
      if (a != b) Seq(a,b)
      else Nil
    }
  }
  //equals to 4Comprehension 
  val forResult = for {
    a <- raw_data
    b <- raw_data2
    if (a != b)
  } yield (a, b)
  
  def f(a:String) = "f("+ a +")"
  def g(a:String) = "g(" + a + ")"
  //compose 
  val compose = f _ compose g _ 

  val f  = (i: Int) => i.toString 
  val f2 = (i: String) => i+i 
  val h  = f2 compose f
  h(0)
  
  //andThen ~> compose
  val andThen = f _ andThen g _
  
  //Curry
  // isDefinedAt() be used as condition 
  val one : PartialFunction[Int, String] = {case 1 => "one"; case 2 => "two"}  
  one.isDefinedAt(1)// true
  one.isDefinedAt(2)// true
  
  val friends: PartialFunction[String, Int] = {case "chloe" => 28; case "emma" => 24; case _ => 0} 
  friends.isDefinedAt("chloe")
  friends.isDefinedAt("emma")
  friends("chloe")

  //PartialFunction <-> compose 
  val wildcard: PartialFunction[String, Int] = { case _ => 0} 
  val partial = friends orElse wildcard 
  //
  val f: PartialFunction[Int, String] = {case i if i%2 == 0 => "even"}
  val f2: (Int => String)= f orElse {case _ => "odd"}
  f2(1) 
  
  def outside_function(f: Int => Int): (Int, Int) => Int = {
      def wrapper(a: Int, b:Int):Int = f(a) + f(b)
      wrapper
  } 
  val result = outside_function(x=> x*x)
  println(result(1,2)) 
}