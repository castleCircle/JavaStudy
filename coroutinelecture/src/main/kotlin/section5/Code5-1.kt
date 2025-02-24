package sample.section5

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val networkDeferred: Deferred<String> = async(Dispatchers.IO) {
        delay(1000L)
        println("[${Thread.currentThread().name}] 결과값이 반환됩니다")
        return@async "Dummy Response"
    }
    val result = networkDeferred.await()
    println("[${Thread.currentThread().name}] $result")
}