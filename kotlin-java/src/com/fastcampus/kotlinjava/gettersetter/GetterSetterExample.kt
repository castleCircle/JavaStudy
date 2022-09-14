package com.fastcampus.kotlinjava.gettersetter

fun main() {

    // 자바 스타일
    val person = Person()
    person.setName("토니")
    person.setAge(44)
    person.setAddress("스타크")

    println(person.getName())
    println(person.getAge())

    val person2 = Person()
    person2.name = "토니스타크"
    person2.age = 44

    println(person2.name)
    println(person2.age)

    //프로퍼티가 없어도 게터 메서드라면 프로퍼티처럼 사용 가능
    println(person2.uuid)

    person2.myAddress()

}