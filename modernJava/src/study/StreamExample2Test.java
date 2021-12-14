package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamExample2Test {

  public static void main(String[] args) {
    final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
    final List<Integer> more3 = filter(numbers, i-> i>3);
    final List<Integer> less9 = filter(more3, i-> i<9);
    final List<Integer> multi3 = map(less9, i-> i*3);
    System.out.println(more3);
    System.out.println(less9);
    System.out.println(multi3);

    final Optional<Integer> first = Optional.of(numbers.stream()
        .filter(i -> i > 10)
        .filter(i -> i < 9)
        .map(i -> i * 3)
        .findFirst()
        .orElse(new Integer(100)));

    System.out.println(first);

  }

  private static <T> List<T> filter(List<T> list, Predicate<T> predicate){
    final List<T> result= new ArrayList<>();

    for(final T t : list){
      if(predicate.test(t)){
        result.add(t);
      }
    }
    return result;
  }

  private static <T,R> List<R> map(List<T> list, Function<T,R> mapper){
    final List<R> result = new ArrayList<>();
    for(final T t : list){
      result.add(mapper.apply(t));
    }
    return result;
  }

}
