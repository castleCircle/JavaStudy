package wony20240209;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCallbackExmaple {

  interface CallBack{
    void onComplete(int result);
  }


  public static void main(String[] args) {
    ExecutorService executors = Executors.newFixedThreadPool(1);

    Callable<Integer> callable = () ->{
      Thread.sleep(1000);
      return 43;
    };

    final Future<Integer> future = executors.submit(callable);

    System.out.println("비동기 작업 시작");

    registerCallBack(future,result -> {
      System.out.println("비동기 작업 결과:" + result);
    });

    executors.shutdown();

  }

  private static void registerCallBack(Future<Integer> future,CallBack callBack){
    new Thread(()->{
      int result = 0;
        try {
          result = future.get();
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (ExecutionException e) {
          e.printStackTrace();
        }
        callBack.onComplete(result);
    }).start();
  }
}
