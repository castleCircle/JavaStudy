fun main() {

    var a = 0

    while(a<5){
        println(a++);
    }

    for(i in 0..9 step 3){
        print(i);
    }
    println()

    for(i in 9 downTo 0){
        print(i);
    }

    println();
    for(i in 'a'..'e'){
        print(i);
    }


    for(i in 1..10){
        if(i==3) continue;
        print(i);
    }
    println();


    println(true && false);
    println(true);

    println(testtWhen("3"))
    factory('a')
}


fun testtWhen(a : Any) : Any{
    return when(a){
        1L -> "1이다"
        "3" -> "문자3"
        else -> "else test"
    }
}

fun factory(a: Any) : Any{
    return when(a){
        'a' -> b(a);
        else -> "없음"
    }
}

fun b(input : Any){
    println(input)
}