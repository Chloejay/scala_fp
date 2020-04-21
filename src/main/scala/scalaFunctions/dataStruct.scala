package scalaFunctions

/**
  * data constructor: case class 
  * Use Option to return None and Some(value) and Either extends ErrorException 
  */

object UseOption{
  case class Person(name: String, age: Int, city: String)
  def apply(name: String, age: Int, city: String): Option[Person] = 
  if (age < 10) None 
  else new Some(Person(name, age, city)) 
}

object UseEither{
  case class Person(name: String, age: Int, city: String)
  def apply(name: String, age: Int, city: String) : Either[RuntimeException, Person] = 
  if (age < 10) Left(new RuntimeException("sorry error"))
  else Right(new Person(name, age, city))
}

object UserEither2{
  case class Person(name: String, age: Int, city: String)
  case class GetAge(age:Int) extends RuntimeException(s"get $age")
  def apply(name: String, age: Int, city: String) : Either[GetAge, Person] = 
  if (age < 10) Left(GetAge(age))
  else Right(new Person(name, age, city))
}
 

object DataStruct{
  def main(args: Array[String]){
  import UseOption._ 
  import UseEither._ 
  import UserEither2._ 

  val chloe =  UseOption("chloe", 1, "space") 
  val chloe2 = UseEither("chloe2", 2, "mars") 
  val chloe3 = UserEither2("chloe3", 28, "earth")
  println(chloe) //None
  println(chloe2) //Left(java.lang.RuntimeException: sorry error)
  println(chloe3) //Right(Person(chloe3,28,earth)) 
  }
}
