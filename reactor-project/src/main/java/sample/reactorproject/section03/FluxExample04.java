package sample.reactorproject.section03;

import reactor.core.publisher.Flux;
import sample.reactorproject.utils.Logger;

public class FluxExample04 {

  public static void main(String[] args) {
    Flux.concat(
        Flux.just("venus"),
        Flux.just("earth")
    )
        .collectList()
        .subscribe(planetList -> Logger.info("# Solar System: {} " , planetList));
  }

}
