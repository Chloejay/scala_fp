package scalaFunctions

import scala.concurrent.{Future, Await} 
import scala.util.{Success, Failure}
import scala.language.postfixOps
import scala.concurrent.duration._ 
import scala.concurrent.ExecutionContext.Implicits.global


object TimeOut{
  val data= 1 to 100 toList 
  def do_work(v: List[Int])= Future{v map (x=> x*x)} 
}
// do_work(data).onComplete{
//   case Success(data) => println(data)
//   case Failure(e) => println(e.getMessage)
// } 

object composeFuture{
def f(v: Int): Int= {
  Thread.sleep(v)
  println("f1")
  v
}
def f2(v: Int): Int= {
  Thread.sleep(v)
  println("f2")
  v
}
def combine():Future[Int]={
  val ff1= Future(f(1))
  val ff2= Future(f2(2)) 

  for {
      a<- ff1
      b<- ff2
  } yield a + b 
}
} 

//TODO, use Future to load data parallel from API 

object Future{
    import TimeOut._ 
    import composeFuture._ 

    def main() {
        val awaitData = Await.result(do_work(data), 0.0000000001 millis) //TimeoutException
        val result = Await.result(combine(), 1 second) 
    }
}
