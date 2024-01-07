package wony20240107;

public class MultiThreadSleepExample {

  public static void main(String[] args) {
    final Thread thread = new Thread(() -> {
      try {
        System.out.println("1초 후에 메시지가 출력됩니다.");
        Thread.sleep(1000);
        System.out.println("스레드 1이 깨어났습니다.");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    final Thread thread2 = new Thread(() -> {
      try {
        System.out.println("2초 후에 메시지가 출력됩니다.");
        Thread.sleep(3000);
        System.out.println("스레드 2이 깨어났습니다.");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    thread.start();
    thread2.start();

    System.out.println("여기는 메인입니다.");

  }

}
