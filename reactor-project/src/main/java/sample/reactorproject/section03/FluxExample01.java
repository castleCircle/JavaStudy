package sample.reactorproject.section03;

import reactor.core.publisher.Flux;
import sample.reactorproject.utils.Logger;

public class FluxExample01 {

  public static void main(String[] args) {
    //datasource : 6,9,13
    Flux.just(6,9,13)
        .map(num -> num %2)
        .subscribe(remainder -> Logger.info("# remainder: {}", remainder));
  }

}
