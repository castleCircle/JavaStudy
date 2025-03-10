package 백준.백트래킹;

import java.util.Scanner;

public class N과M1 {
  static int N;
  static int M;
  private static int[] arr;
  private static boolean[] visited;
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    N = scanner.nextInt();
    M = scanner.nextInt();

    arr = new int[M+1];
    visited = new boolean[N+1];

    deep(0);

  }

  static void deep(int count){
    if(count == M){
      for(int i = 0; i < M; i++){
        System.out.print(arr[i] + " ");
      }
      System.out.println();
      return;
    }
    for(int i=1;i<=N;i++){
      if(!visited[i]){
        visited[i] = true;
        arr[count] = i;
        deep(count+1);
        visited[i] = false;
        arr[count] = 0;
      }

    }
  }


}
