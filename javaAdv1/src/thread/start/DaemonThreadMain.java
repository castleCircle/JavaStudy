package thread.start;

public class DaemonThreadMain {

  /**
   * daemon Thread는 사용자 스레드가 사라지면 바로 종료된다.
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + ": main() start");
    DaemonThread daemonThread = new DaemonThread();
    daemonThread.setDaemon(true);
//    daemonThread.setDaemon(false);
    daemonThread.start();

    System.out.println(Thread.currentThread().getName() + ": main() end");
  }

  static class DaemonThread extends Thread {

    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() +": run() start");
      try{
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      System.out.println(Thread.currentThread().getName() + ": run() end");
    }
  }

}
