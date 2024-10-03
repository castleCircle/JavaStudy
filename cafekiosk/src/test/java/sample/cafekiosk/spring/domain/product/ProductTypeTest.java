package sample.cafekiosk.spring.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProductTypeTest {

  @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
  @Test
  void containsStockType() {
    ProductType givenType = ProductType.HANDMADE;

    boolean result = ProductType.containsStockType(givenType);

    Assertions.assertThat(result).isFalse();
  }

  @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
  @Test
  void containsStockType2() {
    ProductType givenType = ProductType.BAKERY;

    boolean result = ProductType.containsStockType(givenType);

    Assertions.assertThat(result).isTrue();
  }

  @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
  @CsvSource({"HANDMADE,false", "BOTTLE,true", "BAKERY,true"})
  @ParameterizedTest
  void containsStockType3(ProductType productType, boolean expected) {

    boolean result = ProductType.containsStockType(productType);

    Assertions.assertThat(result).isEqualTo(expected);
  }

}