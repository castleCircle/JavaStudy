package inflearn.item1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListQuiz {

  public static void main(String[] args) {

    List<Integer> numbers = new ArrayList<>();

    numbers.add(1);
    numbers.add(100);
    numbers.add(13);
    numbers.add(46);

    System.out.println(numbers);

    Comparator<Integer> desc = (o1,o2) -> o2 - o1;
    numbers.sort(desc);

    System.out.println(numbers);

    numbers.sort(desc.reversed());

    System.out.println(numbers);

  }

}
