package wony20240108;

public class MultiThreadJoinExample {

  public static void main(String[] args) throws InterruptedException {

    final Thread thread = new Thread(() -> {
      System.out.println("thread 시작중");
      try {
        Thread.sleep(3000);
        System.out.println("thread 종료");
      } catch (InterruptedException e) {
        System.out.println("Itner");
        e.printStackTrace();
      }
    });

    final Thread thread1 = new Thread(() -> {
      System.out.println("thread1 시작중");
      try {
        Thread.sleep(4000);
        System.out.println("thread1 종료");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    thread.start();
    thread1.start();

    thread.join();
    thread1.join();


    System.out.println("메인 시작");

  }

}
