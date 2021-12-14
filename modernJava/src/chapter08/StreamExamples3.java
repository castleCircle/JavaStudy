package chapter08;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExamples3 {

  public static void main(String[] args) {

    //stream은 두가지가 있다.
    // 중간 연산 Intermediate Operation Method
    // 끝 연산 Terminal Operation Method

    final List<String> collect = Stream.of(1, 2, 3, 3,4, 5)
        .filter(i -> i > 2)
        .map(i -> i * 2)
        .map(i -> "#" + i)
        .collect(Collectors.toList());

    System.out.println(collect);

    final Set<String> collect1 = Stream.of(1, 2, 3, 3,4, 5)
        .filter(i -> i > 2)
        .map(i -> i * 2)
        .map(i -> "#" + i)
        .collect(Collectors.toSet());
    System.out.println(collect1);


    final String collect2 = Stream.of(1, 2, 3, 3, 4, 5)
        .filter(i -> i > 2)
        .map(i -> i * 2)
        .map(i -> "#" + i)
        .collect(Collectors.joining(","));
    System.out.println(collect2);

    final String collect3 = Stream.of(1, 2, 3, 3, 4, 5)
        .filter(i -> i > 2)
        .map(i -> i * 2)
        .map(i -> "#" + i)
        .collect(Collectors.joining(",","[","]"));
    System.out.println(collect3);

    final String collect4 = Stream.of(1, 2, 3, 3, 4, 5)
        .filter(i -> i > 2)
        .map(i -> i * 2)
        .map(i -> "#" + i)
        .distinct()
        .collect(Collectors.joining(",","[","]"));
    System.out.println(collect4);

    final List<String> collect5= Stream.of(1, 2, 3, 3, 4, 5)
        .filter(i -> i > 2)
        .map(i -> i * 2)
        .map(i -> "#" + i)
        .distinct()
        .collect(Collectors.toList());
    System.out.println(collect5);

    final Integer integer3 = 3;
    // Integer.valueOf(1) => Integer Object
    // auto Boxing , unboxing valueOf를 사용하는데 그건 캐싱이 있다. default 범위를 벗어나면 캐싱이 안되서 달라짐 == 가 아니라 object는 equals를 써야함
    System.out.println(
        Stream.of(1,2,3,4,5)
            .filter(i -> i == integer3)
            .findFirst()
    );

    final Integer integer127 = 127;
    // Integer.valueOf(1) => Integer Object
    System.out.println(
        Stream.of(1,2,3,4,127)
            .filter(i -> i == integer127)
            .findFirst()
    );

    final Integer integer128 = 128;
    // Integer.valueOf(1) => Integer Object
    System.out.println(
        Stream.of(1,2,3,4,128)
            .filter(i -> i == integer128)
            .findFirst()
    );

    Stream.of(1,2,3,4,5)
        .forEach(i -> System.out.println(i));

  }

}
