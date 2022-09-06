package `0906`

class User(val name:String,val password: String){

    fun validate(){
        if(name.isNotEmpty() && password.isNotEmpty()){
            println("검증성공!")
        }else{
            println("검증실패!")
        }
    }

}


fun main(){

    User(name="tony",password="12334").also {
        it.validate()
    }


}