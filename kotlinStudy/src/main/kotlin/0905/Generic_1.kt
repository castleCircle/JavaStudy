package `0905`

class MyGenerics<out T>(val t:T){

}

class Bag<T>{

    fun saveAll(
        to: MutableList<in T>,
        from: MutableList<T>
    ){
        to.addAll(from)
    }

}

fun main(){

    //생략가
    val list1: MutableList<String> = mutableListOf()

    val list2 = mutableListOf<String>()

    val list3 : List<*> = listOf<String>("테스트")
    val list4 : List<*> = listOf<Int>(1,2,3,4)

    //공변성은 자바 제네릭의 extends 코틀린에서 out
    //반공변성은 자바 제네릭의 super 코틀린에서 In

    //제네릭을 사용한 클래스의 인스턴스를 만드려면 타입아규먼트를 제공
    val generics = MyGenerics<String>("테스트")
    val charGenerics : MyGenerics<CharSequence> = generics

    val bag = Bag<String>()
    bag.saveAll(mutableListOf<CharSequence>("1","2"), mutableListOf<String>("3","4"));



}