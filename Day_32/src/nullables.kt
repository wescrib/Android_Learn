fun main(args: Array<String>) {
    var name: String? = "Arya"
    name = null

    var msg: String = ""

    if (name == null) msg = "A girl has no name" else msg = "I guess my name is $name"

    println(msg)

}