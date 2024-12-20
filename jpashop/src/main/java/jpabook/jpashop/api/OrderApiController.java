package jpabook.jpashop.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.query.OrderFlatDto;
import jpabook.jpashop.repository.order.query.OrderQueryDto;
import jpabook.jpashop.repository.order.query.OrderQueryRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

  private final OrderRepository orderRepository;
  private final OrderQueryRepository orderQueryRepository;

  @GetMapping("/api/v1/orders")
  public List<Order> ordersV1(){
    final List<Order> all = orderRepository.findAllByString(new OrderSearch());
    for (Order order : all) {
      order.getMember().getName();
      order.getDelivery().getAddress();
      final List<OrderItem> orderItems = order.getOrderItems();
      orderItems.stream().forEach(o->o.getItem().getName());
    }
    return all;
  }

  @GetMapping("/api/v2/orders")
  public List<OrderDto> ordersV2(){
    final List<Order> orders = orderRepository.findAllByString(new OrderSearch());
    final List<OrderDto> collect = orders.stream()
        .map(o -> new OrderDto(o))
        .collect(Collectors.toList());
    return collect;
  }

  @GetMapping("/api/v3/orders")
  public List<OrderDto> ordersV3(){
    List<Order> orders = orderRepository.findAllWithItem();
    final List<OrderDto> collect = orders.stream()
        .map(o -> new OrderDto(o))
        .collect(Collectors.toList());
    return collect;
  }

  @GetMapping("/api/v3.1/orders")
  public List<OrderDto> ordersV3_page(
      @RequestParam(value="offset",defaultValue = "0") int offset,
      @RequestParam(value="limit",defaultValue = "100") int limit
      ){
    List<Order> orders = orderRepository.findAllWithMemberDelivery(offset,limit);

    System.out.println("=======================");

    final List<OrderDto> collect = orders.stream()
        .map(o -> new OrderDto(o))
        .collect(Collectors.toList());

    return collect;
  }

  @GetMapping("/api/v4/orders")
  public List<OrderQueryDto> orderV4(){
    return orderQueryRepository.findOrderQueryDtos();
  }

  @GetMapping("/api/v5/orders")
  public List<OrderQueryDto> orderV5(){
    return orderQueryRepository.findAllByDto_optimization();
  }

  @GetMapping("/api/v6/orders")
  public List<OrderFlatDto> orderV6(){
    return orderQueryRepository.findAllByDto_flat();
  }

  @GetMapping("/api/v7/orders")
  public List<OrderQueryDto> orderV7(){
   return orderQueryRepository.test();
  }

  @Getter
  static class OrderDto{

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order){
      orderId = order.getId();
      name = order.getMember().getName();
      orderDate = order.getOrderDate();
      orderStatus = order.getStatus();
      address = order.getDelivery().getAddress();
      orderItems = order.getOrderItems().stream()
          .map(orderItem -> new OrderItemDto(orderItem))
          .collect(Collectors.toList());
      System.out.println("============OrderDto===========");
    }

  }

  @Getter
  static class OrderItemDto{

    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemDto(OrderItem orderItem){
      itemName = orderItem.getItem().getName();
      orderPrice = orderItem.getOrderPrice();
      count = orderItem.getCount();
    }

  }

}
