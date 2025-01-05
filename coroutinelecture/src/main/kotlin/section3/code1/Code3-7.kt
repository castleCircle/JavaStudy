package sample.section3.code1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking<Unit> {
    val dedicateDispatcher = Dispatchers.IO.limitedParallelism(2)
    repeat(100){
        launch(dedicateDispatcher){
            println("[${Thread.currentThread().name}] Image processing 완료  ")
        }
    }
}