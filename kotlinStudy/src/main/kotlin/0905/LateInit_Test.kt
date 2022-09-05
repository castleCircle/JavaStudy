package `0905`

class LateClass{

    lateinit var text:String

    fun printText(){
        if(this::text.isInitialized){
            text = "초기화"
        }else{
            text = "하잇"
        }

        println(text)
    }

}


fun main(){
    val lateClass = LateClass()
    lateClass.printText()
    lateClass.printText()

}