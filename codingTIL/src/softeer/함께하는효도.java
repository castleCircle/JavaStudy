package softeer;

import java.util.Scanner;

public class 함께하는효도 {

  static int n, m;
  static int[][] grid;
  static int maxHarvest = 0;
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();
    sc.nextLine();

    grid = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        grid[i][j] = sc.nextInt();
      }
      sc.nextLine();
    }

    for (int i = 0; i < m; i++) {
      int x = sc.nextInt() - 1;
      int y = sc.nextInt() - 1;
      sc.nextLine();
    }

    System.out.println(maxHarvest);
  }
}
