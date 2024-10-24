package sample.cafekiosk.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

class CafeKioskTest {

  @Test
  void add_manual_test(){
    CafeKiosk cafeKiosk = new CafeKiosk();
    cafeKiosk.add(new Americano());

    System.out.println(">>> 담긴 음료수 : " + cafeKiosk.getBeverages().size());
    System.out.println(">>> 담긴 음료 : " + cafeKiosk.getBeverages().get(0).getName());
  }

  @Test
  void add(){
    CafeKiosk cafeKiosk = new CafeKiosk();
    cafeKiosk.add(new Americano());

    assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
  }

  @Test
  void addBeverages(){
    CafeKiosk cafeKiosk = new CafeKiosk();
    final Americano americano = new Americano();
    cafeKiosk.add(americano,2);

    assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
    assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
  }

  @Test
  void addZeroBeverages(){
    CafeKiosk cafeKiosk = new CafeKiosk();
    final Americano americano = new Americano();

    assertThatThrownBy(()-> cafeKiosk.add(americano,0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
  }

  @Test
  void remove(){
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();

    cafeKiosk.add(americano);
    assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);

    cafeKiosk.remove(americano);
    assertThat(cafeKiosk.getBeverages()).isEmpty();
  }


//  @Test
//  void createOrder(){
//    CafeKiosk cafeKiosk = new CafeKiosk();
//    Americano americano = new Americano();
//
//    cafeKiosk.add(americano);
//
//    final Order order = cafeKiosk.createOrder();
//
//    assertThat(order.getBeverages()).hasSize(1);
//  }


  @Test
  void calculateTotalPrice(){
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();

    Latte latte = new Latte();

    cafeKiosk.add(americano);
    cafeKiosk.add(latte);

  }

  @Test
  void createOrderWithCurrentTime(){
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();

    cafeKiosk.add(americano);

    final Order order = cafeKiosk.createOrder(LocalDateTime.of(2023,1,17,10,0));

    assertThat(order.getBeverages()).hasSize(1);
  }

  @Test
  void createOrderOutSideOpenTime() {
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();

    cafeKiosk.add(americano);


    assertThatThrownBy(()->cafeKiosk.createOrder(LocalDateTime.of(2023,1,17,9,0)))
        .isInstanceOf(IllegalArgumentException.class);
  }

}