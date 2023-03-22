package greedy;

import java.util.HashSet;
import java.util.Set;

public class Solution {

  public static void main(String[] args) {
    System.out.println(solution(new int[]{1,2,1,3,1,4,1,2}));
    System.out.println(solution(new int[]{1,2,3,1,4}));
  }

  public static int solution(int[] topping) {
    int answer = 0;

    Set<Integer> left = new HashSet<>();
    int[] dpLeft = new int[topping.length];
    int[] dpRight= new int[topping.length];

    for(int i=0;i<topping.length-1;i++){
      left.add(topping[i]);
      dpLeft[i] = left.size();
    }

    left.clear();

    for(int i=topping.length-1;i>0;i--){
      left.add(topping[i]);
      dpRight[i]  = left.size();
    }



    for(int i=0;i<topping.length-1;i++){

      if(dpLeft[i] == dpRight[i+1]){
        answer++;
      }

    }

    return answer;
  }

}
