package sample.cafekiosk.unit.beverage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AmericanoTest {

  @Test
  void getName(){
    final var americano = new Americano();
    Assertions.assertEquals(americano.getName(),"아메리카노");
  }

  @Test
  void getPrice(){
    Americano americano = new Americano();
    Assertions.assertEquals(americano.getPrice(),4000);
  }

}