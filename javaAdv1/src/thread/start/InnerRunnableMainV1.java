package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV1 {

  public static void main(String[] args) {
    log("main start");

    final MyRunnable myRunnable = new MyRunnable();
    final Thread thread = new Thread(myRunnable);
    thread.start();

    log("main end");
  }

  static class MyRunnable implements Runnable{

    @Override
    public void run() {
      log("run()");
    }
  }

}