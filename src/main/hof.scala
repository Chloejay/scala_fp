// package main 

trait List[A] {
  def filter(f: A => Boolean): List[A]
}

//compare with Python for classic wrapper function
//python 
/**def outside_function(f):
    def wrapper(a,b):
        return f(a)+ f(b)
    return wrapper 

def pow(x):
    return x*x
x= outside_function(pow)
print(x(1,2)) //should return 5
*/

//scala 
def outside_function(f: Int=>Int):(Int, Int)=>Int={
  def wrapper(a: Int, b:Int):Int= f(a)+f(b)
  wrapper
}

def pow(a: Int)= a *a 
val result= outside_function(pow)
println(result(1,2)) //should return 5 

//lambda function(anonymous)
val result2= outside_function((a: Int)=> a*a) //should return 5 
//
val result3= outside_function(a=>a*a) //should return 5

/** in Python, use map, reduce, filter etc method make use of lambda function
list_= list(range(1,100)) 
print(list_)
print(list(map(lambda x: x+1, list_))) 
print(list(filter(lambda x: x%2!=0, list_)))  
from functools import reduce 
print(reduce(lambda x,y: x+y, list_))
*/

/** scala, is more tight and concise, use "_" save bytes*/ 
val list_ = 1 to 100 
//map 
println(list_.map(x=>x+1))
//
println(list_.map(_+2))
//filter
println(list_.filter(x=>x%2 !=0))
//
println(list_.filter(_%2 !=0)) 
//reduce 
println(list_.reduce((x,y) => x+y))
//
println(list_.reduce(_+_))


//scala filter case class 
case class Person(name: String, age: Int, city: String)
val person= List(
  Person("chloe", 28, "shanghai"), 
  Person("emma", 24, "newyork")
  )
val isAgeTrue= person.filter{case Person(name, age, city)=> age>25}
println(isAgeTrue)

val person2= Map("chloe"->28, "emma"->24)
val filter2= person2.filter((p:(String, Int))=>p._2>25)
println(filter2)

//use case here again, case is very useful :) 
val filterCase= person2.filter{case(name, age)=>age>25} 
println(filterCase)

//case for pattern matching 
val person_ : PartialFunction[String, Int]= {case "chloe"=>28; case "emma"=>24; case _ => 0}
println(person_ ("chloe"))
println(person_ ("doc_who")) 

