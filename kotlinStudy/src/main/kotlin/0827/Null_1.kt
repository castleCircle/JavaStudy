package `0827`


fun getNullStr(): String? = null

fun getLengthIfNotNull(str: String?) = str?.length ?: 0


fun main(){

//    val a : String String= null
//    var b : String ="aabbcc"
//    b = null

//    var a : String?=null
//    println(a?.length)
//
//    val b : Int = if(a!=null) a.length else 0
//    println(b)
//
//
//    //엘비스 연산자
//    val c = a?.length ?: 0
//
//    var test : String?;
//    test = null
//    println(test?.length ?: 0)

    val nullableStr = getNullStr()

    val nullableStrLength = nullableStr?.length ?: "null인 경우 반환".length

    println(nullableStrLength)

    val length = getLengthIfNotNull(null)
    println(length)

    //NPE 발생할수 있는 코드

//    throw NullPointerException()

    //? 안전연산자
    //단언 연산자 : 개발자가 컴파일러에게 이코드는 null을 반환하지 않는다 명시적으로 해주는거임.(개발자가 핸들링한다고 생각하면 됨)
    val c: String?=null
//    val d = c?.length
    val d = c!!.length

}