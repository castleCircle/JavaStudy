package `20240804`

fun main(){
    //람다식
    compute(5,3){ a,b -> a+b}

    //익명함수
    compute(5,3,fun(a,b) = a+b)
}


//고차함수
fun compute(num1 : Int,num2 : Int, op : (Int,Int) -> Int) : Int{
    return op (num1,num2)
}


