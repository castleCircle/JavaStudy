package sample.cafekiosk.spring.domain.product;

import static org.assertj.core.api.Assertions.assertThat;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.HOLD;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.STOP_SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.IntegrationTestSupport;

@Transactional
class ProductRepositoryTest extends IntegrationTestSupport {

  @Autowired
  private ProductRepository productRepository;

  @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
  @Test
  void findAllBySellingStatusIn() {

    final Product product1 = Product.builder()
        .productNumber("001")
        .type(HANDMADE)
        .sellingStatus(SELLING)
        .name("아메리카노")
        .price(4000)
        .build();

    final Product product2 = Product.builder()
        .productNumber("002")
        .type(HANDMADE)
        .sellingStatus(HOLD)
        .name("카페라떼")
        .price(4500)
        .build();

    final Product product3 = Product.builder()
        .productNumber("003")
        .type(HANDMADE)
        .sellingStatus(ProductSellingStatus.STOP_SELLING)
        .name("팥빙수")
        .price(7000)
        .build();

    productRepository.saveAll(List.of(product1, product2, product3));

    //
    final List<Product> products = productRepository.findAllBySellingStatusIn(
        List.of(SELLING, HOLD));

    assertThat(products).hasSize(2)
        .extracting("productNumber", "name", "sellingStatus")
        .containsExactlyInAnyOrder(
            Tuple.tuple("001", "아메리카노", SELLING),
            Tuple.tuple("002", "카페라떼", HOLD)
        );
  }


  @DisplayName("상품번호 리스트로 상품들을 조회한다.")
  @Test
  void findAllByProductNumberIn() {

    final Product product1 = Product.builder()
        .productNumber("001")
        .type(HANDMADE)
        .sellingStatus(SELLING)
        .name("아메리카노")
        .price(4000)
        .build();

    final Product product2 = Product.builder()
        .productNumber("002")
        .type(HANDMADE)
        .sellingStatus(HOLD)
        .name("카페라떼")
        .price(4500)
        .build();

    final Product product3 = Product.builder()
        .productNumber("003")
        .type(HANDMADE)
        .sellingStatus(ProductSellingStatus.STOP_SELLING)
        .name("팥빙수")
        .price(7000)
        .build();

    productRepository.saveAll(List.of(product1, product2, product3));

    //
    final List<Product> products = productRepository.findAllByProductNumberIn(
        List.of("001", "002"));

    assertThat(products).hasSize(2)
        .extracting("productNumber", "name", "sellingStatus")
        .containsExactlyInAnyOrder(
            Tuple.tuple("001", "아메리카노", SELLING),
            Tuple.tuple("002", "카페라떼", HOLD)
        );
  }

  @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어온다.")
  @Test
  void findLatestProductNumber() {

    String targetProductNumber = "003";
    final Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
    final Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
    final Product product3 = createProduct(targetProductNumber, HANDMADE, STOP_SELLING, "팥빙수",
        7000);

    productRepository.saveAll(List.of(product1, product2, product3));

    //when
    final String latestProductNumber = productRepository.findLatestProductNumber();

    //then
    assertThat(latestProductNumber).isEqualTo(targetProductNumber);
  }

  @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어올 때, 상품이 하나도 없는 경우에는 null을 반환한다.")
  @Test
  void findLatestProductNumberWhenProductIsEmpty() {

    //when
    final String latestProductNumber = productRepository.findLatestProductNumber();

    //then
    assertThat(latestProductNumber).isNull();
  }

  private Product createProduct(
      String productNumber,
      ProductType productType,
      ProductSellingStatus productSellingStatus,
      String productName,
      int price
  ) {
    return Product.builder()
        .productNumber(productNumber)
        .type(productType)
        .sellingStatus(productSellingStatus)
        .name(productName)
        .price(price)
        .build();
  }


}