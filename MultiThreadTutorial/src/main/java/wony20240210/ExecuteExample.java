package wony20240210;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteExample {


  public static void main(String[] args) {

    final ExecutorService executorService = Executors.newSingleThreadExecutor();

    executorService.execute(()->{
      System.out.println("비동기 작업 실행");
    });

    executorService.shutdown();
  }
}
