package study;

@FunctionalInterface
interface FunctionTest1<T1,T2,T3,R>{
  R apply(T1 t1,T2 t2,T3 t3);
}

public class Function06Study {

  public static void main(String[] args) {
    System.out.println("test");
    printlnTest(11,22,33,(t1,t2,t3)->{
      return String.valueOf(t1+t2+t3);
    });
  }

  public static <T1,T2,T3> void printlnTest(T1 t1,T2 t2,T3 t3,FunctionTest1<T1,T2,T3,String> functionTest1){
    System.out.println(functionTest1.apply(t1,t2,t3));
  }

}
