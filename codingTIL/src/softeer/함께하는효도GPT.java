package softeer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 함께하는효도GPT {

  static int n, m;
  static int[][] grid;
  static List<int[]> friends;
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

    friends = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      int x = sc.nextInt() - 1;
      int y = sc.nextInt() - 1;
      friends.add(new int[]{x, y});
      sc.nextLine();
    }

    calculateMaxHarvest();

    System.out.println(maxHarvest);
  }

  private static void calculateMaxHarvest() {
    int[] initialHarvests = new int[m];
    boolean[][] visited = new boolean[n][n];
    for (int i = 0; i < m; i++) {
      initialHarvests[i] = grid[friends.get(i)[0]][friends.get(i)[1]];
      visited[friends.get(i)[0]][friends.get(i)[1]] = true;
    }
    dfs(0, 0, initialHarvests, visited);
  }

  private static void dfs(int friendIndex, int time, int[] harvests, boolean[][] visited) {
    if (time == 3) {
      int totalHarvest = 0;
      for (int harvest : harvests) {
        totalHarvest += harvest;
      }
      maxHarvest = Math.max(maxHarvest, totalHarvest);
      return;
    }

    if (friendIndex == m) {
      dfs(0, time + 1, harvests, visited);
      return;
    }

    int[] currentPos = friends.get(friendIndex);
    int x = currentPos[0];
    int y = currentPos[1];

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
        visited[nx][ny] = true;
        harvests[friendIndex] += grid[nx][ny];

        int[] previousPos = friends.set(friendIndex, new int[]{nx, ny});
        dfs(friendIndex + 1, time, harvests, visited);
        friends.set(friendIndex, previousPos);

        harvests[friendIndex] -= grid[nx][ny];
        visited[nx][ny] = false;
      }
    }
    dfs(friendIndex + 1, time, harvests, visited);
  }
}
