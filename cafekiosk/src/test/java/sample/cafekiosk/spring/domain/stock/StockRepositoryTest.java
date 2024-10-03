package sample.cafekiosk.spring.domain.stock;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sample.cafekiosk.spring.IntegrationTestSupport;
import sample.cafekiosk.spring.domain.product.ProductRepository;


class StockRepositoryTest extends IntegrationTestSupport {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private StockRepository stockRepository;


  @DisplayName("상품번호 리스트로 재고을 조회한다.")
  @Test
  void findAllByProductNumberIn() {

    final Stock stock1 = Stock.create("001", 1);
    final Stock stock2 = Stock.create("002", 2);
    final Stock stock3 = Stock.create("003", 3);

    stockRepository.saveAll(List.of(stock1, stock2, stock3));

    //when
    final List<Stock> stocks = stockRepository.findAllByProductNumberIn(
        List.of("001", "002"));

    //then
    assertThat(stocks).hasSize(2)
        .extracting("productNumber", "quantity")
        .containsExactlyInAnyOrder(
            Tuple.tuple("001", 1),
            Tuple.tuple("002", 2)
        );
  }

}