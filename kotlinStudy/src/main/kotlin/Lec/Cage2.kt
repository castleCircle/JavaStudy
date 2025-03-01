package Lec


fun main() {
    val goldFish = Cage2<GoldFish>()
    goldFish.put(GoldFish("금붕어"))

    val cage = Cage2<Fish>()
    cage.moveFrom(goldFish)
}

class Cage{
    private val animals: MutableList<Animal> = mutableListOf()

    fun getFirst(): Animal{
        return animals.first()
    }

    fun put(animal: Animal){
        animals.add(animal)
    }

    fun moveFrom(cage: Cage){
        this.animals.addAll(cage.animals)
    }
}

class Cage2<T>{
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T{
        return animals.first()
    }

    fun put(animal: T){
        animals.add(animal)
    }

    fun moveFrom(cage: Cage2<out T>){
        this.animals.addAll(cage.animals)
    }
}