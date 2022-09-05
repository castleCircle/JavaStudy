package `0905`

class GenericTest<out T>(val t:T){

}


class Bag1<T>{

    fun saveAll(
        to: MutableList<in T>,
        from: MutableList<T>
    ){
       to.addAll(from)
    }

}



fun main(){

    val tt = GenericTest<String>("test");

    val charGeneric:GenericTest<CharSequence> = tt;

    val bag = Bag1<String>()
    bag.saveAll(mutableListOf<CharSequence>("1","2"), mutableListOf<String>("3","4"));
}