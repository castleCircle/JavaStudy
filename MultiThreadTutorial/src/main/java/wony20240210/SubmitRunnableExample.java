package wony20240210;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitRunnableExample {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    final ExecutorService executorService = Executors.newSingleThreadExecutor();

    final Future<?> future = executorService.submit(new Runnable() {
      @Override
      public void run() {
        System.out.println("비동기 작업 실행");
      }
    });

    final Object result = future.get();
    System.out.println("result:" + result);

    final Future<Integer> future1 = executorService.submit(new Runnable() {
      @Override
      public void run() {
        System.out.println("비동기 작업 실행");
      }
    },100);

    final Integer result1 = future1.get();
    System.out.println("result1:" + result1);

    executorService.shutdown();
  }
}
