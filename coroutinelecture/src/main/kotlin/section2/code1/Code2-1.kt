package sample.section2.code1

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//main 스레드를 점유하는 코투린
fun main() = runBlocking<Unit>(CoroutineName("runBlocking 코루틴")) {
    println("[${Thread.currentThread().name}] runBlocking 코루틴 실행")

    launch(CoroutineName("launch 코루틴")) {
        println("[${Thread.currentThread().name}] launch 코루틴 실행")
    }
}