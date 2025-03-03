package Lec

class Person{

    lateinit var name: String
   val iksKim : Boolean
       get() = this.name.startsWith("김")

    val maskingName: String
        get() = name[0] + (1 until name.length).joinToString(""){"*"}
}

fun main(){

    val person = Person()
    person.name = "22"

}