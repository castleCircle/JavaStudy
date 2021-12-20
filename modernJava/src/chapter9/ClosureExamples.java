package chapter9;

public class ClosureExamples {

  public static void main(String[] args) {

    //원래는 익명함수에서는 final이 아니면 사용을 못했다. 자바 8 이전에는
    //int number만 가능 => effectively final 결국은 final이지 의미는 파이널이고 값을 수정하면 익명함수에서 못쓴다.
    final int number = 100;

    testClosure("anonyClass",
       new Runnable() {
      @Override
      public void run() {
        System.out.println(number);
      }
    });

    testClosure("lamda" , () -> System.out.println(number));
  }

  private static void testClosure(final String name , final Runnable runnable){
    System.out.println("Start " + name + ": " );
    runnable.run();
    System.out.println();
  }

}
