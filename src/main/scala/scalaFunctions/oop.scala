package scalaFunctions 

//currying and recursion method 
object productTest{
  def product(f:Int=>Int)(a: Int, b:Int):Int=
  {
    if (a>b) a
    else f(a) * product(f)(a+1,b) 
  }

  def main():Unit={
  val pkt= productTest.product(x=>x*x)(1,2)
  println(pkt)
  }
}

object Main{
  productTest.main()
} //12 
