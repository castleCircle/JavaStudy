package functional;

//identify function이 왜 중요할까? 추후에 설명

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
  public static void main(String[] args) {

//    Function<String,Integer> toInt = new Function<String, Integer>() {
//      @Override
//      public Integer apply(String s) {
//        return Integer.parseInt(s);
//      }
//    };

    Function<String,Integer> toInt = (value) -> Integer.parseInt(value);

    final Integer number = toInt.apply("100");
    System.out.println(number);

    final Function<Integer,Integer> identity = Function.identity();

    System.out.println(identity.apply(999));


    final Consumer<String> print = value -> System.out.println(value);
    //여기서 의문 Consumer를 써야하나? Function으로 할수 있지 않나? 그러면 Funciton<String,Void> 하면 되는거 아닌가가 라는 생각을 하지만 Void 타입을 리턴해줄께 없다.ㅋㅋㅋ

    print.accept("test");

    final Predicate<Integer> isPositive = new Predicate<Integer>() {
      @Override
      public boolean test(Integer integer) {
        if(integer > 0){
          return true;
        }
        return false;
      }
    };

    List<Integer> numbers= Arrays.asList(-5,-4,-3,-2,-1,0,1,2,3,4,5);
    var positiveList = filter(numbers,isPositive);
    System.out.println(positiveList);
  }

  private static <T> List<T> filter(List<T> list , Predicate<T> filter){
    List<T> result = new ArrayList<>();
    for(T input : list){
      if(filter.test(input)){
        result.add(input);
      }
    }
    return result;
  }

}
