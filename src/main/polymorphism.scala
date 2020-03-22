//oop subtype 
abstract class Language(name: String){
  def greetings
}
class Deutsch(name: String) extends Language(name){
  override def greetings:Unit = println(s"Halo!! $name")
}
class Chinese(name: String) extends Language(name){
  override def greetings:Unit = println(s"Ni Hao! $name")
}
def say(lang: Language):Unit={
  println(lang.greetings)
}

val test= say(new Chinese("chloe"))

//type classes 
trait DataSource[T]{
    def add(x:T, y:T):T   
}

object DataSource[T]{
  implicit object IntData extends DataSource[Int]{
    override def add(x: Int, y: Int)= x + y 
}
  implicit object DoubleData extends DataSource[Double] {
    override def add(x: Double, y: Double)= x + y 
 }

  implicit object StringData extends DataSource[String]{
    override def add(x: String, y: String)= x + y 
}
}

def concat[T](x: T, y: T)(implicit data: DataSource[T]):T=
data.add(x,y) 

concat(1,2)
concat(1.0, 2.0)
concat("kulture","menchen")

val filter= (predicate: Int=>Boolean, xs: List[Int])=>{
    for (x<-xs; if predicate(x)) yield x 
}