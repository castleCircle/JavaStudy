package sample.section4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val updateToken = launch(Dispatchers.IO) {
        println("[${Thread.currentThread().name}] Updating token start...]")
        delay(100L)
        println("[${Thread.currentThread().name}] Updating token end...]")
    }

    updateToken.join()

    val networkCallJob = launch(Dispatchers.IO) {
        println("[${Thread.currentThread().name}] Network calling start...")
    }
}