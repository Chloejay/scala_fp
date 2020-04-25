package scalaFunctions

import scala.language.higherKinds
//goal: write module, extend new functionality use type class, reuse components 

object PayrollSys{

  case class Employee (name: String, pay: Int) //construct data with case class 
  
  trait PayrollProcessor[F[_], A]{//higher kinded type to parameterize types 
    def processPayroll(payee: Seq[A]): Either[String, Double]  
  }
  //interface 
  case class USPayroll[A](payee: Seq[A])(implicit processor : PayrollProcessor[USPayroll, A]){
    def processPayroll = processor.processPayroll(payee) 
  }
  case class CNYPayroll[A](payee: Seq[A])(implicit processor: PayrollProcessor[CNYPayroll,A]){
  def processPayroll = processor.processPayroll(payee)
}
  case class EUPayroll[A](payee: Seq[A])(implicit processor: PayrollProcessor[EUPayroll, A]){
    def processPayroll = processor.processPayroll(payee) 
  }
}

object PayrollProcessor { //use companion object to init the instances for each subtype based on currency rate 
  import PayrollSys._ 

  implicit object USPayrollProcessor extends PayrollProcessor[USPayroll, Employee]{
  def processPayroll(payee: Seq[PayrollSys.Employee]): Either[String, Double] = Right(payee(0).pay) 
}

  implicit object CNYPayrollProcessor extends PayrollProcessor[CNYPayroll, Employee]{
    def processPayroll(payee: Seq[PayrollSys.Employee]): Either[String, Double] = Right(payee(0).pay * 7.08)  
  }
  
  implicit object EUPayrollProcessor extends PayrollProcessor[EUPayroll, Employee]{
    def processPayroll(payee: Seq[PayrollSys.Employee]): Either[String, Double] = Right(payee(0).pay * 0.92)
}
}

object RunPayroll {
  import PayrollSys._ 
  import PayrollProcessor._ 
  
  def main(args: Array[String]):Unit = run 
  def run: Unit = {
      //create instance for the USPayroll 
      val usa = USPayroll(Vector(Employee("emma", 100))).processPayroll
      val cny = CNYPayroll(Vector(Employee("chloe", 150))).processPayroll
      val eu  = EUPayroll(Vector(Employee("Nat", 300))).processPayroll
      println(usa, cny, eu)
  }
}
