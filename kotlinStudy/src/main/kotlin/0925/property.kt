package `0925`

import java.lang.RuntimeException

//class Car(val yearOfMake: Int,var color:String)
//
//
//fun userCarObject() : Pair<Int,String>{
//    val car = Car(2019,"RED")
//    val year = car.yearOfMake
//    car.color = "Green"
//    val color = car.color
//    return year to color
//}

class Car(val yearOfMake:Int, theColor: String){
//    var fuelLevel = 100

    var fuelLevel = if(yearOfMake < 2020) 90 else 100
    private set
    var color = theColor

    set(value){
        if(value.isBlank()){
            throw RuntimeException("no empty, please")
        }
        field = value
    }
}

fun main(){

    val car = Car(2019,"RED")
    car.color = "Green"
//    car.fuelLevel--
    println(car.fuelLevel)

    try {
        car.color = ""
    }catch(ex: Exception){
        println(ex.message)
    }
    println(car.color)

}