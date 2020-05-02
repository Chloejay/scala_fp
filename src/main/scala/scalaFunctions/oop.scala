package scalaFunctions 

//curry;recursion
object Add{
  def ++ (f:Int => Int)(a: Int, b:Int):Int ={
    if (a > b) a
    else f(a) * ++(f)(a+1,b) 
  }
}

//sealed trait + case class pattern (enum) 
//with pattern matching 
sealed trait DoStuff 
case class Work(v: String)         extends DoStuff
case class Study(v: String)        extends DoStuff 

object Do{
  val doWhat: DoStuff => Unit= {
      case Work(v: String)  => println(s"do the $v")
      case Study(v: String) => println(s" do the $v")
  }
}
//expand data struct and pattern matching in base trait 
sealed trait Transportation {
  def take(v: String): Transportation = {
    this match {
      case Bike(c, p) => ???
      case Skateboard(c,p) => ???
      case Subway() => ???
      case _ => None 
      } 
  }
}
case class Bike(color: String, price: Int)          extends Transportation
case class Skateboard(color: String, price: Double) extends Transportation
case class Subway()                                 extends Transportation

sealed trait City 
case object Shanghai     extends City 
case object Berlin       extends City 
case object Nice         extends City 

case class What(
where: City,
what:Transportation, 
how: DoStuff 
)
//TODO: use generic type 

object OOP{
  import Do._ 
  import Add._ 
  def main(args: Array[String]){
    Do.doWhat(Study("political economics"))
    val pkt = Add.++(x => x+x )(1,2)
    println(pkt)//24
  }  
}

