package scalaFunctions

/**
  * https://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html
  */

//parameters automatically bind, in scope and unambiguous
object implicit1{
  implicit val name = "chloe" //make sure compiler is enough to look up in scope  
  def check(implicit value: String) = 
  println(s"checks name $value without parameters")
  check
}

object multiplyImplicits{
  implicit val n = 1
  def add(x:Int)(implicit y:Int) = x + y
  add(10)
}
//type conversion Int=> String or String=> Int as implicit view 
object view {
  implicit def i2s(v: Int) = v.toString 
  def s(a: String) = a
  implicit def s2i(v: String) = v.toInt 
}

//typeclass 
object adHoc{
  trait WriteBytes[A]{
      def read(v: A): String 
  }
  //companion object as interface 
  object WriteBytes{
      def apply[A](implicit v: WriteBytes[A]): WriteBytes[A] = v 
  }
  //instance of typeclass 
  implicit val ReadPoetry: WriteBytes[String] = new WriteBytes[String] {
      override def read(v: String): String = v.toUpperCase 
  }
  implicit val ReadDigits: WriteBytes[Int] = new WriteBytes[Int] {
      override def read(v: Int): String = v.toString
  }
  implicit val ReadDouble: WriteBytes[Double] = new WriteBytes[Double] {
      override def read(v: Double): String = v.toString
  }
}
//monoid with typeclass and implicit 
trait SemiGroup[A]{
  def combine(x: A, y: A): A
}
trait Monoid[A] extends SemiGroup[A]{
  def empty: A
}
object Monoid{
  def apply [A](implicit v: Monoid[A]) = v 
  
  implicit val booleanInstance: Monoid[Boolean] = new Monoid[Boolean]{
  def empty = true 
  def combine(a: Boolean, b: Boolean) = (a && b) || (!a && b)  
  }
}

object implicitHKT{
  //same use the higher kinded type with implicit to move abstraction one level up
  trait Whatever[F[_]]{
    def forCode[A](a: A): F[A]  // create interface, use F as type constructor and A type of type 
  }
  // create companion object to hold implementation 
  object Whatever{
    def apply[F[_]](implicit v: Whatever[F]) = v 
    implicit val lstInstance: Whatever[List] = new Whatever[List] {
    def forCode[A](a: A): List[A] = List(a) //a can be any data types, pattern matching in this scope 
    }
  }
}


object Implicit{
  import adHoc._ 
  import Monoid._
  import view._ 
  import implicitHKT._
  
  def main(args: Array[String]){
    WriteBytes[String].read("soundness") 
    WriteBytes[Int].read(0)
    WriteBytes[Double].read(1.0) 
    Monoid.combine(true, true) 
    val str = view.i2s(1)
    val int = view.s2i("1")
    implicitHKT.Whatever.lstInstance.forCode("anyWords...")
  }
}