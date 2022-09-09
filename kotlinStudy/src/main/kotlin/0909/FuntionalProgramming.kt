package `0909`

fun main(){

//    val list = mutableListOf(printHello)
//
//    val func: () -> Unit = list[0]
//    func()

//    call(printHello)

//  fun으로 만든 함수는 일급객체 불가능
//    val list = mutableListOf(printNo)

    val result : Int =plus(1,3)
    println(result)

    val minus = minus(2,1)
    println(minus)

}

val printMessage: (String) -> Unit = {message:String -> println(message)}
//val printMessage: (String) -> Unit = {message -> println(message)}
//val printMessage: (String) -> Unit = { println(it)}

val plus:(Int,Int) -> Int = {a,b -> a+b}

val minus: (Int,Int) -> Int = {a,b -> a-b}

val printHello:() -> Unit = {println("Hello")}

fun call(block: ()->Unit){
    block()
}

//fun으로 펑션을 만들게 되면 일급객체로 사용 불가능
fun printNo() = println("No!")