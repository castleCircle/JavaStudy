package wony20240114;

public class UserAndDaemonInhreitanceExample {

  public static void main(String[] args) {
    final Thread userThread = new Thread(() -> {
      new Thread(() -> {
        System.out.println("사용자 스레드의 자식 스레드의 데몬 상태:" + Thread.currentThread().isDaemon());
      }).start();
      System.out.println("사용자 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
    });

    final Thread daemonThread = new Thread(() -> {
      new Thread(() -> {
        System.out.println("데몬 스레드의 자식 스레드의 데몬 상태:" + Thread.currentThread().isDaemon());
      }).start();
      System.out.println("데몬 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
    });

    daemonThread.setDaemon(true);

    userThread.start();
    daemonThread.start();





  }

}
