package thread.executor.future;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancelMain {

//  private static boolean mayInterruptIfRunning = true;
  private static boolean mayInterruptIfRunning = false;

  public static void main(String[] args) {
    final ExecutorService es = Executors.newFixedThreadPool(1);
    final Future<String> future = es.submit(new MyTask());
    log("Future.state: " + future.state());

    sleep(3000);


    //cancel() 호출
    log("future.cancel(" + mayInterruptIfRunning + ") 호출");
    final boolean cancelResult = future.cancel(mayInterruptIfRunning);
    log("cancel(" +  mayInterruptIfRunning + ") result: " + cancelResult);

    //결과 확인
    try {
      log("Future result: " + future.get());
    }catch (CancellationException e){
      log("Future는 이미 취소 되었습니다.");
    }catch (InterruptedException | ExecutionException e){
      e.printStackTrace();
    }

    es.close();
  }

  static class MyTask implements Callable<String>{

    @Override
    public String call() throws Exception {
      try {
        for (int i = 0; i < 10; i++) {
          log("작업 중: " + i);
          Thread.sleep(1000);
        }
      }catch (InterruptedException e){
        log("인터럽트 발생");
        return "Interrupted";
      }

      return "Completed";
    }
  }

}