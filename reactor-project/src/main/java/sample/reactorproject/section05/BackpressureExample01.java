package sample.reactorproject.section05;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import sample.reactorproject.utils.Logger;
import sample.reactorproject.utils.TimeUtils;

/**
 * Subscriber가 처리 가능한 만큼의 request 개수를 조절하는 Backpressure 예제
 */
public class BackpressureExample01 {

  public static void main(String[] args) {
    Flux.range(1,5)
        .doOnNext(Logger::doOnNext)
        .doOnRequest(Logger::doOnRequest)
        .subscribe(new BaseSubscriber<Integer>() {
          @Override
          protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("hookOnSubscribe Start");
            request(1);
          }

          @Override
          protected void hookOnNext(Integer value) {
            System.out.println("hookOnNext Start");
            TimeUtils.sleep(2000L);
            Logger.onNext(value);
            request(1);
          }
        });

  }

}
