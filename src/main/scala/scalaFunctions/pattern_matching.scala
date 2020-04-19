package scalaFunctions 

object patternMatchConst{
  //matching by constant value use case class 
  val const = List[Int](1,2,3) 
  const match {
    case List(a,b,c) => a + b + c} 
  }

object matchByCaseClass{
  case class Person(name: String, age: Int)

  def person(v:Person) = v match {
    case Person(name, age) => s"$name is $age"
    case Person(_, age) => s"only get $age"
    case  _ => None }
}

//use Option[F_] 
  case class Person(name: String, email: Option[String])
  object Email{
    def getEmail(name: List[String]): Any = 
  for {
    user <- users.find(_.name == name)
    } yield user.email 
  }

//matching by function variables, type 
object matchByTypes{
  def f(a: Any):String = a match {
  case i: Int => s"$i is int" 
  case i: Double => s"$i is double"
  case _ => "none"}
}

object matchByVals{
  def f(a:Int) = a match {
    case 1 => "one"
    case 2 => "two"
    case 3 => "three"
    case 4 => "four" 
  } 
}

object matchByGenerics{
  def count[A](v: List[A]) = v match {
      case List(v) => Some(v) 
      case _ => None 
  }
  
  def combine[A](v: Seq[A]) = v match {
      case v +: _ => Some(v)
      case _ => None 
  }
}

//trait/case class 
sealed trait User{
  def name: String 
  def score: Int
}
object User{
case class UserA (val name: String, val score: Int, val upgrade: Double) extends User
case class UserB (val name: String, val score: Int) extends User 

def testUser(v: User) = { 
  v match{
  case UserA(name, _, _) => s"hello $name"
  case UserB(name, _) => s"welcome dear $name"}
  }
}

//matching by type parameters 
sealed trait Price 
object Price {
//no parameter, either write as final case class NoSale() or write `object` 
case object NoSalePrice extends Price 
final case class PreSalePrice (discount: Double) extends Price
final case class OnSalePrice (discount: Double, qty: Int) extends Price
}
//product 
final case class ProductSales(
  sku: Int,
  pktName: String,
  price: Price)

object Calculation{
  def calculatePrice(cost: Double, price: Price) =
    {price match 
      {
        case Price.NoSalePrice => 1
        case Price.PreSalePrice(discount) => cost * discount 
        case Price.OnSalePrice(discount,qty) => cost * discount * (qty * 0.9)
        case _ => None 
      }
  }
}

object PatternMatch{
  def main(args: Array[String]):Unit = { 
  import patternMatchConst._ 
  import matchByCaseClass._ 
  import matchByTypes._
  import matchByVals._ 
  import matchByGenerics._
  import Price._ 

  val c = Person("chloe", 28) 
  println(person(c)) //return java.io.Serializable = chloe is 28
  val person = List(
  Person("chloe", Some("chloejiy@gmail.com")),
  Person("emma", None))

  val preSale = new ProductSales(1, "coke", Price.PreSalePrice(1.0))
  val onSale  = new ProductSales(2,  "sprite", Price.OnSalePrice(1.0, 2))
  println(preSale.pktName) //coke 
  }
}