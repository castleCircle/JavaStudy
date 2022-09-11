package `0909`

fun main(){

    println(minusTest(3,2))
    println(plusTest(1,2))

    var t = plusTest
    println(t(1,2))

    callTest({println("21")})
    callTest(callFunc())
}



val plusTest : (Int,Int) -> (Int) = {a,b-> a+b}

fun minusTest(a:Int,b:Int) : Int{
    return a-b
}

fun callFunc() : ()->Unit = {println("aa")}

fun callTest(block : ()->Unit){
    block()
}