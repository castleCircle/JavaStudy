package sample.cafekiosk.spring.domain.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.HOLD;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;

import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//@SpringBootTest
@ActiveProfiles("test")
@DataJpaTest
class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
  @Test
  void findAllBySellingStatusIn(){

    final Product product1 = Product.builder()
        .productNumber("001")
        .type(ProductType.HANDMADE)
        .sellingStatus(ProductSellingStatus.SELLING)
        .name("아메리카노")
        .price(4000)
        .build();

    final Product product2 = Product.builder()
        .productNumber("002")
        .type(ProductType.HANDMADE)
        .sellingStatus(HOLD)
        .name("카페라떼")
        .price(4500)
        .build();

    final Product product3 = Product.builder()
        .productNumber("003")
        .type(ProductType.HANDMADE)
        .sellingStatus(ProductSellingStatus.STOP_SELLING)
        .name("팥빙수")
        .price(7000)
        .build();

    productRepository.saveAll(List.of(product1,product2,product3));

    //
    final List<Product> products = productRepository.findAllBySellingStatusIn(
        List.of(ProductSellingStatus.SELLING, HOLD));

    assertThat(products).hasSize(2)
        .extracting("productNumber","name","sellingStatus")
        .containsExactlyInAnyOrder(
            Tuple.tuple("001","아메리카노",SELLING),
            Tuple.tuple("002","카페라떼",HOLD)
        );
  }



}