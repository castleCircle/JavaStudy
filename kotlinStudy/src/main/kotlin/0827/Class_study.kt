package `0827`

class Person(var number:Int,age:Int,name: String? = "없음"){

    init {
        this.number = 10
    }

    var weight :Int = 0
    get() = field + 123
    set(value) {
        field = 77
    }

}

fun main(){
    val person = Person(1234566,10,"이름")
    println(person.number)

    println(person.weight)
    person.weight = 22
    println(person.weight)
}