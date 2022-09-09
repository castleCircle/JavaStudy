package `0909`



fun main(){

    val list = listOf("a","b","c")

    val printStr : (String) -> Unit = {println(it)}

    forEachStr(list,printStr)

    list.forEach(printStr)


//    list.map{
//        it.uppercase()
//    }

    val upperCase : (String) -> String = {it.uppercase()}

    println(list.map(upperCase))

    val func = outerFunc()
    func()

    //후행람다
    forEachStr(list){
        println(it)
    }

    println("------")

    arg1{
        it.length
        it.first()
    }

    arg2{ a:String, b:String ->
        a.length
        b.first()
    }

//    val callReference:() -> Unit = {printHello()}
    //람다 reference
    val callReference = ::printHello1

    callReference()()

    val numberList = listOf("1","2","3")
//    numberList.map{ it.toInt()}.forEach { println(it) }
    //::println은 탑레벨이나 로컬에 있는 함수들은 앞에 클래스 명이 필요없다.
    numberList.map(String::toInt).forEach(::println)
}

val printHello1 : () -> Unit = {println("Hello")}

fun arg1(block : (String) -> Unit){}
fun arg2(block : (String,String) -> Unit){}

val sum1 : (Int,Int) -> Int = {x,y -> x+y}

val sum2 = {x: Int,y:Int -> x+y}

//fun outerFunc() : ()-> Unit{
////    return fun(){
////        println("이것은 익명함수!")
////    }
//
//    return {
//        println("이것은 익명함수!")
//    }
//}

//익명함수
fun outerFunc() : ()-> Unit = {println()}

fun forEachStr(collection: Collection<String>, action: (String) -> Unit){
    for(item in collection){
        action(item)
    }
}