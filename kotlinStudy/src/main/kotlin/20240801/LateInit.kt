package `20240801`

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
}