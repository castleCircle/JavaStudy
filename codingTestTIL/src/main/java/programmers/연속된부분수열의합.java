package programmers;

import java.util.Arrays;

public class 연속된부분수열의합 {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(new int[]{1,2,3,4,5},7)));
    System.out.println(Arrays.toString(solution(new int[]{1,1,1,2,3,4,5},5)));
    System.out.println(Arrays.toString(solution(new int[]{2,2,2,2,2},6)));
  }


  public static int[] solution(int[] sequence, int k) {
    int sum = 0;
    int left = 0;
    int right = 0;
    int length = Integer.MAX_VALUE;
    int start = 0;
    int end = 0;
    for (int i = 0; i < sequence.length; i++) {
      left = i;
      right = i;
      int count = 0;
      while (sum < k && right <= sequence.length -1 ) {
        sum += sequence[right];
        right++;
        count++;
      }

      if (sum == k) {
        right--;
        if(count < length){
          start = left;
          end = right;
          length = count;
        }
      }
      sum = 0;
    }

    return new int[]{start,end};
  }

}
