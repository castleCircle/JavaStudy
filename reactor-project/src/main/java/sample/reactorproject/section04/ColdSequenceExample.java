package sample.reactorproject.section04;

import java.util.Arrays;
import reactor.core.publisher.Flux;
import sample.reactorproject.utils.Logger;

public class ColdSequenceExample {

  public static void main(String[] args) {
    Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("RED","YELLOW","GREEN","BLUE"))
        .map(String::toLowerCase);

    coldFlux.subscribe(country -> Logger.info("# Subscriber1: {}" , country));
    Logger.info("--------------");
    coldFlux.subscribe(country -> Logger.info("# Subscriber2: {}" , country));
  }

}
