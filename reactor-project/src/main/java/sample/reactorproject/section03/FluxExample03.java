package sample.reactorproject.section03;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.reactorproject.utils.Logger;

/**
 * 2개의 Mono를 연결해서 Flux로 변환하는 예제
 */
public class FluxExample03 {

  public static void main(String[] args) {
    Flux<Object> flux = Mono.justOrEmpty(null).concatWith(Mono.justOrEmpty("Jobs"));
    flux.subscribe(data -> Logger.info("# result; {}", data));
  }

}
