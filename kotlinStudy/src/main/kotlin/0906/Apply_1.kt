package `0906`

fun main(){

    val client: DatabaseClient = DatabaseClient().apply {
        url = "localhost:3306"
    }

    client.connect().run { println(this) }


}