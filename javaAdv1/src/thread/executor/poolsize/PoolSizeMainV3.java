package thread.executor.poolsize;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import thread.executor.RunnableTask;

public class PoolSizeMainV3 {

  public static void main(String[] args) {
//    final ExecutorService es = Executors.newCachedThreadPool();
    ExecutorService es = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
        3, TimeUnit.SECONDS,
        new SynchronousQueue<Runnable>());

    log("pool 생성");
    printState(es);

    for(int i=1;i<=4;i++){
      String taskName = "task" + i;
      es.execute(new RunnableTask(taskName));
      printState(es,taskName);
    }

    sleep(3000);
    log("== 작업 수행완료 ==");
    printState(es);

    sleep(3000);
    log("== maximumPoolSize 대기 시간 초과 ==");
    printState(es);

    es.close();
    log("== shutdown 완료 ==");
  }

}
