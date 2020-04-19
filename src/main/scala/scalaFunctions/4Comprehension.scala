package scalaFunctions

object Main{
def main(args: Array[String]){
  val list_ = (1 to 10).toList
  for {a <- list_
  new_list = a/2 
  b <- list_} yield (new_list, b) 
  }
}
//TODO trait and HKT
