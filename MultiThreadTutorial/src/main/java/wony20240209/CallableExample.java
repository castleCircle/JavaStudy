package wony20240209;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    ExecutorService executors = Executors.newFixedThreadPool(1);

    Callable<Integer> callable = () ->{
      System.out.println("Callbale 작업 수행중..");
      System.out.println("Callbale 작업 완료.");
      return 43;
    };

    final Future<Integer> submit = executors.submit(callable);

    System.out.println(submit.get());

  }

}
