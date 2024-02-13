package wony20240213;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class SupplyAsyncExample {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    MyService service = new MyService();

    final CompletableFuture<List<Integer>> cf = CompletableFuture.supplyAsync(
        new Supplier<List<Integer>>() {
          @Override
          public List<Integer> get() {
            System.out.println(Thread.currentThread().getName() + " 가 비동기 작업을 시작합니다.");
            return service.getData();
          }
        });

    final List<Integer> result = cf.join();
    result.stream().forEach(r-> System.out.println(r));

    System.out.println("======================");

    ExecutorService executorService = Executors.newFixedThreadPool(1);
    final Future<List<Integer>> submit = executorService.submit(() -> {
      System.out.println(Thread.currentThread().getName() + " 가 비동기 작업을 시작");
      return service.getData();
    });

    final List<Integer> integers = submit.get();

    System.out.println("메인 스레드 종료");

  }

}

class MyService{

  public List<Integer> getData() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return Arrays.asList(1,2,3);
  }
}
