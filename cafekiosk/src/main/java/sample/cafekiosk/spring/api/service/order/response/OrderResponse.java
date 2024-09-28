package sample.cafekiosk.spring.api.service.order.response;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.ordderproduct.OrderProduct;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderStatus;

@Getter
public class OrderResponse {
  private Long id;
  private int totalPrice;
  private LocalDateTime registeredDateTime;
  private List<ProductResponse> products;

  @Builder
  private OrderResponse(Long id, int totalPrice, LocalDateTime registeredDateTime,
      List<ProductResponse> products) {
    this.id = id;
    this.totalPrice = totalPrice;
    this.registeredDateTime = registeredDateTime;
    this.products = products;
  }

  public static OrderResponse of(Order order) {
    return OrderResponse.builder()
        .id(order.getId())
        .totalPrice(order.getTotalPrice())
        .registeredDateTime(order.getRegisteredDateTime())
        .products(order.getOrderProducts().stream()
            .map(orderProduct -> ProductResponse.of(orderProduct.getProduct()))
            .collect(Collectors.toList()))
        .build();
  }
}
