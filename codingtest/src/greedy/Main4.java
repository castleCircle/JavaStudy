package greedy;


/**
 * 정수 n이 주어졌을때 , 합이 n이 되는 제곱수의 최소개수
 */

//https://www.acmicpc.net/problem/1699

public class Main4 {

  public static void main(String[] args) {
    System.out.println(solution(12));
  }

  static int solution(int n){
    int answer = 0;

    int[] dp = new int[n+1];

    for (int i = 1; i <= n; i++) {
      dp[i] = i;
      for (int j = 1; j * j <= i; j++) {
        if (dp[i] > dp[i - j * j] + 1) {
          dp[i] = dp[i - j * j] + 1;
        }
      }
    }
    return dp[n];
  }

}
