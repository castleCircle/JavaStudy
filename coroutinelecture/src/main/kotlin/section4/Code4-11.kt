package sample.section4

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val longJob: Job = launch(Dispatchers.Default){
        Thread.sleep(1000L)
        println("longJob 코루틴의 동작")
    }
    longJob.cancel()
    executeAfterJobCancel()
}

fun executeAfterJobCancel(){
    println("longJob 코루틴 취소 후 실행돼야 하는 동작")
}