package wony20240210;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutoerExample {

  public static void main(String[] args) {

    int corePoolSize = 2;
    int maxPoolSize = 4;
    long keepAliveTime = 0L; // 이 시간 동아 스레드가 남아서 쉬면 corePoolSize 사이즈를 제외하곤 버림
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(4);
//    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(); // 무제한 큐가 늘어난다.
//    그래서 maxPoolsize가 의미가 없다. thread queue가 가득찼을때 maxPoolSize 이내로 쓰레드가 만들어진다.

    int taskNum = 8;

    ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,
        TimeUnit.SECONDS,workQueue);

    for(int i=0;i<taskNum;i++){
      final int taskId = i;
      executor.execute(()->{
        System.out.println(Thread.currentThread().getName() + " 가 테스크" + taskId + " 를 실행하고 있습니다.");
      });
    }

    executor.shutdown();
  }

}
