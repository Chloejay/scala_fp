package scalaFunctions 

// if/then/else
// try/catch/finally 

object Conds{
  try {
  throwsException();
} catch { //Functional Error handling 
  case e: IllegalArgumentException => e.getMessage;
  case e: IllegalStateException    => e.getMessage;
  case e: IOException              => e.getMessage;
} finally {
  println("executed") //close  
}
}