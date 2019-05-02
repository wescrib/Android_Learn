fun main(args: Array<String>) {
    fun hello(name: String = "world"): String{
        return "hello $name!"
    }

//    fun addNum(n1: Int, n2:Int):Int{
//        return n1 + n2
//    }

    fun addNum(n1: Int, n2: Int) = n1+n2

    println(addNum(1,2))

//    println(hello())
}