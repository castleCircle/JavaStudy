package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

@Entity
@Table(name="orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Order {

  @Id
  @GeneratedValue
  @Column(name="order_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
  private List<OrderItem> orderItems = new ArrayList<>();

  @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JsonIgnore
  @JoinColumn(name = "delivery_id")
  private Delivery delivery;

  private LocalDateTime orderDate;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  //====//
  public void setMember(Member member){
    this.member = member;
    member.getOrders().add(this);
  }

  public void addOrderItem(OrderItem orderItem){
    orderItems.add(orderItem);
    orderItem.setOrder(this);
  }

  public void setDelivery(Delivery delivery){
    this.delivery = delivery;
    delivery.setOrder(this);
  }

  public static Order createOrder(Member member,Delivery delivery,OrderItem... orderItems){
    Order order = new Order();
    order.setMember(member);
    order.setDelivery(delivery);
    for(OrderItem orderItem : orderItems){
      order.addOrderItem(orderItem);
    }
    order.setStatus(OrderStatus.ORDER);
    order.setOrderDate(LocalDateTime.now());
    return order;
  }

  /**
   * 주문 취소
   */
  /**
   * 마이바티스였으면 상태를 업데이트 치고 각자 업데이트 쿼리를 다 짜야한다.
   */
  public void cancel(){
    if(delivery.getStatus() == DeliveryStatus.COMP){
      throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
    }

    this.setStatus(OrderStatus.CANCEL);
    for(OrderItem orderItem: orderItems){
      orderItem.cancel();
    }
  }

  /**
   * 전체 주문 가격 조회
   */
  public int getTotalPrice(){
    int totalPrice = 0;
    for(OrderItem orderItem: orderItems){
      totalPrice += orderItem.getTotalPrice();
    }
    return totalPrice;
  }

}
