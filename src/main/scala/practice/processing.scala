package main
package scala 
package practice 

import scala.annotation.tailrec
import scala.util.{Try,Success,Failure}

class InitProcessing(n: Int,
                    city: List[String], 
                    _age: Int, 
                    _g: String = null) 

    object run_process{
      require( (city != None), "city list should not be null") 
      val num = n
      val cityList = city 
      var singleAge = _age 
      private var _gender= _g 
      val funcValue= (_age: Int)=> _age+1 
      def legitAge(currAge: Int= singleAge): Try[Int]=
        if (currAge<18) Failure (
          new IllegalArgumentException(
            s"age must be older than 18, received age is $currAge"
            ))
        else Success(currAge)

      def setAge(year: Int, lastYr: Int = 2019): Any = 
      {if ((legitAge() == Success(singleAge)) && 
        (year > lastYr)){
          singleAge += year.-(lastYr) 
          print(s"enter into, $singleAge")
          }
        else None 
      } 

      def findMatch(): List[String]=
      {var city= List[String]() 
       val upperCity= cityList map(_.toUpperCase)
       upperCity foreach {c=> println(c)}
       for (c <- upperCity)
       if ((c indexOf("B"))==0) (city::=c)  
       else city.::(Nil) 
       city 
      }
    
      override def toString =  s"$city is rank as + $num" 
      val mulDiv: Int=> Int= x=> x*x/(x+2) 
      
      @tailrec //tail recursion 
      final def filterEven(x: Int, y: Int= singleAge): String =
      if (y %2 == 0) s"until find even number $y" 
      else filterEven(y, mulDiv(x)/2)    
}  

/** val test= new InitProcessing(3, List("shanghai", "berlin", "bielefeld", "bern"), 28, "F") 
* println(test) 
* println(test.findMatch()) 
* println(test.setAge(2020, 2019)) 
* print(test.filterEven(31))*/
