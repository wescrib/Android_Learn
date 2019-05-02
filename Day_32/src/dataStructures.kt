fun main(args: Array<String>) {

    //arraylist
    var n = arrayListOf<Int>(1,2,3,4,5)

    //mutable list
    var l = mutableListOf<Char>('w','i','l','l')
    l.add('y')

    //list
    var movies = listOf<String>("Interstellar", "Lego Movie", "Fast and Furious")

    //maps
    var slang = mutableMapOf<String, String>(
        "antistalking" to "Methodically learning another person's routine in order to systematically avoid them.",
        "scamazon" to "Ordering an item on Amazon and claiming it never arrived to your home which results in a refund and you can keep the item.",
        "capper" to "A Cap Is A lie. A capper is a liar. So if someone is capping, they are lying.")
    println(slang["scamazon"])

//    for(movie in movies){
//        println(movie)
//    }

    //print every number odd number up to 200
//    for(i in 1..200){
//        if(i%2 != 0){
//            println(i)
//        }
//    }
}