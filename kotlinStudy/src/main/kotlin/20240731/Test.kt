package `20240729`

fun main(){
    val cage = Cage5(mutableListOf(Eagle(),Eagle()))
}

abstract class Bird(
    name: String,
    private val size: Int
): Animal(name), Comparable<Bird> {

    override fun compareTo(other: Bird): Int {
        return this.size.compareTo(other.size)
    }
}

class Sparrow: Bird("침새",100)
class Eagle: Bird("독수리",100)

class Cage5<T>(
    private val animals: MutableList<T> = mutableListOf()
) where T : Animal , T: Comparable<T>{

    fun printAfterSorting(){
        this.animals.sorted()
            .map{it.name}
            .let{println(it)}
    }


}