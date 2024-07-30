package onestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

;

public class number2 {

  public static int[] solution(int[] balls, int[] orders) {
    Deque<Integer> deque = new LinkedList<>();
    for (int ball : balls) {
      deque.add(ball);
    }

    List<Integer> result = new ArrayList<>();
    Set<Integer> waitList = new HashSet<>();

    for (int order : orders) {
      if (deque.peekFirst() == order) {
        result.add(deque.pollFirst());
      } else if (deque.peekLast() == order) {
        result.add(deque.pollLast());
      } else {
        waitList.add(order);
      }

      while (!waitList.isEmpty()) {
        if (waitList.contains(deque.peekFirst())) {
          int removed = deque.pollFirst();
          result.add(removed);
          waitList.remove(removed);
        } else if (waitList.contains(deque.peekLast())) {
          int removed = deque.pollLast();
          result.add(removed);
          waitList.remove(removed);
        } else {
          break;
        }
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  public static void main(String[] args) {
    int[] balls = {1, 2, 3, 4, 5, 6};
    int[] order = {6, 2, 5, 1, 4, 3};
    System.out.println(Arrays.toString(solution(balls, order)));  // [6, 5, 2, 4, 3, 1]

    int[] balls1 = {11, 2, 9, 13, 24};
    int[] order1 = {9, 2, 13, 24, 11};
    System.out.println(Arrays.toString(solution(balls1, order1)));  // [6, 5, 2, 4, 3, 1]
  }
}
