package sample.section4

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()
    val lazyJob: Job = launch(start=CoroutineStart.LAZY){
        println("lazyJob start")
    }
}