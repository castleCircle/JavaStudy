package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {

  public static void main(String[] args) {
    MyTask task = new MyTask();
    Thread thread = new Thread(task,"work");
    thread.start();

    sleep(100);
    log("작업 중단 지시 thread.inturrput");
    thread.interrupt(); //이걸 한다고 바로 인터럽트가 터지지 않는다. sleep , join 과 같은 애들이 있어야 가능
    log("work 스레드 입터럽트 상태1 = " + thread.isInterrupted()); // true
  }

  static class MyTask implements Runnable{

    @Override
    public void run() {
      while(!Thread.currentThread().isInterrupted()){ // 인터럽트 상태 변경 X
        log("작업중");
      }
      log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted()); // 깨워났기 때문에 interrupt 상태에서 벗어난거다. ,false
      log("자원 정리");
      log("자원 종료");

      try{
        log("자원정리;");
        Thread.sleep(1000);
        log("자원종료;");
      }catch (InterruptedException e){
        log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
        log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());
      }

    }
  }

}
