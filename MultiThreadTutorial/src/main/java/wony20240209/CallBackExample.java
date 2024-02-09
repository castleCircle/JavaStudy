package wony20240209;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallBackExample {

  interface CallBack{
    void onComplete(int result);
  }

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(1);

    executorService.execute(()->{
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      int result = 42;

      CallBack callBack = new MyCallBack();
      callBack.onComplete(result);
    });

    System.out.println("비동기 작업 시작");
  }

  static class MyCallBack implements CallBack{

    @Override
    public void onComplete(int result) {
      System.out.println("비동기 작업 결과: " + result );
    }
  }

}
