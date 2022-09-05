package `0905`

class LateInit{

    lateinit var text: String

    fun printText(){

        if(this::text.isInitialized){
            println("초기화됨")
        }else{
            text = "안녕하세요"
        }
        println(text)
    }

}

fun main(){

    val test = LateInit()
    test.printText()
    test.printText()

}