package `20240729`


//fun main(){
//
//    val fishCage = Cage2<Fish>()
//
//    val goldFishCage = Cage2<GoldFish>()
//    goldFishCage.put(GoldFish("금붕어"))
//    goldFishCage.moveTo(fishCage)
//
//}
//
//class Cage {
//    private val animals: MutableList<Animal> = mutableListOf()
//
//    fun getFirst(): Animal{
//        return animals.first()
//    }
//
//    fun put(animal: Animal){
//        this.animals.add(animal)
//    }
//
//    fun moveFrom(cage: Cage){
//        this.animals.addAll(cage.animals)
//    }
//
//
//}
//
//class Cage2<T> {
//    private val animals: MutableList<T> = mutableListOf()
//
//    fun getFirst(): T{
//        return animals.first()
//    }
//
//    fun put(animal: T){
//        this.animals.add(animal)
//    }
//
//    //공변
//    fun moveFrom(otherCage: Cage2<out T>){
//        this.animals.addAll(otherCage.animals)
//    }
//
//    //반공변
//    fun moveTo(otherCage: Cage2<in T>){
//        otherCage.animals.addAll(this.animals)
//    }
//
//
//}


fun main(){
    val fishCage = Cage3<Fish>()
    val animalCage: Cage3<Animal> = fishCage
}

//생산만 하는 클래스
class Cage3<out T>{
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst() : T{
        return this.animals.first()
    }

    fun getAll(): List<T>{
        return this.animals
    }
}

//소비만 하는 클래스
class Cage4<in T>{
    private val animals: MutableList<T> = mutableListOf()

}