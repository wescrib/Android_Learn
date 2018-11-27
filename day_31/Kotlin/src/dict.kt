fun main(args: Array<String>) {

    var cats = mapOf<String, Int>("Mogwai" to 8, "Elaine" to 26, "Ripley" to 12)

    println(cats["Elaine"])

    var friendsAge = mutableMapOf<String, Int>("Tim" to 27, "Sandra" to 23, "Elvys" to 22)

    friendsAge["Jennifer"] = 26
    println(friendsAge.keys)

    friendsAge["Jennifer"] = 30

    println(friendsAge["Jennifer"])

    var slangDict = mutableMapOf<String, String>("lit" to "fuckin cool", "swag" to "derived from the shakespearean word 'swagger', this also means cool", "hundo-p" to "this apparently means 100%")

    println(slangDict)

}