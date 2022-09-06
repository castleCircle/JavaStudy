package `0906`

fun main(){

    val str = "안녕"

    with(str) {
        println("length=$length")

    }

    val length :Int = with(str){
        length
    }

    println(length)

    val result : Boolean = with(DatabaseClient()){
        url = "localhost:3306"
        connect()
    }

    println(result)


}