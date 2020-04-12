package scalaFunctions 

sealed trait TransportationCharge 
final case class Bike(price: Int) extends TransportationCharge
final case class Train(price: Double) extends TransportationCharge 

trait Charge[A]{
    def charge_fee(price: A): TransportationCharge} 

//TO IMPLEMENT; 
class Bikes(price: Int, city: String)
class Trains(price: Double, city: String)

//concert of superclass Charge[A] 
object ChargeBike{
  implicit val bikeCharge: Charge[Int] = new Charge[Int]{
    def charge_fee(price: Int): TransportationCharge = Bike(price) 
    }

  implicit  val bikeChargeByCity: Charge[Bikes]= new Charge[Bikes] {
    def charge_fee(price: Bikes): TransportationCharge = ???
    }
}
object ChargeTrain{
  implicit val trainCharge: Charge[Double] = new Charge[Double]{
    def charge_fee(price: Double): TransportationCharge = Train(price) 
    }

  implicit val trainChargeByCity: Charge[Trains]= new Charge[Trains]{
    def charge_fee(price: Trains): TransportationCharge = ???
    }
} 

object Main{
  def main(args: Array[String]){
      val bike= ChargeBike.bikeCharge.charge_fee(10)
      val train= ChargeTrain.trainCharge.charge_fee(60.8)
      println(s"Price fee is $bike; train fee is $train") 
  }
}
