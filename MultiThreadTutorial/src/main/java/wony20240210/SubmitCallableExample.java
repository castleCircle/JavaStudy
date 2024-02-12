package wony20240210;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitCallableExample {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    final ExecutorService executorService = Executors.newSingleThreadExecutor();

    final Future<String> future = executorService.submit(new Callable<String>() {
      @Override
      public String call() throws Exception {
        System.out.println("비동기 작업 실행..");
        return "Hello";
      }
    });

    final String result = future.get();
    System.out.println("result:" + result);

    executorService.shutdown();
  }
}
