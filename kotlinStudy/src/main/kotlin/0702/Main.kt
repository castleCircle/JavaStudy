package `0702`

import java.io.Serializable

fun main(){
    val name = "test"
    println(name)

    var test: String? = null
    test = "1"

    var test1 = "trestse"

    test1 = getName()
    println(test1)
    
    val test2 : String by lazy { getName() }
    var test3: String = getName()

    val person = Person("test", 100)
    println("name:${person.name} ,age: ${person.age}" );

    val person1 = Person("test",100)

    val test4 = Test("test")
    println(test4)

    val test5 = Test("test1",200)
    println(test5)

    Person.create("1")

}



data class Test(val name:String, val age:Int = 100)

class Test1(name:String,age:Int){

}


data class Person constructor(val name:String, val age:Int) : Serializable {
    companion object{
        fun create(xml: String): Person{
            TODO("Write an implementaion creating" + "a Person from an xml STring")
        }
    }
}

class Person1(name:String,age:Int,height:Int)

fun getName(): String{
    println("이름 계산중")
    return "이것은 이름이다."
}