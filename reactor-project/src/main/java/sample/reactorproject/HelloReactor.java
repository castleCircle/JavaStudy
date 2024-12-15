package sample.reactorproject;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class HelloReactor {

  public static void main(String[] args) {
    Mono.just("Hello Reactor")
        .subscribe(message -> System.out.println(message));

    //just,map 같은 연산자들을 오퍼레이터라고 부름
    Flux<String> sequence = Flux.just("Hello", "World");
    sequence
        .map(data-> data.toLowerCase())
        .subscribe(data -> log.info(data));

    //Flux에서 subsribe하는데 구독도 Flux가 하는것이냐?

  }

}
