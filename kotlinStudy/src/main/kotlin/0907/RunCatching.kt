package `0907`


fun getStr(): Nothing = throw Exception("예외 발생 기본 값으로 초기화")

fun main(){

//    val result = try{
//        getStr()
//    }catch (e:Exception){
//        println(e.message)
//        "기본값"
//    }

    val result = runCatching { getStr() }
        .getOrElse {
            println(it.message)
            "기본값"
        }

    println(result)

    val result1 = runCatching { "성공" }.getOrNull()

    println(result1)

//    val result2 = runCatching { getStr() }.exceptionOrNull()
//
//    result2?.let {
//        println(it.message)
//        throw it
//    }

    val result3 = runCatching {getStr() }.getOrDefault("기본 값")
    println(result3)

//    val result4:Nothing = runCatching { getStr() }.getOrThrow()


    val result5 = runCatching { "안녕" }
        .map {
            "${it}하세요"
        }.getOrThrow()

    println(result5)

    val result6 = runCatching { "안녕" }.mapCatching { throw Exception("예외") }.getOrDefault("기본값")

    println(result6)

    val result7 = runCatching { getStr() }.recover { "복구" }.getOrNull()

    println(result7)

}