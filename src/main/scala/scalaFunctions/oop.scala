package scalaFunctions 

//curry;recursion
object Add{
  def ++ (f:Int => Int)(a: Int, b:Int):Int =
  {
    if (a > b) a
    else f(a) * ++(f)(a+1,b) 
  }
}

object OOP{
  def main(args: Array[String]){

    import Add._ 
    val pkt = Add.++(x => x+x )(1,2)
    println(pkt)//24
  }  
} 
