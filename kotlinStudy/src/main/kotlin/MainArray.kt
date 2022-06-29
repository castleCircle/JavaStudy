fun main(args: Array<String>){

    var intArr = arrayOf(1,2,3,4,5)
    var nullArr = arrayOfNulls<Int>(5)

    nullArr[0] = 1
    nullArr[1] = 1
    nullArr[3] = 3

    println(intArr[2]);
    println(intArr.size)
    println(nullArr[0]);
    println(nullArr[3]);

    var a = 1
    var s1 = "a is $a"

    println(s1);
}
