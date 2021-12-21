package chapter9;

public class ClosureExamples {

  private int number = 999;

  public static void main(String[] args){
    new ClosureExamples().test1();
  }

  private void test1() {

    //원래는 익명함수에서는 final이 아니면 사용을 못했다. 자바 8 이전에는
    //int number만 가능 => effectively final 의미는 파이널이고 값을 수정하면 익명함수에서 못쓴다. => lamda도 똑같다
    //final처럼 써야한다.
    final int number = 100;
//
    testClosure("anonyClass",
       new Runnable() {
      @Override
      public void run() {
        System.out.println(number);
      }
    });

    testClosure("lamda" , () -> System.out.println(this.number));

    //anonyClass에서 this.number을 하니까 에러가 남
    //ClosureExamples.this.number을 해야함 new Runnable()을 가르킨다. this는
    //람다는 가능하다. 람다는 스코프가 없다. 그래서
  }

  private static void testClosure(final String name , final Runnable runnable){
    System.out.println("Start " + name + ": " );
    runnable.run();
    System.out.println();
  }

}
