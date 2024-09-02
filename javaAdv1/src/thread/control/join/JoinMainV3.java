package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV3 {

  public static void main(String[] args) throws InterruptedException {
    log("start");
    final SumTask sumTask1 = new SumTask(1, 50);
    final SumTask sumTask2 = new SumTask(51, 100);

    Thread thread1 = new Thread(sumTask1,"thread-1");
    Thread thread2 = new Thread(sumTask2,"thread-2");

    thread1.start();
    thread2.start();

    log("join() - main 스레드가 thread1,thread2 종료까지 대기");
    thread1.join();
    thread2.join();
    log("join() - main 스레드가 thread1,thread2 종료까지 완료");

    log("task1.result = " + sumTask1.result);
    log("task2.result = " + sumTask2.result);

    log("task1 + task2 = " + (sumTask1.result + sumTask2.result));

    log("end");
  }

  static class SumTask implements Runnable{

    int startValue;
    int endValue;
    int result = 0;

    public SumTask(int startValue, int endValue) {
      this.startValue = startValue;
      this.endValue = endValue;
    }

    @Override
    public void run() {
      log("작업시작");
      sleep(2000);
      int sum = 0;
      for(int i= startValue;i<=endValue;i++){
        sum += i;
      }
      result = sum;
      log("작업완료 result = " + result);
    }
  }

}
