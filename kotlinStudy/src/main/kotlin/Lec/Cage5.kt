package Lec


fun main() {
    val fishCage = Cage3<Fish>()
    val goldFish = Cage3<GoldFish>()
    val animalCage: Cage3<Animal> = fishCage
}

abstract class Bird(
    name: String,
    private val size: Int,
): Animal(name), Comparable<Bird> {



}

class Cage5<T>(
    private val animals: MutableList<T> = mutableListOf()
)  where T : Animal, T: Comparable<T> {

    fun printAfterSorting(){
        this.animals.sorted()
            .map { it.name }
            .let { println(it) }
    }


    fun getFirst(): T{
        return animals.first()
    }

    fun put(animal: T){
        animals.add(animal)
    }

    fun moveFrom(cage: Cage5<T>){
        this.animals.addAll(cage.animals)
    }
}

fun <T> List<T>.hasIntercetion(other: List<T>): Boolean{
    return (this.toSet().intersect(other.toSet())).isNotEmpty()
}