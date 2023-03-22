package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 무인도여행 {

  static int[][] map;

  public static void main(String[] args) {
    System.out.println(solution(new String[]{"X591X","X1X5X","X231X", "1XXX1"}));
  }

  public static int[] solution(String[] maps) {

    map = new int[maps.length][maps[0].length()];

    for(int i=0;i<maps.length;i++){
      final char[] chars = maps[i].toCharArray();

      for(int j=0;j<chars.length;j++){
        char c = chars[j];

        if(c == 'X' || c == 'x'){
          map[i][j] = -1;
        }else{
          map[i][j] = c - '0';
        }
      }
    }

    List<Integer> answer = new ArrayList<>();

    for(int i=0;i<maps.length;i++){
      for(int j=0;j<maps[0].length();j++){
        int sum = dfs(i,j);
        if(sum >0){
          answer.add(sum);
        }
      }
    }

    if(answer.size() ==0){
      return new int[]{-1};
    }

    Collections.sort(answer);

    return Arrays.stream(answer.toArray()).mapToInt(o->(int)o).toArray();
  }

  public static int dfs(int i,int j){

    if(i<0 || j <0 || i>= map.length || j>= map[0].length){
      return 0;
    }

    if(map[i][j] == -1){
      return 0;
    }

    int tmp = map[i][j];
    map[i][j] = -1;

    return tmp + dfs(i-1,j) + dfs(i+1,j) + dfs(i,j-1) + dfs(i,j+1);

  }

}
