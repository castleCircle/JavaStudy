package `0827`

fun getNullReturn() : String? = null

fun getNotnull(text : String?) = text?.length ?: 0


fun main(){


    var text = getNullReturn()

    var realText = text?.length ?: "없는데이터".length

    println(realText)

    var length = getNotnull(null)

    println(length)

}