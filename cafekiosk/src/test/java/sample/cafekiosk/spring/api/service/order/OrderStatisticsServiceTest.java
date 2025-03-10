package sample.cafekiosk.spring.api.service.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sample.cafekiosk.spring.IntegrationTestSupport;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderProductRepository;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.order.OrderStatus;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;


class OrderStatisticsServiceTest extends IntegrationTestSupport {

  @Autowired
  private OrderStatisticsService orderStatisticsService;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderProductRepository orderProductRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private MailSendHistoryRepository mailSendHistoryRepository;

//  @MockBean
//  private MailSendClient mailSendClient;

  @AfterEach
  void tearDown() {
    orderProductRepository.deleteAllInBatch();
    orderRepository.deleteAllInBatch();
    productRepository.deleteAllInBatch();
    mailSendHistoryRepository.deleteAllInBatch();
  }

  @DisplayName("결제완료 주문들을 조회하여 매출 통계 메일을 전송한다.")
  @Test
  void sendOrderStatisticsMail() {

    LocalDateTime now = LocalDateTime.of(2023, 3, 5, 0, 0);

    final Product product1 = createProduct(HANDMADE, "001", 1000);
    final Product product2 = createProduct(HANDMADE, "002", 2000);
    final Product product3 = createProduct(HANDMADE, "003", 3000);
    final List<Product> products = List.of(product1, product2, product3);
    productRepository.saveAll(products);

    final Order order1 = createPaymentCompletedOrder(LocalDateTime.of(2023, 3, 4, 23, 59, 59),
        products);
    final Order order2 = createPaymentCompletedOrder(now, products);
    final Order order3 = createPaymentCompletedOrder(LocalDateTime.of(2023, 3, 5, 23, 59, 59),
        products);
    final Order order4 = createPaymentCompletedOrder(LocalDateTime.of(2023, 3, 6, 0, 0),
        products);

    when(mailSendClient.sendEmail(any(String.class), any(String.class), any(String.class),
        any(String.class)))
        .thenReturn(true);

    //when
    final boolean result = orderStatisticsService.sendOrderStaticsMail(LocalDate.of(2023, 3, 5),
        "test@test.com");

    assertThat(result).isTrue();

    final List<MailSendHistory> histories = mailSendHistoryRepository.findAll();
    assertThat(histories).hasSize(1)
        .extracting("content")
        .contains("총 매출 합계는 12000원입니다.");
  }

  public Order createPaymentCompletedOrder(LocalDateTime now, List<Product> products) {
    final Order order1 = Order.builder()
        .products(products)
        .orderStatus(OrderStatus.PAYMENT_COMPLETED)
        .registeredDateTime(now)
        .build();
    return orderRepository.save(order1);
  }

  private Product createProduct(
      ProductType type,
      String productNumber,
      int price
  ) {
    return Product.builder()
        .type(type)
        .productNumber(productNumber)
        .price(price)
        .sellingStatus(SELLING)
        .name("메뉴 이름")
        .build();
  }
}