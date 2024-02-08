package wony20240208;

import java.util.concurrent.Executor;
import wony20240208.SyncExecutorExample.SyncExecutor;

public class AsyncExecutorExample {

  public static void main(String[] args) {
    Executor aSyncExecutor = new AsyncExecutorExample.AsyncExecutor();

    aSyncExecutor.execute(()->{
      System.out.println("비동기 작업1 수행 중...");

      System.out.println("비동기 작업 1 완료");
    });

    aSyncExecutor.execute(()->{
      System.out.println("비동기 작업2 수행 중...");

      System.out.println("비동기 작업 2 완료");
    });

  }

  static class AsyncExecutor implements Executor{

    @Override
    public void execute(Runnable command) {
      Thread thread = new Thread(command);
      thread.start();
    }
  }

}
