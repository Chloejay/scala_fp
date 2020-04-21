package scalaFunctions

//supertype and subtype 
sealed trait Transportation[+T]
object Transportation {
  final case class Bikes[+T](param:T)       extends Transportation[T]
  final case class Trains[+T](param:T)      extends Transportation[T] 
  final case object Feet                    extends Transportation[Nothing] 
}
//superclass and subclass 
sealed trait MoveTool 
case object BlackBike  extends MoveTool
case object CityTrain      extends MoveTool

//use object 
object StartMove{
  def move(tool: Transportation[MoveTool]): Unit = {
    println(s"move with $tool")
  }  
}

//monad pattern  
sealed trait Monad[+A] {
  def flatMap[B](f: A => Monad[B]): Monad[B] 
}
//concrete monad 
case class Just[+A](a: A) extends Monad[A] {
  override def flatMap[B](f: A => Monad[B]) = f(a) //need implement
}
//concrete monad 
case object MaybeNot extends Monad[Nothing] {
  override def flatMap[B](f: Nothing => Monad[B]) = MaybeNot
}

object Monad{
  def unit[A](option: Option[A]):Monad[A] = option match {
    case Some(a) => Just(a)
    case None => MaybeNot 
  }
}

//Monoid 
trait Monoid[A]{
    def empty: A
    def combine(a: A, b:A): A
}

object Monoid{
    implicit val IntMo: Monoid[Int] = new Monoid[Int] {         
        def empty: Int = 0
        def combine(a: Int, b: Int): Int = a+b 
    }

    implicit val StringMo:Monoid[String] = new Monoid[String] {
        def empty: String = " "
        def combine(a: String, b: String): String = a + b
    }
}

object Main{
  def main(args: Array[String]){
    import Monoid._
    def ++[A: Monoid](v: List[A]):A = {
    val c = implicitly [Monoid[A]] 
    v.foldLeft(c.empty)(c.combine)}
    println(++(List("ok","viola"))) 
    println(++(List(1,2)))  
    val bike = Transportation.Bikes(BlackBike)
    println(StartMove.move(bike))
  }
}