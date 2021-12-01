package chapter4;

//identify function이 왜 중요할까? 추후에 설명

import java.util.function.Function;

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

  }
}
