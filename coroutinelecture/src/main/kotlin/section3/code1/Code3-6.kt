package sample.section3.code1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking<Unit> {
    val imageProcessingDispatcher = Dispatchers.Default.limitedParallelism(2)
    repeat(100){
        launch(imageProcessingDispatcher){
            Thread.sleep(1000L)
            println("[${Thread.currentThread().name}] Image processing 완료  ")
        }
    }
}