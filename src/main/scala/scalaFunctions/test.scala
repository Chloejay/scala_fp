package scalaFunctions

object Test{
  def sum(x: Int, y: Int): Int={
  require (y!=0, "wrong number")
  // private def justAdd= x+y 
  def number = x+y 
  number 
  }

  def main():Unit={
    println(sum(1,2)) 
  }
}