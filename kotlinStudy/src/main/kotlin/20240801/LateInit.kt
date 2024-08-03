package `20240801`

import kotlin.properties.Delegates

class Person{
    lateinit var name : String

    val isKim: Boolean
        get() = this.name!!.startsWith("김")

    val maskingName: String
        get() = name!![0] + ""
}

class Person2{
    val name: String by lazy{
        Thread.sleep(2_000)
        "김수한무"
    }
}

fun main(){
    val p = Person()
    //p.isKim  초기화 안하고 쓰면 error가 남
    val pp = Person4()
    pp.age = 30
    
}

class Person4{
    var age: Int by Delegates.observable(20){
        property, oldValue, newValue ->
        println("이전값:${oldValue} 새로운값: ${newValue}")
    }
}