fun main() {

    var a = Person("홍길동",1990)
    var b = Person("홍길동1",1992)
    var c = Person("홍길동2",1991)

    println("${a.birthYear}년생 입니다")

    a.introduce()
    b.introduce()
    c.introduce()

    a.name = "1";

    a.introduce();

}

class Person(var name:String,val birthYear:Int){
    fun introduce(){
        println("안녕하세요, ${birthYear}년생 ${name}입니다.")
    }
}