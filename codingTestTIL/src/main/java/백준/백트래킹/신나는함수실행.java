package 백준.백트래킹;

import java.util.Scanner;

public class 신나는함수실행 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (true) {
      String s = sc.nextLine();
      String[] split = s.split(" ");
      if (split[0].equals("-1") && split[1].equals("-1") && split[2].equals("-1")) {
        break;
      }

      int w = w(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
      System.out.printf("w(%d, %d, %d) = %d%n", Integer.parseInt(split[0]), Integer.parseInt(split[1]),
          Integer.parseInt(split[2]), w);
    }
  }

  static int[][][] dp = new int[21][21][21];

  static boolean isRange(int a, int b, int c) {
    return a >= 0 && a <= 20 && b >= 0 && b <= 20 && c >= 0 && c <= 20;
  }

  static int w(int a, int b, int c) {

    if (isRange(a, b, c) && dp[a][b][c] != 0) {
      return dp[a][b][c];
    }

    if (a <= 0 || b <= 0 || c <= 0) {
      return 1;
    }

    if (a > 20 || b > 20 || c > 20) {
      return dp[20][20][20] = w(20, 20, 20);
    }

    if (a < b && b < c) {
      return dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
    }

    return dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
  }
}
