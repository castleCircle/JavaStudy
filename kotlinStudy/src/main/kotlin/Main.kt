fun main(args: Array<String>) {

    var a : Int? = null;
    a = 123
    println(a);

    var boolean : Boolean = true;
    boolean = false
    println(boolean)

    // """ """ 은 줄바꿈 등 가능
    var test = """sdf
        |ㄴㅇㄹsdfsad"33fd'\awewkasdkewo3290@12'03'" """.trimMargin()
    println(test)

    //함수

    println(add(1,2,3))

    println(addString(1,"s"));
}

fun add(a:Int, b:Int, c:Int) = a + b + c

fun addString(b:Long, c:String) : String{
    return b.toString() + c;
}