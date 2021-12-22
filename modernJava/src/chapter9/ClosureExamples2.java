package chapter9;

public class ClosureExamples2 {

  private int number = 999;

  public static void main(String[] args) {
    int number = 100;

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println(number);
      }
    };
    runnable.run();

    Runnable runnable1 = () -> System.out.println(number);
    runnable1.run();

    //invoke 다이나믹
    //런타임시에 타입을 정할수있게됨

 됨  //Method Handle
    //=> 저장해놨다가 꺼내는것

  }

}
