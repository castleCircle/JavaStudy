package study;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FunctionTest1209 {

  public static void main(String[] args) {
      final List<Money> moneyList = Arrays.asList(
          new Money(1L,"A",new BigDecimal("10")),
          new Money(2L,"B",new BigDecimal("27.00")),
          new Money(3L,"C",new BigDecimal("35")),
          new Money(4L,"D",new BigDecimal("50")),
          new Money(5L,"E",new BigDecimal("120"))
      );

     final List<Money> filterMoney = moneyFilter(
         moneyList,money -> money.getPrice().compareTo(new BigDecimal("30"))>= 0);
    System.out.println(filterMoney);

//    final List<DiscountMoney> discountMonies = mony


  }

  public static <T> List<T> moneyFilter(List<T> list, Predicate<T> predicate){
    final List<T> returnList = new ArrayList<>();

    for(T t : list){
      if(predicate.test(t)){
        returnList.add(t);
      }
    }

    return returnList;
  }

}

@Data
@AllArgsConstructor
class Money{
  Long id;
  String name;
  BigDecimal price;
}

@ToString(callSuper = true)
class DiscountMoney extends Money{
  public DiscountMoney(Long id, String name, BigDecimal price) {
    super(id, name, price);
  }
}

