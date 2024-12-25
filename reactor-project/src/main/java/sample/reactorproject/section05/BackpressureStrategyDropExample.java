package sample.reactorproject.section05;

import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import sample.reactorproject.utils.Logger;
import sample.reactorproject.utils.TimeUtils;

/**
 * Unbounded request 일 경우, Downstream 에 Backpressure Drop 전략을 적용하는 예제
 * - DownStream으로 전달 할 데이터가 버퍼에 가득 찰 경우, 버퍼 밖에서 대기하는 먼저 emit된 데이터를 drop 시키는 전략
 */
public class BackpressureStrategyDropExample {

  public static void main(String[] args) {
    Flux
        .interval(Duration.ofMillis(1L))
        .onBackpressureDrop(dropped -> Logger.info("# dropped:{}" , dropped))
        .publishOn(Schedulers.parallel())
        .subscribe(
            data -> {
              TimeUtils.sleep(5L);
              Logger.onNext(data);
            },
            error -> Logger.onError(error));

    System.out.println("===========================end===========================");
    TimeUtils.sleep(2000L);
  }

}
