package thread.executor.future;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExceptionMain {

  public static void main(String[] args) {
    final ExecutorService es = Executors.newFixedThreadPool(1);
    log("작업 전달");
    final Future<Integer> future = es.submit(new ExCallable());
    sleep(1000);

    try {
      log("future.get() 호출시도, future.state(): " + future.state());
      final Integer result = future.get();
      log("result value = " + result);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } catch (ExecutionException e) {
      log("e = " + e);
      final Throwable cause = e.getCause();
      log("cause = "+ cause);
    }
    es.close();
  }

  static class ExCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
      log("Callable 실행, 예외 발생");
      throw new IllegalStateException("ex!");
    }
  }

}
