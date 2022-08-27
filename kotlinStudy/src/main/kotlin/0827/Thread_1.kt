package `0827`

import java.lang.IllegalArgumentException

fun main(){


    val a = try{
        "1234".toInt()
    }catch (e : Exception){
        println("예외 발생!")
    }

    failFast("test")

    println(a)

}

fun failFast(message:String) : Nothing {
    throw IllegalArgumentException(message)
}