package sample.reactorproject.section03;

import reactor.core.publisher.Flux;
import sample.reactorproject.utils.Logger;

public class FluxExample02 {

  public static void main(String[] args) {
    Flux.fromArray(new Integer[]{3,6,7,9})
        .filter(num -> num > 6)
        .map(num -> num * 2)
        .subscribe(multiple -> Logger.info("# multiply: {}", multiple));
  }

}
