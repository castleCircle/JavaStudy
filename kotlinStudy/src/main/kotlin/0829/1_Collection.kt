package `0829`

import java.util.*


@OptIn(ExperimentalStdlibApi::class)
fun main(){

    var currencyList = listOf("달러","유로","원")


    //mutable
    var mutableCurrencyList: MutableList<String> = mutableListOf<String>().apply {
     add("달러")
     add("유료")
     add("원")
    }

    val numberSet = setOf(1,2,3,4,4)

    val mutableSet = mutableSetOf<Int>().apply {
        add(1)
        add(2)
        add(3)
        add(4)
    }

    val numberMap = mapOf("one" to 1,"two" to 2)

    //mutableMap

    val mutableNumberMap = mutableMapOf<String,Int>()
    mutableNumberMap["one"] = 1
    mutableNumberMap["two"] = 2


    //컬렉션 빌더
    val numberList:List<Int> = buildList{
        add(1)
        add(2)
        add(3)
    }

    val linkedList = LinkedList<Int>().apply {
        addFirst(3)
        add(2)
        addLast(1)
    }

    val iterator = currencyList.iterator()
    while(iterator.hasNext()){
        println(iterator.next())
    }

    for(currency in currencyList){
        println(currency)
    }

    println("=========")
    currencyList.forEach{
        println(it)
    }

    

}