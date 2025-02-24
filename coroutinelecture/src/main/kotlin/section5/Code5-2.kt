package sample.section5

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val result = withContext(Dispatchers.IO){
        delay(1000L)
        println("[${Thread.currentThread().name}] 결과값이 반환됩니다")
        "결과값"
    }

    println("[${Thread.currentThread().name}] $result")
}