package programmers;

import java.util.Arrays;

public class 연속펄스부분수열의합 {

  public static void main(String[] args) {
    System.out.println(solution(new int[]{2,3,-6,1,3,-1,2,4}));
  }


  public static long solution(int[] sequence) {

    long[] numbers1 = new long[sequence.length];
    long[] numbers2 = new long[sequence.length];

    int n = 1;
    for (int i = 0; i < sequence.length; i++) {
      numbers1[i] = sequence[i] * n ;
      n *= -1;
      numbers2[i] = sequence[i] * n ;
    }

    long[] dp1 = new long[sequence.length];
    long[] dp2 = new long[sequence.length];
    dp1[0] = numbers1[0];
    dp2[0] = numbers2[0];
    long sum = Math.max(dp1[0],dp2[0]);
    for(int i = 1; i < numbers1.length; i++) {
      dp1[i] = Math.max(dp1[i-1] + numbers1[i], numbers1[i]);
      dp2[i] = Math.max(dp2[i-1] + numbers2[i], numbers2[i]);

      long tt= Math.max(dp1[i], dp2[i]);
      sum = Math.max(sum,tt);
    }


    return sum;
  }

}
