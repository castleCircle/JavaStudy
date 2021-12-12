package chapter08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StreamPrelude {

  public static void main(String[] args) {
    final int abs1 = Math.abs(-1);
    final int abs2 = Math.abs(1);

    System.out.println("ab1 == abs2 is " + (abs1 == abs2));

    final int minInt = Math.abs(Integer.MIN_VALUE);
    System.out.println("minInt: " + minInt);

    final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    System.out.println("old Map");
    System.out.println(
        mapOld(numbers, i->i*2)
    );
    System.out.println(
        mapOld(numbers,null)
    );

    System.out.println("new Map");
    System.out.println(map(numbers,i->i*2));
    System.out.println(map(numbers,i->i));
    System.out.println(map(numbers,Function.identity()));

  }

  private static <T,R> List<R> map(final List<T> list,final Function<T,R> mapper){
    final List<R> result = new ArrayList<>();
    for(final T t: list){
      result.add(mapper.apply(t));
    }
    return result;
  }

  private static <T,R> List<R> mapOld(final List<T> list, Function<T,R>mapper){
    final List<R> result = new ArrayList<>();
    for(final T t: list){
      if(mapper != null) {
        result.add(mapper.apply(t));
      }else{
        result.add((R)t);
      }
    }

    return result;
  }

}
