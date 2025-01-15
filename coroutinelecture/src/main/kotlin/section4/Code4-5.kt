package sample.section4

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val convertImageJob: Job = launch(Dispatchers.Default) {
        Thread.sleep(1000L)
        println("[${Thread.currentThread().name}] convert image1")
    }

    val convertImageJob2: Job = launch(Dispatchers.Default) {
        Thread.sleep(1000L)
        println("[${Thread.currentThread().name}] convert image2")
    }

    joinAll(convertImageJob, convertImageJob2)

    val uploadImageJob : Job = launch(Dispatchers.IO) {
        println("[${Thread.currentThread().name}] upload image1,2")
    }
}