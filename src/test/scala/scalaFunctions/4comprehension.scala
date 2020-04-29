package scalaFunctions

object check extends munit.FunSuite{
  class Person(val name: String)
  test("init test for class Person to try frameowrk munit"){
    val p = new Person("chloe")
    assert(p.name == "chloe")
  }
}
