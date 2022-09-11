package `0909`

fun main(){

    val list = listOf("a","b","c")

    val printStr : (String) -> Unit = {println(it)}

    for(a in list){
        printStr(a)
    }

    forEachStrFunc(list,printStr)

    list.forEach{
        println(it)
    }

    forEachStrFunc(list){
        println("===" + it + "-----")
        printStr(it)
    }

    println("===========")

    arg1Test {
    println("bb")
    }

    arg2Test{a:String,b:String ->
        println(a.length)
        b.first()
    }

    var anonymous = fun(q:Int,w:Int) = q+w
    println(anonymous(11,22))
}

fun arg1Test(block: (String) -> Unit){
    block("a");
    println("aaa")
}
fun arg2Test(block: (String,String) -> Unit){println("bbb")}

fun outerFunc1() : () -> Unit = {println()}
fun forEachStrFunc(collection: Collection<String>,action: (String)-> Unit){
    for(item in collection){
        action(item)
    }
}