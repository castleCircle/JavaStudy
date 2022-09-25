package `0925`

fun drawCircle(){
    var circle = object {
        val x = 10
        val y = 20
        val radius = 30
    }
    println("Circle :x ${circle.x}")
}

//fun createRunnable(): Runnable{
//    val runnable = object : Runnable{
//        override fun run() {
//            println("You called")
//        }
//    }
//    return runnable;
//}

object Util{
    fun numberOfProccessors() = Runtime.getRuntime().availableProcessors()
}


fun createRunnable(): Runnable = Runnable{println("yucalled")}

fun main() {
    drawCircle()

    val runnable = createRunnable()
    runnable.run()

    println(Util.numberOfProccessors())
}