package thread.study;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import thread.executor.RunnableTask;

public class ExecutorBasicMain {

  private static boolean mayInterruptIfRunning = false;

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executor = new ThreadPoolExecutor(1, 1, 0,
        TimeUnit.SECONDS, new SynchronousQueue<>());

    executor.submit(new RunnableTask("task1"));
    executor.submit(new RunnableTask("task2"));
    executor.submit(new RunnableTask("task3"));

    executor.close();
  }


  static class MyTask implements Callable<String> {

    @Override
    public String call() throws Exception {
      try{
        for(int i=0;i<10;i++){
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
