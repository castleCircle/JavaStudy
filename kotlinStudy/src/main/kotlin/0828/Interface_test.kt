package `0828`

interface worker{

    fun work()

}

class PayWorker(var status:String="0") : worker{

    override fun work() {
        println(status)
    }

}


fun main(){
    var payWorker = PayWorker("10")
    payWorker.work()
}