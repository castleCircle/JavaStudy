package programmers;

import java.util.Stack;

public class 광물캐기 {

  private static String one = "11011";
  private static String zero = "00000";
  public static int solution(int n, long l, long r) {
    int answer = 0;

    for (long val = l; val <= r; val++) {
      answer += query(n, val - 1);
    }

    return answer;
  }

  static int query(int n, long l) {
    if (n == 0 || l == 0)
      return 1;
    if (l % 5 == 2)
      return 0;
    return query(n - 1, l / 5);
  }


  public static void main(String[] args) {
    System.out.println(solution(2, 4, 17));
    System.out.println(solution(1, 1, 1));
  }

}
