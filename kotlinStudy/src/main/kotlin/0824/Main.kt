package `0824`


//표현식 스타일
fun sum2(a:Int,b:Int) : Int = a+b

//표현식 & 반환타입 생략
fun sum3(a:Int,b:Int) = a+b

//몸통이 있는 함수는 반환 타입을 제거하면 컴파일 오류
fun sum4(a:Int,b:Int) : Int{
    return a+b
}

//반환타입이 없는 함수는 Unit을 반환한다
fun printSum(a:Int,b:Int){
    println("$a + $b = ${a+b}")
}

fun greeting(message:String="안녕하세요!!"){
    println(message)
}

//fun main(){
//   greeting()
//   greeting("HI")
//}


//named argument
fun log(level:String="INFO",message:String){
    println("[$level]$message")
}

//fun main(){
//    log(message="인포로그")
//    log(level="DEBUG",message="디버그 로그")
//    log("WARN","워닝 로그")
//    log(level="ERROR",message="워닝 로그")
//    log(message= "tt",level="ss")
//}

fun fruit(type:String="과일",message:String){
    println("type:$type, message:$String")
}

fun main(){
    fruit("사과","달다")
    fruit(type="바나나","길다")

}



