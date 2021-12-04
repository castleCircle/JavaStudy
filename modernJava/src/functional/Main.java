package functional;

//identify function이 왜 중요할까? 추후에 설명

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
  public static void main(String[] args) {

//    Function<String,Integer> toInt = new Function<String, Integer>() {
//      @Override
//      public Integer apply(String s) {
//        return Integer.parseInt(s);
//      }
//    };

    Function<String, Integer> toInt = (value) -> Integer.parseInt(value);

    final Integer number = toInt.apply("100");
    System.out.println(number);

    final Function<Integer, Integer> identity = Function.identity();

    System.out.println(identity.apply(999));


    final Consumer<String> print = value -> System.out.println(value);
    //여기서 의문 Consumer를 써야하나? Function으로 할수 있지 않나? 그러면 Funciton<String,Void> 하면 되는거 아닌가가 라는 생각을 하지만 Void 타입을 리턴해줄께 없다.ㅋㅋㅋ

    print.accept("test");

    final Predicate<Integer> isPositive = new Predicate<Integer>() {
      @Override
      public boolean test(Integer integer) {
        if (integer > 0) {
          return true;
        }
        return false;
      }
    };

    List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);
    var positiveList = filter(numbers, isPositive);
    System.out.println(positiveList);


    //Supplier는 lazy 가능하다.
    Supplier<String> supplierTest = new Supplier<String>() {
      @Override
      public String get() {
        return "test";
      }
    };

    System.out.println(supplierTest.get());
    testFunction(1,multiCalculate());
    testFunction(0,multiCalculate());
    testFunction(-1,multiCalculate());

    Supplier<String> cal = ()->{
      //long calculate
      return "long Cal";
    };

    supplierTestFunction(1,cal);
    supplierTestFunction(0,cal);
    supplierTestFunction(-1,cal);

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


  private static String multiCalculate(){
    //10초 걸리는 연산
    return "hello";
  }

  private static void testFunction(Integer value, String result){
    if(value > 0 ){
      System.out.println("multiCalculate : " + result);
    }else{
      System.out.println("minus");
    }
  }

  private static void supplierTestFunction(Integer value,Supplier<String> supplier){
    if(value > 0){
      System.out.println("multiCalculate : " + supplier.get());
    }else{
      System.out.println("minus");
    }
  }

}
