package thread.control;

import static util.MyLogger.log;

public class ThreadStateMain {

  public static void main(String[] args) throws InterruptedException {
    final Thread thread = new Thread(new MyRunnable(), "myThread");
    log("myThread.state1 = " + thread.getState()); // NEW
    log("myThread.statrt()");
    thread.start();
    Thread.sleep(1000);
    log("myThread.state3 = " + thread.getState()); // TIMED_WAITING
    Thread.sleep(4000);
    log("myThread.state5 = " + thread.getState()); // TERMINATED
  }

  static class MyRunnable implements Runnable{

    @Override
    public void run() {
      try {
        log("start");
        log("myThread.state2 = " + Thread.currentThread().getState()); // runnable
        log("sleep() satart");
        Thread.sleep(3000);
        log("sleep() end");
        log("myThread.state4 = " + Thread.currentThread().getState()); // runnable

        log("end");
      }catch (InterruptedException e){
        throw new RuntimeException(e);
      }
    }
  }

}