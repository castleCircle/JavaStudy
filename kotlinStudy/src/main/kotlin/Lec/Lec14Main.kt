package Lec


fun main(){
    val dto1 = PersonDto("name",100);

}


data class PersonDto(
    val name: String,
    val age:Int
)