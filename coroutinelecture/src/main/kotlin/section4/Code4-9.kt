package sample.section4

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()
    val longJob:Job = launch(Dispatchers.Default){
        repeat(10){repeatTime ->
            delay(1000L)
            println("[${getElapsedTime(startTime)}] 반복횟수 $repeatTime")
        }
    }

    delay(2500L)
    longJob.cancel()
}

fun getElapsedTime(startTime: Long): String =
    "지난 시간: ${System.currentTimeMillis() - startTime} ms"