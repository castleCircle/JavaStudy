package `0906`


fun main(){

//    val str:String ?= "안녕"
    val str:String ?= null


    //null이 아닌경우에 let을 사용한다.

    val result: Int? = str?.let{
        1234
    }

    println(result)


}