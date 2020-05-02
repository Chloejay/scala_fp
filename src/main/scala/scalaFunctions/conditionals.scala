package scalaFunctions 

// if/then/else
// try/catch/finally 
import java.io.IOException
import java.io.FileReader
import java.io.FileNotFoundException

object Conds{
  def read(file: String){
    try {
    val f = new FileReader(file) 
    // throwsException();
    } catch { //Functional Error handling
    case e: FileNotFoundException    => e.getMessage
    case e: IllegalArgumentException => e.getMessage;
    case e: IllegalStateException    => e.getMessage;
    case e: IOException              => e.getMessage;
    } finally {
    f.close() 
    println("executed") //close  
    }
  }
}