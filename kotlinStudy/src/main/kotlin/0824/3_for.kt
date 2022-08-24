package `0824`

fun main(){

    //범위 연산자 ..를 사용해 for loop 돌리기
    for(i in 0..3){
        println(i)
    }

    //until을 사용해 반복한다.
    //뒤에오는 숫자는 포함하지 않음
    for(i in 0 until 3){
        println(i)
    }

    //step에 들어온 값 만큼 증가시킨다
    for(i in 0..6 step 2){
        println(i)
    }

    for(i in 0 until 6 step 2){
        println(i)
    }

    for(i in 3 downTo 1){
        println(i)
    }

    //전달받은 배열을 반복
    var numbers = arrayOf(1,2,3,"b")

    for(i in numbers){
        println(i)
    }


}