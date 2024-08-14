package dsl

import java.time.LocalDate

class OperatorOverloading {
}

data class Point(
    val x: Int,
    val y: Int,
){
    fun zeroPointSymmetry(): Point = Point(-x,-y)

    operator fun unaryMinus() : Point{
        return Point(-x,-y)
    }

    operator fun inc(): Point{
        return Point(x+1,y+1)
    }
}

fun main(){
    var point = Point(20,10)
    println(-point)
    println(point.zeroPointSymmetry())
    println(++point)

    LocalDate.of(2023,1,1).plusDays(3)
    LocalDate.of(2023,1,1) + Days(3)

}

data class Days(val day: Long)

operator fun LocalDate.plus(days: Days): LocalDate{
    return this.plusDays(days.day)
}