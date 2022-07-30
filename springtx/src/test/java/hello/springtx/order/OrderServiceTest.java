package hello.springtx.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class OrderServiceTest {

  @Autowired
  OrderService orderService;

  @Autowired
  OrderRepository orderRepository;


  @Test
  void complete() throws NotEnoughMoneyException {
    Order order = new Order();
    order.setUsername("정상");

    orderService.order(order);

    final Order findOrder = orderRepository.findById(order.getId()).get();
    Assertions.assertThat(findOrder.getPayStatus()).isEqualTo("완료");
  }

  @Test
  void runtimeException() throws NotEnoughMoneyException {
    Order order = new Order();
    order.setUsername("예외");

    assertThatThrownBy(()->orderService.order(order)).isInstanceOf(RuntimeException.class);

    final Optional<Order> byId = orderRepository.findById(order.getId());
    assertThat(byId.isEmpty()).isTrue();

  }

  @Test
  void bizException() throws NotEnoughMoneyException {
    Order order = new Order();
    order.setUsername("잔고 부족");

    try{
      orderService.order(order);
    }catch (NotEnoughMoneyException e){
      log.info("고객에게 잔고 부족을 알리고 별도 처리한다");
    }

    final Order findOrder = orderRepository.findById(order.getId()).get();
    Assertions.assertThat(findOrder.getPayStatus()).isEqualTo("대기");

  }

}