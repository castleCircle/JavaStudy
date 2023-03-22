package greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class 계단오르기 {

  static char[] inputs1;
  static char[] inputs2;

  static Integer[][] dp;

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    inputs1 = sc.nextLine().toCharArray();
    inputs2 = sc.nextLine().toCharArray();

    dp = new Integer[inputs1.length][inputs2.length];

    System.out.println(LCS(inputs1.length-1,inputs2.length-1));
  }

  public static int LCS(int x,int y){

    if(x == -1 || y==-1){
      return 0;
    }

    if(dp[x][y]==null){
      dp[x][y] = 0;

      if(inputs1[x] == inputs2[y]){
        dp[x][y] = LCS(x-1,y-1) + 1;
      }else{
        dp[x][y] = Math.max(LCS(x-1,y),LCS(x,y-1));
      }

    }

    return dp[x][y];
  }


}
