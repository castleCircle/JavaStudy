package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 도넛과막대그래프 {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(new int[][]{{2, 3}, {4, 3}, {1, 1}, {2, 1}})));
  }

  public static int[] solution(int[][] edges) {
    int[] answer = new int[4];
    Map<Integer,Integer> out = new HashMap<>();
    Map<Integer,Integer> in = new HashMap<>();

    for(int[] edge : edges) {
      out.put(edge[0],out.getOrDefault(edge[0],0) + 1);
      in.put(edge[1],out.getOrDefault(edge[1],0) + 1);
    }

    for(int key : out.keySet()){
      final Integer i = out.get(key);
      if(i > 1){
        if(!in.containsKey(key)){
          answer[0] = key;
        }else{
          answer[3] += 1;
        }
      }
    }

    for(int key : in.keySet()){
      if(!out.containsKey(key)){
        answer[2] += 1 ;
      }
    }

    answer[1] = out.get(answer[0]) - answer[2] - answer[3];

    return answer;
  }

}
