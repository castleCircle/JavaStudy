package Lec

fun main(){
    val str: String? = "ABC"
    //str.length 안댐
    println(str?.length)

    val str1: String? = null
    println(str1?.length ?: 0)

    println(startWith("ABC"))
    println(startWith(null))
}

fun startsWithA1(str: String?) : Boolean{
    if(str == null) {
        throw IllegalArgumentException("null이 들어옴")
    }
    return str.startsWith("a1")
}


fun startsWithA1Kotlin(str: String?) : Boolean{
    return str?.startsWith("a1") ?: throw IllegalArgumentException("nuill이 들어옴")
}

fun startsWithA2(str: String?) : Boolean?{
    if(str == null) {
        return null
    }
    return str.startsWith("a2")
}

fun startsWithA2Kotlin(str: String?) : Boolean?{
    return str?.startsWith("a2")
}

fun startsWithA3(str: String?) : Boolean{
    return str?.startsWith("a2") ?: false
}

fun startWith(str: String?):Boolean{
    return str!!.startsWith("A")
}