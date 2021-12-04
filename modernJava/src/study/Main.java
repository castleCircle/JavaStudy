package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

  public static void main(String[] args) {

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
