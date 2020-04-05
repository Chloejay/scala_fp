class Test(x: Int, y: Int): Int = {
    require (y!=0, "wrong number")
    private def add= x+y 
    def number= x/add
}
