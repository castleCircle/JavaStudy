package thread.executor;

import static util.MyLogger.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class ExecutorUtils {

  public static void printState(ExecutorService executorService){

    if(executorService instanceof ThreadPoolExecutor poolExecutor){
      int pool = poolExecutor.getPoolSize();
      int active = poolExecutor.getActiveCount();
      final int queuedTasks = poolExecutor.getQueue().size();
      final long completedTask = poolExecutor.getCompletedTaskCount();
      log("[pool=" + pool + ", active=" + active + ", queuedTasks=" + queuedTasks + ", completedTask= " + completedTask + "]");
    }else {
      log(executorService);
    }
  }

  public static void printState(ExecutorService executorService,String taskName){
    if(executorService instanceof ThreadPoolExecutor poolExecutor){
      int pool = poolExecutor.getPoolSize();
      int active = poolExecutor.getActiveCount();
      final int queuedTasks = poolExecutor.getQueue().size();
      final long completedTask = poolExecutor.getCompletedTaskCount();
      log(taskName + "[pool=" + pool + ", active=" + active + ", queuedTasks=" + queuedTasks + ", completedTask= " + completedTask + "]");
    }else {
      log(executorService);
    }
  }

}
