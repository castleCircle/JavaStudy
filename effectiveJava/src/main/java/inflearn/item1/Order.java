package inflearn.item1;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Order {

  private boolean prime;

  private boolean urgent;

  private Product product;

  public static Order primeOrder(Product product){
    Order order = new Order();
    order.prime = true;
    order.product = product;
    return order;
  }

  public static Order urgentOrder(Product product){
    Order order = new Order();
    order.urgent = true;
    order.product = product;
    return order;
  }


  public static void main(String[] args) {
    Arrays.stream(OrderStatus.values()).forEach(System.out::println);

    EnumMap<OrderStatus,String> a = (EnumMap<OrderStatus, String>) Map.of(OrderStatus.SHIPPING,"1",OrderStatus.COMPLETE,"2");

    System.out.println(a.get(OrderStatus.SHIPPING));
  }

}
