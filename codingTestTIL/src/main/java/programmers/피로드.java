package programmers;

public class 피로드 {

  static boolean[] visited;

  public int solution(int k, int[][] dungeons) {
    visited = new boolean[dungeons.length];
    return dfs(k, dungeons, 0);
  }

  public int dfs(int k, int[][] dungeons, int count) {
    int max = 0;
    for (int i = 0; i < dungeons.length; i++) {
      if (visited[i])
        continue;
      if (k >= dungeons[i][0]) {
        visited[i] = true;
        max = Math.max(max, dfs(k - dungeons[i][1], dungeons, count + 1));
        visited[i] = false;
      }
    }

    return Math.max(max, count);
  }

}
