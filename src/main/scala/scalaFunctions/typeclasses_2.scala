package scalaFunctions

//supertype and subtype 
sealed trait Transportation[+T]
object Transportation {
  final case class Bikes[+T](param:T) extends Transportation[T]
  final case class Trains[+T](param:T) extends Transportation[T] 
  final case object Feet extends Transportation[Nothing] 
}
//superclass and subclass 
sealed trait MoveTool 
case object BlackBike extends MoveTool
case object Train extends MoveTool

//use object 
object StartMove{
  def move(tool: Transportation[MoveTool]):Unit={
    println(s"move with $tool")
  }  
}

//monad pattern 
//TODO 
sealed trait Monad[+A] {
  def flatMap[B](f: A => Monad[B]): Monad[B] 
}
//concrete monad 
case class Just[+A](a: A) extends Monad[A] {
  override def flatMap[B](f: A => Monad[B]) = f(a) //need implement
}
//concrete monad, without variable 
case object MaybeNot extends Monad[Nothing] {
  override def flatMap[B](f: Nothing => Monad[B]) = MaybeNot //need implement
}

object Monad{
  def unit[A](option: Option[A]):Monad[A]= option match {
    case Some(a)=> Just(a)
    case None=> MaybeNot 
  }
}

object Main{
  def main{
    val bike= Transportation.Bikes(BlackBike)
    println(StartMove.move(bike)) 
  }
}
