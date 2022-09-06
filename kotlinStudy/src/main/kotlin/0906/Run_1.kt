package `0906`

class DatabaseClient{
    var url: String? =null
    var username:String? =null
    var password:String?= null

    fun connect(): Boolean{
        println("DB 접속중")
        Thread.sleep(1000)
        println("DB접속 완료")
       return true
    }
}

fun main(){

    val config = DatabaseClient()
    config.url = "localhost:3306"
    config.username = "mysql"
    config.password = "1234"
    val connected = config.connect()

    println(connected)


    val connected1 = DatabaseClient().run{
        url= "localhost:.3306"
        username = "mysql"
        password = "1234"
        connect()
    }
    println(connected1)

}