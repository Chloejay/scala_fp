package scalaFunctions 

sealed trait TransportationCharge 
final case class Bike(price: Int) extends TransportationCharge
final case class Train(price: Double) extends TransportationCharge 

//Charge is typeclass 
trait Charge[A]{
    def fee(price: A): TransportationCharge} 

//TO IMPLEMENT; 
class Bikes(price: Int, city: String)
class Trains(price: Double, city: String)

//concrete of superclass Charge[A] 
object ChargeBike{
  implicit val bikeCharge: Charge[Int] = new Charge[Int]{
    def fee(price: Int): TransportationCharge = Bike(price) 
    }

  implicit val bikeChargeByCity: Charge[Bikes]= new Charge[Bikes] {
    def fee(price: Bikes): TransportationCharge = ???
    }
}
object ChargeTrain{
  implicit val trainCharge: Charge[Double] = new Charge[Double]{
    def fee(price: Double): TransportationCharge = Train(price) 
    }

  implicit val trainChargeByCity: Charge[Trains] = new Charge[Trains]{
    def fee(price: Trains): TransportationCharge = ???
    }
} 

object Main{
  def main(args: Array[String]){
      val bike= ChargeBike.bikeCharge.fee(10)
      val train= ChargeTrain.trainCharge.fee(60.8)
      println(s"Price fee is $bike; train fee is $train") 
  }
}


