package thread.executor.future;

import static util.MyLogger.log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SumTaskMainV2 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    final SumTask task1 = new SumTask(1, 50);
    final SumTask task2 = new SumTask(51, 100);

    final ExecutorService es = Executors.newFixedThreadPool(2);

    final Future<Integer> future1 = es.submit(task1);
    final Future<Integer> future2 = es.submit(task2);

    final Integer sum1 = future1.get();
    final Integer sum2 = future2.get();

    log("task1.result=" + sum1);
    log("task2.result=" + sum2);

    int sumAll = sum1 + sum2;
    log("task1 + task2 = " + sumAll);
    log("End");

  }

  static class SumTask implements Callable<Integer>{

    int startValue;
    int endValue;

    public SumTask(int startValue, int endValue) {
      this.startValue = startValue;
      this.endValue = endValue;
    }

    @Override
    public Integer call() throws Exception {
      log("작업 시작");
      Thread.sleep(2000);
      int sum = 0;
      for(int i = startValue; i <= endValue;i++){
        sum += i;
      }

      log("작업 완료 result = " + sum);
      return sum;
    }
  }

}
