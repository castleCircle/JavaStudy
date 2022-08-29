package `0829`

enum class DeliveryStatus(val status:String) : Refundable{
    START_BEFORE("배송전"){
        override fun refundable() = false
                       },
    STARTED("배송중"){
        override fun refundable() = false
                  },
    STARTED_COMPLETED("배송완료"){
        override fun refundable() = true
    }
}

interface Refundable{
    fun refundable() : Boolean
}


fun main(){

    println(DeliveryStatus.STARTED.status)

    val status = DeliveryStatus.valueOf("STARTED_COMPLETED")
    val isBoolean = status.refundable()

    if(isBoolean){
        println("환불가능합니다.")
    }

}