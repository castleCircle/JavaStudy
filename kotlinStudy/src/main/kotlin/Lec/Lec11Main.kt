package Lec

fun main(){
    Person.Factory.newBaby("1")
    Person.Factory.log()
}

class Person private constructor(
    var name: String,
    var age:Int
){

    companion object Factory : Log{
        private val MIN_AGE = 0
        fun newBaby(name:  String): Person{
            return Person(name,MIN_AGE)
        }

        override fun log() {
            println("나는 Person 클래스의 동행객체 Factoriy 에요")
        }
    }


}

interface Log{
    fun log()
}

//싱글톤-
object Singleton