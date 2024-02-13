package wony20240213;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureExample {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    final int findResult = CompletableFuture.supplyAsync(
        () -> {
          System.out.println("Service1 시작");
          return 1;
        }).thenApplyAsync(result1 -> {
      System.out.println("Service2 시작");
      return result1 + 2;
    }).thenApplyAsync(result2 -> {
      System.out.println("Service3 시작");
      return result2 + 3;
    }).thenApplyAsync(result3 -> {
      System.out.println("Service4 시작");
      return result3 + 4;
    }).get();

    System.out.println(findResult);

  }

}
