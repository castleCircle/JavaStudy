package sample.cafekiosk.unit;

import java.time.LocalDateTime;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

public class CafeKioskRunner {

  public static void main(String[] args) {
    CafeKiosk kiosk = new CafeKiosk();
    kiosk.add(new Americano());
    System.out.println(">>>> 아메리카노 추가");

    kiosk.add(new Latte());
    System.out.println(">>>> 라떼 추가");

    int totalPrice = kiosk.calculateTotalPrice();
    System.out.println("총 주문가격: " + totalPrice);

    Order order = kiosk.createOrder(LocalDateTime.now());
  }

}
