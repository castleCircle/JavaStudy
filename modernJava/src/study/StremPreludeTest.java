package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StremPreludeTest {

  public static void main(String[] args) {
    final List<Integer> numberList = Arrays.asList(1,2,3,4,5);
    System.out.println(map(numberList,i->i*2));
    System.out.println(map(numberList,i->i));
    System.out.println(map(numberList,Function.identity()));
  }

  private static <T,R> List<R> map(List<T> list, Function<T,R> function){
    final List<R> result = new ArrayList<>();
    for(T t: list){
      result.add(function.apply(t));
    }
    return result;
  }

}
