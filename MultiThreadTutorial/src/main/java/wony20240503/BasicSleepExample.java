package wony20240503;

public class BasicSleepExample {

  public static void main(String[] args) {
    for(int i=0;i<7;i++) {
      try {
        System.out.println("반복: " + (i + 1));
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

}
