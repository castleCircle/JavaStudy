package wony20240210;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExample {

  public static void main(String[] args) {

    final ExecutorService executorService = Executors.newCachedThreadPool();

    for(int i=0;i<10;i++){
      final int taskId = i;
      executorService.execute(()->{
        System.out.println("Task " + taskId + "is executeing on " + Thread.currentThread().getName());
      });
    }

    try{
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    executorService.shutdown();
  }

}
