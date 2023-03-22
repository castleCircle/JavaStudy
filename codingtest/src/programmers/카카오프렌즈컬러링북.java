package programmers;

import java.util.Arrays;
import java.util.Map;

public class 카카오프렌즈컬러링북 {

  static boolean[][] map;

  public static void main(String[] args) {

    int[][] picture = new int[][] {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
//    assertArrayEquals(new int[] {4 , 5}, test.solution(6, 4, picture));

    int[][] picture1 = new int[][] {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
//    assertArrayEquals(new int[] {0 , 0}, test.solution(5, 5, picture1));

    int[][] picture2 = new int[][] {{1, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 1}};
//    assertArrayEquals(new int[] {3 , 1}, test.solution(5, 5, picture2));

    int[][] picture3 = new int[][] {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
//    assertArrayEquals(new int[] {1 , 25}, test.solution(5, 5, picture3));

    int[][] picture4 = new int[][] {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 100, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
//    assertArrayEquals(new int[] {2 , 24}, test.solution(5, 5, picture4));

    int[][] picture5 = new int[][] {{1, 1, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
//    assertArrayEquals(new int[] {2 , 6}, test.solution(6, 4, picture5));

    int[][] picture6 = new int[][] {{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 0}, {0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0}, {0, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}};
//    assertArrayEquals(new int[] {12 , 120}, test.solution(13, 16, picture6));

    int[][] picture7 = new int[][] {{1,0},{0,1}};


    System.out.println(Arrays.toString(solution(6,4,new int[][]{{1, 1, 1, 0},{1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}})));
    System.out.println(Arrays.toString(solution(5,5,picture1)));
    System.out.println(Arrays.toString(solution(5,5,picture2)));
    System.out.println(Arrays.toString(solution(5,5,picture3)));
    System.out.println(Arrays.toString(solution(5,5,picture4)));
    System.out.println(Arrays.toString(solution(6,4,picture5)));
    System.out.println(Arrays.toString(solution(13,16,picture6)));
    System.out.println(Arrays.toString(solution(2,2,picture7)));

  }

  public static int[] solution(int m,int n,int[][] picture){
    int max = 0;
    int count = 0;

    map = new boolean[m][n];

    for(int i=0;i<m;i++){
      for(int j=0;j<n;j++){

        if(!map[i][j] && picture[i][j] != 0) {
          final int dfsCount = dfs(i, j, picture, picture[i][j]);
          count++;
          if (max < dfsCount) {
            max = dfsCount;
          }
        }
      }
    }

    return new int[]{count,max};
  }

  public static int dfs(int m,int n,int[][] picture,int position){

    if(m < 0 || n <0 || m >= picture.length || n >= picture[0].length){
      return 0;
    }

    if(map[m][n]){
      return 0;
    }

    if(picture[m][n] != position){
      return 0;
    }

    map[m][n] = true;

    return 1 + dfs(m-1,n,picture,position) + dfs(m+1,n,picture,position) + dfs(m,n-1,picture,position) + dfs(m,n+1,picture,position);
  }

}
