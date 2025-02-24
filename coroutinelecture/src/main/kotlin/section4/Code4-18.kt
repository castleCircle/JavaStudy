package sample.section4

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val whileJob: Job = launch {
        delay(1000L)
    }
    println(whileJob)
}