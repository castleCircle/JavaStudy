package sample.section3.code1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//runBlocking 코루틴이 가장 부모 코루틴이고 launch가 그 아래이고 그 안에 또 launch 코루틴들이 붙는 개념이다.
fun main() = runBlocking<Unit> {
    launch(Dispatchers.IO) {
        launch {
            println("[${Thread.currentThread().name}] 작업1 실행")
        }
        launch {
            println("[${Thread.currentThread().name}] 작업2 실행")
        }
        launch {
            println("[${Thread.currentThread().name}] 작업3 실행")
        }
    }
}