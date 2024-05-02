package wony20240503;

public class MultiThreadJoinExample {

  public static void main(String[] args) throws InterruptedException {
    final Thread thread = new Thread(() -> {
      try {
        System.out.println("1초후에 메시지가 출력됩");
        Thread.sleep(1000);
        System.out.println("스레드1 깨어남");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    final Thread thread2 = new Thread(() -> {
      try {
        System.out.println("2초후에 메시지가 출력됩");
        Thread.sleep(2000);
        System.out.println("스레드2 깨어남");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    thread.start();
    thread2.start();

    System.out.println("메인 스레드가 기다림");

    thread.join();
    thread2.join();

    System.out.println("메인 스레드 종료");
  }

}
