package thread.study;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainTestV1 {

  public static void main(String[] args) throws InterruptedException {
    MyTaskStudy study = new MyTaskStudy();
    Thread thread = new Thread(study);
    thread.start();

    sleep(100);
    thread.interrupt();
    log("work 스레드 인터럽트 상태1:" + thread.isInterrupted());
  }

  static class MyTaskStudy implements Runnable {

    volatile boolean runFlag = true;

    @Override
    public void run() {
      while (!Thread.interrupted()) {
        log("작업중");
      }

      log("work 스레드 인터럽트 상태 2:" + Thread.currentThread().isInterrupted());

      try{
        log("자원 정리 시도");
        Thread.sleep(1000);
        log("자원 정리 완료");
      }catch (InterruptedException e){
        log("자원 정리 실패 - 자원정리중 인터럽트 발생");
        log("work 스레드 인터럽트 상태 3:" + Thread.currentThread().isInterrupted());
      }
    }
  }

}
