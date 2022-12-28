package jpabook.jpashop.repository.order.simplequery;

import java.util.List;
import javax.persistence.EntityManager;
import jpabook.jpashop.repository.OrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

  private final EntityManager em;

  public List<OrderSimpleQueryDto> findOrderDtos(){
    return em.createQuery(
            "select o from Order o" +
                " join o.member m"+
                " join o.delivery d",OrderSimpleQueryDto.class)
        .getResultList();
  }

}
