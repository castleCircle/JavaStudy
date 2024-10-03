package sample.cafekiosk.spring.api.controller.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import sample.cafekiosk.spring.ControllerTestSupport;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;


class ProductControllerTest extends ControllerTestSupport {


  @DisplayName("신규 상품을 등록한다.")
  @Test
  void createProduct() throws Exception {
    final ProductCreateRequest request = ProductCreateRequest.builder()
        .type(HANDMADE)
        .sellingStatus(SELLING)
        .name("아메리카노")
        .price(4000)
        .build();

    mockMvc.perform(post("/api/v1/products/new")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isOk());
  }

  @DisplayName("신규 상품을 등록할 떄 상품타입은 필수값이다.")
  @Test
  void createProductWithoutType() throws Exception {
    final ProductCreateRequest request = ProductCreateRequest.builder()
        .sellingStatus(SELLING)
        .name("아메리카노")
        .price(4000)
        .build();

    mockMvc.perform(post("/api/v1/products/new")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value("400"))
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("상품 타입은 필수입니다."))
        .andExpect(jsonPath("$.data").isEmpty())
    ;
  }

  @DisplayName("신규 상품을 등록할 떄 상품 판매상태는 필수값이다.")
  @Test
  void createProductWithoutSellingStatus() throws Exception {
    final ProductCreateRequest request = ProductCreateRequest.builder()
        .type(HANDMADE)
        .name("아메리카노")
        .price(4000)
        .build();

    mockMvc.perform(post("/api/v1/products/new")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value("400"))
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("상품 판매상태는 필수입니다."))
        .andExpect(jsonPath("$.data").isEmpty())
    ;
  }

  @DisplayName("신규 상품을 등록할 떄 상품이름은 필수값이다.")
  @Test
  void createProductWithoutName() throws Exception {
    final ProductCreateRequest request = ProductCreateRequest.builder()
        .type(HANDMADE)
        .sellingStatus(SELLING)
        .price(4000)
        .build();

    mockMvc.perform(post("/api/v1/products/new")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value("400"))
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("상품 이름은 필수입니다."))
        .andExpect(jsonPath("$.data").isEmpty())
    ;
  }

  @DisplayName("신규 상품을 등록할 떄 상품가격은 양수")
  @Test
  void createProductWithoutPrice() throws Exception {
    final ProductCreateRequest request = ProductCreateRequest.builder()
        .type(HANDMADE)
        .sellingStatus(SELLING)
        .name("테스트")
        .price(0)
        .build();

    mockMvc.perform(post("/api/v1/products/new")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value("400"))
        .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
        .andExpect(jsonPath("$.message").value("상품 가격은 양수여야 합니다."))
        .andExpect(jsonPath("$.data").isEmpty())
    ;
  }

  @DisplayName("판매 상품을 조회한다.")
  @Test
  void getSellingProducts() throws Exception {

    List<ProductResponse> result = List.of();
    Mockito.when(productService.getSellingProducts()).thenReturn(result);

    mockMvc.perform(get("/api/v1/products/selling")
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value("200"))
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("OK"))
        .andExpect(jsonPath("$.data").isArray())
    ;
  }

}