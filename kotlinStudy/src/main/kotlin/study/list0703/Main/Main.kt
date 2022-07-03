package study.list0703.Main

fun main() {

    val listOf = listOf(1, 2, 3)
    val list2 = listOf + 4
    val list3 = listOf + list2
    var list4 = listOf + "asb"

    println(listOf)
    println(list2)
    println(list3)
    println(list4)

    val mutableList1 = mutableListOf(1,2,3)
    val mutableList2 = mutableList1.add(4)

    println(mutableList1)
    println(mutableList2)

    /**
     * 타입을 선언하
     */
    val typeMutableList1:List<Int> = mutableListOf(4,5,6)
    val typeMutableList2: List<Int> = typeMutableList1 + 2

    println(typeMutableList1)
    println(typeMutableList2)

    println(sumOfPrimes(10))

    val s: String?=null
    val l = s?.length

    println(l)

    var s1: String?=null
    var s2: String?= null
    val l1: String? = s2?.toString()

    println(l1)

    val a:Int = 10
    val b:Int = 20

    val ss = if(a<b)
        "a is b"
    else "b is a"

    println(ss)

    val country = "sou"

    val capital = when {
        country == "Austrial" -> "호주"
        else -> "test"
    }

    println(capital)

    val range = 0 until 10 step 2
    for(i in range)
        println(i)

}

fun sumOfPrimes(limit: Int): Long{

    val seq: Sequence<Long> = sequenceOf(2L) + generateSequence(3L,{
        it + 2
    }).takeWhile {
        it < limit
    }

    println("=================")

    fun isPrime(n: Long): Boolean = seq.takeWhile {
        it * it<=n
    }.all{
        n % it != 0L
    }

    return seq.filter(::isPrime).sum()
}
