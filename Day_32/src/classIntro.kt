fun main(args: Array<String>) {
    class Car{

        var make: String
        var model: String

        constructor(){
            this.make = "unassigned"
            this.model = "unassigned"
        }

        constructor(make: String){
            this.make = make
            this.model = "unassigned"
        }

        constructor(make: String, model: String){
            this.make = make
            this.model = model
        }

        fun accel():String {
            var msg: String = ""
            if (this.make != "unassigned") {
                msg = "vroom vroom"
            } else {
                msg = "there is no car to go vroom"
            }
            return msg
        }
    }

    var myCar = Car()

    println(myCar.make)
    println(myCar.accel().toUpperCase())
}