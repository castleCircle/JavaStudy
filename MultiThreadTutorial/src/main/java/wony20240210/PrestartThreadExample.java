package wony20240210;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PrestartThreadExample {

  public static void main(String[] args) {

    int corePoolSize = 2;
    int maxPoolSize = 4;
    long keepAliveTime = 0L;
    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(); // 무제한 큐가 늘어난다. 그래서 maxPoolsize가 의미가 없다.
//
    int taskNum = 9;

    ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,
        TimeUnit.SECONDS,workQueue);

    executor.prestartCoreThread();

    for(int i=0;i<taskNum;i++){
      final int taskId = i;
      executor.execute(()->{
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 가 테스크" + taskId + " 를 실행하고 있습니다.");
      });
    }

    executor.shutdown();
  }

}
