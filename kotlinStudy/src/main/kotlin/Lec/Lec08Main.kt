package Lec

fun main() {
    //named argument로 builder를 만들지 않고 만든것 같은 효과를 낸다.
    printNameAndGender(name = "ysw", gender = "남자")
    val array = arrayOf("1","2")
    //가변인자 함수를 사용하기 위해선 배열을 바로 넣는 대신 연산자 * 를 붙여줘야한다.
    printAll(*array)
}

fun max(a: Int, b: Int): Int {
    return if(a > b) {
        a
    }else {
        b
    }
}

//함수가 하나의 결과값이면 block 대신 = 사용 가능
//block {} 을 사용하는 경우에는 반환 타입이 Unit이 아니면 명시적으로 작성해주서야 합니다.
fun max1(a: Int, b: Int) =
    if(a > b) {
        a
    }else {
        b
    }

fun printNameAndGender(name: String, gender: String) {
    println("$name, $gender")
}

fun printAll(vararg strings: String){
    for(str in strings){
        println(str)
    }
}