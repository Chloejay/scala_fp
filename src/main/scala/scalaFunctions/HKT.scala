package scalaFunctions

//F[_], type function for typeclass
trait Item [F[_]]{
  def f[A](v: A): F[A]
}
object Item{
  val test = new Item[List]{def f[A](v: A) = List(v)}
}
trait What[F[_]]{
    def get_words(v: String): F[String]
}
// F <=> List 
object List_ extends What[List] {
    def get_words(v: String): List[String] = List(v) 
    def get_int(v: Int): Int = v * v 
}


//TODO 

object HKT{
  import Item._ 
  import List_._ 
  
  def main(args: Array[String]){
  val result1 = Item.test.f(1) 
  val result2 = Item.test.f("whatever") 
  val words = List_.get_words("what?")
  val pow = List_.get_int(2) 
  println(s"$result1, $result2")
  println(words, pow) 
  }
}