package `0905`

//f(1,3) = 1 + 3 =4

data class Tuple(val a: Int,val b:Int)

fun plus(tuple:Tuple) = tuple.a + tuple.b

fun plus1(pair:Pair<Int,Int>) = pair.first + pair.second

fun main(){

    println(plus(Tuple(1,3)))

    val pair = Pair("A",1)
    val newPair = pair.copy(first = "B")
    println(newPair)

    val second = newPair.component2()
    println(newPair.second)
    println(second)

    val list = newPair.toList()
    println(list)

    val triple = Triple("A","B","C")
    println(triple)

    val newTriple = triple.copy(third = "D")
    println(newTriple)

    val (a,b,c) = newTriple
    println("$a,$b,$c")

    val (q,w) = Tuple(1,2)
    println("$q,$w")

    val list3 = newTriple.toList()
    val (a1,a2,a3) = list3
    println(list3)
    println("$a1,$a2,$a3")

    list3.component1()

    val map = mutableMapOf("이상훈" to "개발자")
    println(map)

    for((key,value) in map){
        println("${key}는 ${value}")
    }

}