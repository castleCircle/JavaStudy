package wony20240209;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {

  public static void main(String[] args) {

    final ExecutorService executorService = Executors.newFixedThreadPool(1);

    final Future<Integer> future = executorService.submit(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return 42;
    });

    System.out.println("비동기 작업 시작");

    try {
      System.out.println(future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    executorService.shutdown();

  }

}
