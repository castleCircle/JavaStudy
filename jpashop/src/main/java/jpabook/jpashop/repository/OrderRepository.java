package jpabook.jpashop.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

  private final EntityManager em;

  public void save(Order order){
    em.persist(order);
  }

  public Order findOne(Long id){
    return em.find(Order.class,id);
  }

  public List<Order> findAllByString(OrderSearch orderSearch){
    String jpql = "select o from Order o join o.member m";
    boolean isFirstCondition = true;

    if(orderSearch.getOrderStatus() !=null){
      if(isFirstCondition){
        jpql += " where";
        isFirstCondition = false;
      }else{
        jpql += " and";
      }
      jpql += " o.status = :status";
    }

    if(StringUtils.hasText(orderSearch.getMemberName())){
      if(isFirstCondition){
        jpql += " where";
        isFirstCondition = false;
      }else{
        jpql += " and";
      }
      jpql += " m.name like :name";
    }

    TypedQuery<Order> query = em.createQuery(jpql,Order.class).setMaxResults(1000);

    if(orderSearch.getOrderStatus() != null){
      query = query.setParameter("status",orderSearch.getOrderStatus());
    }
    if(StringUtils.hasText(orderSearch.getMemberName())){
      query = query.setParameter("name",orderSearch.getMemberName());
    }

    return query.getResultList();
  }

  public List<Order> findAllWithMemberDelivery(int offset, int limit) {
    return em.createQuery(
        "select o from Order o" +
            " join fetch o.member m" +
            " join fetch o.delivery d",Order.class)
        .setFirstResult(offset)
        .setMaxResults(limit)
        .getResultList();
  }

  /**
   * 컬렉션 패치 조인은 페이징 불가능하고 컬렉션 페치 조인은 하나만 써야함
   * @return
   */
  public List<Order> findAllWithItem() {
    return em.createQuery(
        "select distinct o from Order o"+
                " join fetch o.member m"+
                " join fetch o.delivery d"+
                " join fetch o.orderItems oi"+
                " join fetch oi.item i",Order.class)
        .getResultList();
  }
}
