package `0828`

open class Animal {
    open var type : String = "동물"
    open fun bark(){
        println("울음소리")
    }
}

class Cat(override var type: String = "") : Animal() {

  override fun bark() {
     super.bark()
  }

}

abstract class Designer{
    abstract var age : Int
    abstract fun skill(type:String)
}

class ADesigner(override var age: Int) : Designer(){

    override fun skill(type: String) {
        TODO("Not yet implemented")
    }
}

fun main(){
    val cat = Cat(type="고양이")
    println(cat.type)
}