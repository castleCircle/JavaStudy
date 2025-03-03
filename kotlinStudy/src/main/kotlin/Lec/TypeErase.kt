package Lec

fun main(){
    var numbers = listOf(1,2f,3.0)
    println(numbers.filterIsInstance<Float>())
}


inline fun <reified T> List<*>.hasAnyInstanceOf(): Boolean{
    return this.any { it is T }
}