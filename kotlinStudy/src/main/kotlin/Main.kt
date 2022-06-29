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


    //if

    a = 7

    if(a > 10){
        println("a는 10보다 크다")
    }else{
        println("a는 10보다 작다")
    }

    doWhen(1);
    doWhen(2)
    doWhen("saaddsa")

    a = 0;

    while(a<5){
        a++;
    }

    println(a);

    do{
        a++;
    }while(a<10);


    var q = 20;

    for(i in 0..q step 2){
        print(i);
    }

    println()

    for(i in 10 downTo 1){
        print(i)
    }
}

fun doWhen(a:Any) : Any{
    val test = when(a){
        1 -> println("정수 1입니다.")
        "DiMo" -> println("디모의 코틀린 강좌")
        is Long -> println("Long 타입")
        !is String -> println("String 타입")
        else -> "없음"

    }

    return test;
}

fun add(a:Int, b:Int, c:Int) = a + b + c


fun addString(b:Long, c:String) : String{
    return b.toString() + c;
}

