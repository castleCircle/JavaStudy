package chapter08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamExamples2 {
  public static void main(String[] args) throws Exception {
    Stream.of(1,2,3,4,5).forEach(i-> System.out.println(i + " "));

    final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
    Integer result = null;
    for(final Integer number : numbers){
      if(number > 3 && number < 9){
        final Integer newNumber = number * 2;

        if(newNumber > 10) {
          result = newNumber ;
          break;
        }
      }
    }
    System.out.println("Imperative REsult" + result);

    System.out.println("Functional Result: " +
    numbers.stream()
        .filter(i-> {
          System.out.println("number > 3");
          return i > 3;
        })
        .filter(i-> {
          System.out.println("number < 9");
          return i < 9;
        })
        .map(number -> {
          System.out.println("number * 2");
          return number * 2;
        })
        .filter(number -> {
          System.out.println("number > 10");
          return number > 10;
        })
        .findFirst().orElseThrow( () -> new Exception("test")));

    //- 08 Stream API - 03 Stream API 02 - Stream vs 예전방식

  }
}
