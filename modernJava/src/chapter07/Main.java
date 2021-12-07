package chapter07;

import java.math.BigDecimal;

public class Main {

  public static void main(String[] args) {
    BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();
    System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("120.00")));

    InvalidFunctionalInterface invalidFunctionalInterface = new InvalidFunctionalInterface() {
      @Override
      public <T> String mkString(T value) {
        return value.toString();
      }
    };

    System.out.println(invalidFunctionalInterface.mkString(123));

//    InvalidFunctionalInterface invalidFunctionalInterface1 = value -> value.toString();
//    System.out.println(invalidFunctionalInterface1.mkString(123));

  }

}

@FunctionalInterface
interface BigDecimalToCurrency {
  String toCurrency(BigDecimal value);
}

@FunctionalInterface
interface InvalidFunctionalInterface {
  //내부에 jeneric이면 람다를 못씀
  <T> String mkString(T value);
}