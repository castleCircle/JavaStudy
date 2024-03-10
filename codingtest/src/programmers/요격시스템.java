package programmers;

import java.util.Arrays;

public class 요격시스템 {

  public static void main(String[] args) {
    System.out.println(solution(new int[][]{
        {4, 5}, {4, 8}, {10, 14}, {11, 13},
        {5, 12},{3, 7},{1, 4}
    }));
  }

  public static int solution(int[][] targets) {
    int answer = 0;

    Arrays.sort(targets,(o1,o2) -> {
      return o1[1] - o2[1];
    });

    int rocket = 0;

    for(int[] target : targets){
      if(rocket < target[0]+1){
        rocket = target[1];
        answer++;
      }
    }

    return answer;
  }


}
