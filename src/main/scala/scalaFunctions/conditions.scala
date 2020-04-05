package scalaFunctions 

// if/then/else
// try/catch/finally 

object Conds{
  try {
  throwsException();
} catch {
  case e: IllegalArgumentException => println("illegal arg. exception");
  case e: IllegalStateException    => println("illegal state exception");
  case e: IOException              => println("IO exception");
} finally {
  println("this code is always executed");  
}
}