package wony20240107;

public class LoopSleepExample {

  public static void main(String[] args) {
    for(int i=0;i<7;i++){
      try {
        System.out.println("본복: " + (i+1));
        Thread.sleep(2000);
        System.out.println("Hello World");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }

}
