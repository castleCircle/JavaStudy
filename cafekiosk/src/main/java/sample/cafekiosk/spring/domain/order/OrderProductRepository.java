package sample.cafekiosk.spring.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.cafekiosk.spring.domain.ordderproduct.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
