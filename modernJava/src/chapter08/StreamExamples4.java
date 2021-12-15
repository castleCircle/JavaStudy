package chapter08;

import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExamples4 {

  public static void main(String[] args) {
    Stream.of(1,2,3,4,5)
        .collect(toList());

    Stream.of(1,2,3,4,5)
        .collect(toSet());


  }

}
