package wony20240210;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancellExample {

  public static void main(String[] args) throws InterruptedException {

    final ExecutorService executorService = Executors.newFixedThreadPool(1);

    Callable<Integer> callableTask = () -> {
      System.out.println("비동기 작업 시작..");
      Thread.sleep(1000);
      System.out.println("비동기 작업 완료");

      return 42;
    };

    final Future<Integer> future = executorService.submit(callableTask);

    future.cancel(false);
//    future.cancel(true);


    try{
      int result = future.get();
      System.out.println("result:" + result);
    }catch (ExecutionException | InterruptedException e){

    }

    executorService.shutdown();

  }

}
