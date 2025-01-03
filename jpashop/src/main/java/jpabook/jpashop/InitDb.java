package jpabook.jpashop;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

  private final InitService initService;

  @PostConstruct
  public void init(){
    initService.doInit1();
    initService.doInit2();
  }


  @Component
  @Transactional
  @RequiredArgsConstructor
  static class InitService{

    private final EntityManager em;
    public void doInit1(){
      Member member = createMember("userA","서울","1","1111");
      em.persist(member);

      Book book1 = createBook("JPA1 BOOK",10000, 100);
      em.persist(book1);

      Book book2 =createBook("JPA2 BOOK",20000, 100);
      em.persist(book2);

      final OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
      final OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

      Delivery delivery = createDelivery(member);
      final Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);

      em.persist(order);
    }

    public void doInit2(){
      Member member = createMember("userB","진주","2","2222");
      em.persist(member);

      Book book1 = createBook("SPRING1 BOOK",20000, 200);
      em.persist(book1);

      Book book2 = createBook("SPRING1 BOOK",40000, 300);
      em.persist(book2);

      final OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
      final OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

      Delivery delivery = createDelivery(member);
      final Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);

      em.persist(order);
    }

    private Member createMember(String name,String city,String street,String zipCode){
      Member member = new Member();
      member.setName(name);
      member.setAddress(new Address(city,street,zipCode));
      return member;
    }

    private Book createBook(String s, int i, int stockQuantity) {
      Book book1 = new Book();
      book1.setName(s);
      book1.setPrice(i);
      book1.setStockQuantity(stockQuantity);
      return book1;
    }

    private Delivery createDelivery(Member member){
      Delivery delivery = new Delivery();
      delivery.setAddress(member.getAddress());
      return delivery;
    }

  }

}



