package Lec

import kotlin.reflect.KProperty

class Person3{
    private val delegateProperty = LazyInitProperty {
        Thread.sleep(2000)
        "김수한무"
    }
//   val name: String
//       get() = delegateProperty.value

    val name: String by LazyInitProperty{
        Thread.sleep(2000)
        "김수한무"
    }
}

class LazyInitProperty<T>(val init: () -> T){
    private var _value: T? = null
    val value: T
        get(){
            if(_value == null){
                this._value = init()
            }
            return _value!!
        }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T{
        return value
    }
}

fun main(){

    val person = Person()
    person.name = "22"

}