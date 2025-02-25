package Lec

fun main(){

    //명시적 변환, 암시적 변환 X
    val number = 3
    val number2: Long = number.toLong()

    //
    val number1: Int? = 3
    val number4: Long = number1?.toLong() ?: 0


}

fun getGrade(score : Int): String{
    return if (score> 90){
        "1"
    }else{
        "2"
    }
}

//kotlin에서는 if-else를 자바와 달리 expression으로 사용가능
fun getGrade1(score : Int): String{
    return if (score> 90){
        "1"
    }else{
        "2"
    }
}
