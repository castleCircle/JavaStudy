package wony20240210;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutDownExample {

  public static void main(String[] args) {
    final ExecutorService executorService = Executors.newFixedThreadPool(2);

    for(int i=0;i<5;i++){
      executorService.submit(()->{
        try{
          Thread.sleep(1000);
          System.out.println(Thread.currentThread().getName() + ": 작업 종료");
        }catch (InterruptedException e){
          System.out.println("인터럽트 발생");
//          Thread.currentThread().interrupt();
          throw new RuntimeException(e);
        }
        return 42;
      });
    }

    executorService.shutdown();

    try{
      if(!executorService.awaitTermination(2, TimeUnit.SECONDS)){
        executorService.shutdownNow();
        System.out.println("스레드 풀 강력 종료");
      }
    }catch (InterruptedException e){

    }

    if(executorService.isShutdown()){
      System.out.println("스레드 풀 종료 여부: " + executorService.isShutdown());
    }

    while(!executorService.isTerminated()){
      System.out.println("다 끝날떄 까지 기다리자..");
    }
  }

}
