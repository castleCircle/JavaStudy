class Person private constructor(
    private val name: String,
    private val age: Int
){
    companion object{
        private val MIN_AGE = 1
        private const val MIN_AGE1 = 1
        fun newBaby(name:String) : Person{
            return Person(name, MIN_AGE)
        }
    }
}

fun main(){
    val number = 3;
    val numbers = listOf(100,200)
    val numbers2 = mutableListOf(100,300)
    numbers2.add(1,2)
    println("123".lastChar("a"))
    println("123" lastChar2 "a")

    val fun1 = fun(data:String) : Boolean {
        return data.length > 1
    }

    val fun2 = {data:String -> data.length > 1}

//    val fun3 : (String) -> Boolean = fun()

    println(fun1("a"))
    println(fun2.invoke("b11"))
    println(fun2("33"))
}

class Car(
    val name: String,
    _price: Int
){
    var price = _price
    private set
}

fun String.lastChar(test : String): Char{
    return this[this.length - 1]
}

infix fun String.lastChar2(test : String): Char{
    return this[this.length - 1]
}
