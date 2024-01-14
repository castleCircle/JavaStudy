package wony20240114;

public class DaemonThreadLifeCycleExample {

  public static void main(String[] args) throws InterruptedException {
    final Thread userThread = new Thread(() -> {
      try {
        Thread.sleep(1000);
        System.out.println("사용자 스레드 실행 중 ");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    final Thread daemonThread = new Thread(() -> {
      while(true) {
        try {
          Thread.sleep(200);
          System.out.println("데몬 스레드 실행 중");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    daemonThread.setDaemon(true);

    userThread.start();
    daemonThread.start();

    userThread.join();

    System.out.println("메인 스레드 종료");


  }

}
