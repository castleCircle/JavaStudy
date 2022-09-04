package `0904`


sealed class Developer{

    abstract val name: String
    abstract fun code(language: String)

}

data class BackendDeveloper(override val name:String) : Developer(){

    override fun code(language: String) {
        println("저는 백엔드 개발자 입니다 ${language}를 사랑합니다.")
    }

}

data class FrontendDeveloper(override val name:String) : Developer(){

    override fun code(language: String) {
        println("저는 프론트 개발자 입니다 ${language}를 사랑합니다.")
    }

}

object OtherDeveloper : Developer(){
    override val name: String = "익명"

    override fun code(language: String) {
        TODO("Not yet implemented")
    }

}

object DeveloperPool{
    val pool = mutableMapOf<String,Developer>()

    fun add(developer: Developer) = when(developer){
        is BackendDeveloper -> pool[developer.name] = developer
        is FrontendDeveloper -> pool[developer.name] = developer
        is OtherDeveloper -> println("지원하지 않는 개발자종류 입니다.")
    }

    fun get(name:String) = pool[name]

}

fun main(){
    val backendDeveloper = BackendDeveloper("토니")
    DeveloperPool.add(backendDeveloper)

    val frontendDeveloper = FrontendDeveloper("카즈아")
    DeveloperPool.add(frontendDeveloper)

    println(DeveloperPool.get("토니"))
    println(DeveloperPool.get("카즈아"))
}