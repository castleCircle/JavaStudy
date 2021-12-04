package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    Function<String,Integer> functionTest = (string) -> Integer.parseInt(string);
    System.out.println(functionTest.apply("1234"));

    Consumer<String> consumerTest = (arg) -> System.out.println(arg);
    consumerTest.accept("hello");

    Predicate<Integer> positiveTest = input -> {
      if(input > 0){
        return true;
      }
      return false;
    };

    Predicate<String> matchPattern = input -> {
      if(input.equals("test")){
        return true;
      }
      return false;
    };

    List<Integer> numberList = Arrays.asList(-3,-2,-1,0,1,2,3);
    List<String> stringList = Arrays.asList("test","test1","test2");
    var positiveList = filterFunction(numberList,positiveTest);
    var matchList = filterFunction(stringList,matchPattern);

    System.out.println(positiveList);
    System.out.println(matchList);

    final Supplier<String> helloSupplier = () -> "hello";
    System.out.println(helloSupplier.get());

    printIfValid(0,"won");
    printIfValid(-1,"won");

    long start = System.currentTimeMillis();
    printIfValid(0,getVeryExpensiveValue());
    printIfValid(-1,getVeryExpensiveValue());
    printIfValid(-2,getVeryExpensiveValue());
    System.out.println("It Look " + (System.currentTimeMillis() - start) / 1000);

    printIfValidSupplier(0, ()->getVeryExpensiveValue());
    printIfValidSupplier(-1,()->getVeryExpensiveValue());
    printIfValidSupplier(-2,()->getVeryExpensiveValue());

  }

  private static String getVeryExpensiveValue(){
    try {
      TimeUnit.SECONDS.sleep(3);
    }catch(Exception e){

    }
    return "kevin";
  }

  private static void printIfValid(int  number, String value){
    if(number >= 0){
      System.out.println("The value is : " + value);
    }else{
      System.out.println("Invalid");
    }
  }

  private static void printIfValidSupplier(int  number, Supplier<String> value){
    if(number >= 0){
      System.out.println("The value is : " + value.get());
    }else{
      System.out.println("Invalid");
    }
  }

  public static <T> List<T> filterFunction(List<T> list , Predicate predicate){
    List<T> returnList = new ArrayList<>();

    for(T input : list){
      if(predicate.test(input)){
        returnList.add(input);
      }
    }

    return returnList;
  }

}
