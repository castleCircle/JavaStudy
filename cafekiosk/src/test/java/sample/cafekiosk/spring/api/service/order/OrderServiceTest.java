package sample.cafekiosk.spring.api.service.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;

import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sample.cafekiosk.spring.IntegrationTestSupport;
import sample.cafekiosk.spring.api.service.order.request.OrderCreateServiceRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.order.OrderProductRepository;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;
import sample.cafekiosk.spring.domain.stock.Stock;
import sample.cafekiosk.spring.domain.stock.StockRepository;


class OrderServiceTest extends IntegrationTestSupport {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderProductRepository orderProductRepository;

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private OrderService orderService;

  //RepositoryTest는 db를 삭제 안해줘도 다른 테스트에 영향을 안 미친는데 Service는 왜 다른테스트에 영향을 미칠까?
  //@DataJpaTest에는 @Transactional이 붙어있기 때문에 롤백이 된다.
  //그러면 @springBootTest에 @Transacional까지 붙여주면 롤백이 되는거 아니야? 맞다 롤백이 된다. 그런데 이렇게 쓸때 주의할점이 있다고 한다. Next...
  @AfterEach
  void tearDown() {
    orderProductRepository.deleteAllInBatch();
    productRepository.deleteAllInBatch();
    orderRepository.deleteAllInBatch();

    //deleteAll은 데이터를 건건이 지우고 또한 매핑 관의 관계있는 애들을 찾아서 지운다. 그래서 느리다.
    //그래서 deleteAllInBatch()가 더 효율적이다.
    //트랜잭션이 여러개 겹칠채는 deleteAllInBatch을 통해 직접 지우는것이 편하고
    //트랜잭션이 하나일때는 @Transacitonal을 붙이는게 편함

//    orderProductRepository.deleteAll();
//    productRepository.deleteAll();
//    orderRepository.deleteAll();

    stockRepository.deleteAllInBatch();
  }

  @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
  @Test
  void createOrder() {

    final Product product1 = createProduct(ProductType.HANDMADE, "001", 1000);
    final Product product2 = createProduct(ProductType.HANDMADE, "002", 3000);
    final Product product3 = createProduct(ProductType.HANDMADE, "003", 5000);
    productRepository.saveAll(List.of(product1, product2, product3));

    final OrderCreateServiceRequest request = OrderCreateServiceRequest.builder()
        .productNumbers(List.of("001", "002"))
        .build();

    final LocalDateTime registeredDateTime = LocalDateTime.now();

    //when
    OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);

    //then
    assertThat(orderResponse.getId()).isNotNull();
    assertThat(orderResponse)
        .extracting("registeredDateTime", "totalPrice")
        .contains(registeredDateTime, 4000);

    assertThat(orderResponse.getProducts()).hasSize(2)
        .extracting("productNumber", "price")
        .containsExactlyInAnyOrder(
            Tuple.tuple("001", 1000),
            Tuple.tuple("002", 3000)
        );

  }

  @DisplayName("중복되는 상품번호 리스트로 주문을 생성할 . 있다.")
  @Test
  void createOrderWithDuplicateProductNumbers() {

    final LocalDateTime registeredDateTime = LocalDateTime.now();

    final Product product1 = createProduct(ProductType.HANDMADE, "001", 1000);
    final Product product2 = createProduct(ProductType.HANDMADE, "002", 3000);
    final Product product3 = createProduct(ProductType.HANDMADE, "003", 5000);
    productRepository.saveAll(List.of(product1, product2, product3));

    final OrderCreateServiceRequest request = OrderCreateServiceRequest.builder()
        .productNumbers(List.of("001", "001"))
        .build();

    //when
    OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);

    //then
    assertThat(orderResponse.getId()).isNotNull();
    assertThat(orderResponse)
        .extracting("registeredDateTime", "totalPrice")
        .contains(registeredDateTime, 2000);

    assertThat(orderResponse.getProducts()).hasSize(2)
        .extracting("productNumber", "price")
        .containsExactlyInAnyOrder(
            Tuple.tuple("001", 1000),
            Tuple.tuple("001", 1000)
        );

  }

  @DisplayName("재고와 관련된 상품이 포함되어 있는 주문번호 리스트를 받아 주문을 생성한다.")
  @Test
  void createOrderWithStock() {
    final LocalDateTime registeredDateTime = LocalDateTime.now();
    final Product product1 = createProduct(ProductType.BOTTLE, "001", 1000);
    final Product product2 = createProduct(ProductType.BAKERY, "002", 3000);
    final Product product3 = createProduct(ProductType.HANDMADE, "003", 5000);
    productRepository.saveAll(List.of(product1, product2, product3));

    Stock stock1 = Stock.create("001", 2);
    Stock stock2 = Stock.create("002", 2);
    stockRepository.saveAll(List.of(stock1, stock2));

    final OrderCreateServiceRequest request = OrderCreateServiceRequest.builder()
        .productNumbers(List.of("001", "001", "002", "003"))
        .build();

    //when
    OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);

    //then
    assertThat(orderResponse.getId()).isNotNull();
    assertThat(orderResponse)
        .extracting("registeredDateTime", "totalPrice")
        .contains(registeredDateTime, 10000);

    assertThat(orderResponse.getProducts()).hasSize(4)
        .extracting("productNumber", "price")
        .containsExactlyInAnyOrder(
            Tuple.tuple("001", 1000),
            Tuple.tuple("001", 1000),
            Tuple.tuple("002", 3000),
            Tuple.tuple("003", 5000)
        );

    List<Stock> stocks = stockRepository.findAll();
    assertThat(stocks).hasSize(2)
        .extracting("productNumber", "quantity")
        .containsExactlyInAnyOrder(
            Tuple.tuple("001", 0),
            Tuple.tuple("002", 1)
        );

  }

  @DisplayName("재고가 부족한 상품으로 주문을 생성하려는 경우 예외가 발생한다.")
  @Test
  void createOrderWithNoStock() {
    final LocalDateTime registeredDateTime = LocalDateTime.now();
    final Product product1 = createProduct(ProductType.BOTTLE, "001", 1000);
    final Product product2 = createProduct(ProductType.BAKERY, "002", 3000);
    final Product product3 = createProduct(ProductType.HANDMADE, "003", 5000);
    productRepository.saveAll(List.of(product1, product2, product3));

    Stock stock1 = Stock.create("001", 2);
    Stock stock2 = Stock.create("002", 2);
    stock1.deductQuantity(1); //todo
    stockRepository.saveAll(List.of(stock1, stock2));

    final OrderCreateServiceRequest request = OrderCreateServiceRequest.builder()
        .productNumbers(List.of("001", "001", "002", "003"))
        .build();

    //when
    assertThatThrownBy(() -> orderService.createOrder(request, registeredDateTime))
        .isInstanceOf(IllegalArgumentException.class);

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