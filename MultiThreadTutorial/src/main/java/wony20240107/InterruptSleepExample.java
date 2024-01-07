package wony20240107;

public class InterruptSleepExample {

  public static void main(String[] args) throws InterruptedException {
    final Thread thread = new Thread(() -> {
      try {
        System.out.println("아무 작업을 하지 않으면 10초 동안 sleep");
        Thread.sleep(10000);
        System.out.println("작업 완료");
      } catch (InterruptedException e) {
        System.out.println("InterruptSleep을 받았네");
      }
    });

    thread.start();

    Thread.sleep(5000);

    thread.interrupt();

    System.out.println("hello");
  }

}
