package study;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionTest1209 {

  public static void main(String[] args) {
     Money moneyA= new Money(1L, "A", new BigDecimal("10"));
     Money moneyB = new Money(2L, "B", new BigDecimal("27.00"));
     Money moneyC = new Money(3L, "C", new BigDecimal("35"));
     Money moneyD = new Money(4L, "D", new BigDecimal("50"));
     Money moneyE = new Money(5L, "E", new BigDecimal("120"));
     List<Money> moneyList = Arrays.asList(
        moneyA,
        moneyB,
        moneyC,
        moneyD,
        moneyE
      );

     final List<Money> expensiveMoneys = moneyFilter(
         moneyList,money -> money.getPrice().compareTo(new BigDecimal("50"))>= 0);
    System.out.println("expensiveMoney : " + expensiveMoneys);

     final List<DiscountMoney> discountMoneyList = moneyMap(
         expensiveMoneys,money ->
             new DiscountMoney(money.getId(),money.getName(),money.getPrice().multiply(new BigDecimal("0.5"))));
    System.out.println("discoutedMony : "+discountMoneyList);

    Predicate<Money> lessThanOfEqual30 = money -> money.getPrice().compareTo(new BigDecimal("30")) <= 0;
    System.out.println("moneyFilter : " + moneyFilter(moneyList,lessThanOfEqual30));
    System.out.println("discountedFilter : " + moneyFilter(discountMoneyList,lessThanOfEqual30));

    final BigDecimal total = totalTest(moneyList,money->money.getPrice());
    System.out.println("total : " + total );

    OrderTest order = new OrderTest(1L,"0n-1234", Arrays.asList(
       new OrderedItemTest(1L,moneyA,2),
       new OrderedItemTest(2L,moneyC,1),
       new OrderedItemTest(3L,moneyD,10)
    ));

    System.out.println(order.totalPrice());

  }

  public static <T> List<T> moneyFilter(List<T> list, Predicate<? super T> predicate){
    final List<T> returnList = new ArrayList<>();
    for(T t : list){
      if(predicate.test(t)){
        returnList.add(t);
      }
    }
    return returnList;
  }

 public static <T,R> List<R> moneyMap(List<T> list, Function<T,R> function){
    final List<R> discountedMoneyList = new ArrayList<>();
    for(T t:list){
      discountedMoneyList.add(function.apply(t));
    }
    return discountedMoneyList;
 }

 private static <T> BigDecimal totalTest(List<T> list,Function<T,BigDecimal> function){
    BigDecimal bigDecimal = BigDecimal.ZERO;
    for(T t : list){
      bigDecimal = bigDecimal.add(function.apply(t));
    }
    return bigDecimal;
 }


  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  static
  class Money{
    Long id;
    String name;
    BigDecimal price;
  }

  @ToString(callSuper = true)
  static
  class DiscountMoney extends Money{
    public DiscountMoney(Long id, String name, BigDecimal price) {
      super(id, name, price);
    }
  }

  @AllArgsConstructor
  @Data
  static class OrderedItemTest{
    private Long id;
    private Money money;
    private int quantity;

    public BigDecimal getItemTotal(){
      return money.getPrice().multiply(new BigDecimal(quantity));
    }
  }

  @AllArgsConstructor
  @Data
  static class OrderTest{
    private Long id;
    private String orderNumber;
    private List<OrderedItemTest> items;

    public BigDecimal totalPrice(){
      return totalTest(items,item->item.getItemTotal());
    }

  }

}
