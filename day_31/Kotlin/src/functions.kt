fun main(args: Array<String>) {

    fun testMethod(){
        println("hello world")
    }

    fun testReturnMethod(): Int{
        return 1
    }

    fun testParam( name: String = "loser"):String{
        return "hello $name"
    }

    fun getSum(n1: Int, n2: Int) = n1+n2

    testMethod()
    println(getSum(1,5))

    fun dogChallenge(name: String, age: Int) = "My dogs name is $name, and he is $age years old"

    println(dogChallenge("Steve", 4))
}