package `0905`


class Test{

    val data:String by lazy {
        println("초기화")
        getText()
    }

}


fun getText() = "안녕하세요"


fun main(){

    val test = Test()
    println(test.data)
    println(test.data)


}