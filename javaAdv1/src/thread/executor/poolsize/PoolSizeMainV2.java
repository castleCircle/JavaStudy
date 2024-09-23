package thread.executor.poolsize;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import thread.executor.RunnableTask;

public class PoolSizeMainV2 {

  public static void main(String[] args) {
    final ExecutorService es = Executors.newFixedThreadPool(2);
//   ExecutorService es == new ThreadPoolExecutor(2,nThreads,0L,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>());

    log("pool 생성");
    printState(es);

    for(int i=1;i<=6;i++){
      String taskName = "task" + i;
      es.execute(new RunnableTask(taskName));
      printState(es,taskName);
    }
    es.close();
    log("== shutdown 완료 ==");
  }

}
