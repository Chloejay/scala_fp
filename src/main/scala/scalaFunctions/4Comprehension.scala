package scalaFunctions

object Comprehension{
def main(args: Array[String]){
  val list_ = (1 to 10).toList
  for {a <- list_
  new_list = a/2 
  b <- list_} yield (new_list, b) 
  }
}
//TODO trait and HKT

// case class 
object Extractor{
  case class Person(name: String, age: Int) 
  val p = List(
  Person("chloe", 28), 
  Person("emma", 26)
  ) 
  for (Person(name, age) <- p; if age >26 ) yield name + "," + age 
}