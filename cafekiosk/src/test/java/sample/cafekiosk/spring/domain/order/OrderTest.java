package sample.cafekiosk.spring.domain.order;

import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductType;

class OrderTest {

  @DisplayName("상품 리스트에서 주문의 총 금액을 계산한다.")
  @Test
  void calculateTotalPrice(){
    //given
    final List<Product> product = List.of(
        createProduct("001", 1000),
        createProduct("002", 2000)
    );

    //when
    final Order order = Order.create(product, LocalDateTime.now());

    //then
    Assertions.assertThat(order.getTotalPrice()).isEqualTo(3000);
  }

  @DisplayName("주문 생성 시 주문 상태는 INIT이다.")
  @Test
  void init(){
    //given
    final List<Product> product = List.of(
        createProduct("001", 1000),
        createProduct("002", 2000)
    );

    //when
    final Order order = Order.create(product,LocalDateTime.now());

    //then
    Assertions.assertThat(order.getOrderStatus()).isEqualByComparingTo(OrderStatus.INIT);
  }

  @DisplayName("주문 생성 시 주문 등록 시간을 기록한다.")
  @Test
  void reigsteredDateTime(){
    //given

    final List<Product> product = List.of(
        createProduct("001", 1000),
        createProduct("002", 2000)
    );

    final LocalDateTime registeredDateTime = LocalDateTime.now();

    //when
    final Order order = Order.create(product,registeredDateTime);

    //then
    Assertions.assertThat(order.getRegisteredDateTime()).isEqualTo(registeredDateTime);
  }

  private Product createProduct(
      String productNumber,
      int price
  ){
    return Product.builder()
        .type(HANDMADE)
        .productNumber(productNumber)
        .price(price)
        .sellingStatus(SELLING)
        .name("메뉴 이름")
        .build();
  }
}