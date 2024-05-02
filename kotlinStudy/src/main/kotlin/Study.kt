import java.io.Serializable
import java.time.Instant

inline class SSN(val id:String)

fun receiveSSN(ssn: SSN){
    println("Received %ssn")
}

fun noflush(){
    println("nofluff called..")
    throw RuntimeException("qwer")
}


fun compute(n:Int) = 0

fun tryExpr(blup: Boolean): Int{
    return try{
        if(blup) {
            throw RuntimeException("aa")
        }
        2
    }catch(ex:Exception){
        4
    }finally {

    }
}

fun init() : Unit{
    return;
}

class Animal(val age: Int){

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}

open class Fruit
class Banana: Fruit()
class Orange: Fruit()

class Person(val first: String,val last: String){
    var fullName = true
    var location: String = "-"
}

class MachineOperator(val name:String){
    fun checkin() = checkedIn++


    companion object{
        var checkedIn = 0
        fun minimumBreak() = "15 minutes"
    }
}



fun main(){

    var s = Person("12","13")
    println(s.fullName)
    println(s.location)

}