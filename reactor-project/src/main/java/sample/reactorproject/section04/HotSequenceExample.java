package sample.reactorproject.section04;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;
import reactor.core.publisher.Flux;
import sample.reactorproject.utils.Logger;
import sample.reactorproject.utils.TimeUtils;

public class HotSequenceExample {

  public static void main(String[] args) {
    Flux<String> concertFlux = Flux.fromStream(Stream.of("SingerA", "SingerB", "SingerC", "SingerD","SingerE"))
            .delayElements(Duration.ofSeconds(1)).share(); // share() 원본 Flux를 여러 Subscriber가 공유한다.

    concertFlux.subscribe(singer -> Logger.info("# Subscirbe1 is watching {}'s song",singer));

    TimeUtils.sleep(2500);

    concertFlux.subscribe(singer -> Logger.info("# Subscirbe2 is watching {}'s song",singer));

    //share를 사용하면 콜드 스퀀스가 핫 스퀀스로 변환되면서 메인 스레드가 아닌 다른스레드가 생기기 때문에 메인스레드 종료 안시키기 위해 3초 넣는다.
    TimeUtils.sleep(3000);
  }

}
