package `0824`

fun main(){

    var age:Int = 10

    //코틀린의 if else는 표현식이다. 자바는 구문이다.
    var test = if(age>10){
        "성인"
    }else{
        "아이"
    }

    println(test)



    //코틀린은 삼항 연산자가 없다. if...else가 표현식이므로 불필요하다.
    val a = 1
    val b = 2
    val c =if(b>a) b else a

    //자바 코드를 코틀린의 when 식으로 반환한 코드
    val day = 2

    val result = when(day){
        1 -> "월요일"
        1 -> "화월요일"
        1 -> "수요일"
        1 -> "목요일"
        else -> "기타"
    }

    println(result)

    when(getColor()){
        Color.RED -> println("red")
        Color.GREEN -> println("green")
        else -> println("blue")
    }

    when(getNumber()){
        0,1 -> println("0 or 1")
        else -> println("0 or 1이 아님")
    }

}

enum class Color{
    RED,GREEN , BLUE
}

fun getColor() = Color.RED

fun getNumber() = 2