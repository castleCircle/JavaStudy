package wony20240116;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolThreadLocalExample {

  private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

  public static void main(String[] args) {

    ExecutorService executorService = Executors.newFixedThreadPool(2);

    executorService.submit(()->{
      threadLocal.set("작업 1의 값");
      System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
      threadLocal.remove();
    });

    try{
      Thread.sleep(500);
    }catch (Exception e){
      e.printStackTrace();
    }

    for(int i=0;i<5;i++){
      executorService.submit(()->{
        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
      });
    }

    executorService.shutdown();


  }

}
