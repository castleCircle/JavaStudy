package greedy;

import java.util.Scanner;

public class 주유소 {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();


    long[] dist = new long[N-1];
    long[] cost = new long[N];

    for(int i=0;i<N-1;i++){
      dist[i] = sc.nextLong();
    }

    for(int i=0;i<N;i++){
      cost[i] = sc.nextLong();
    }

    long sum = 0;
    long defaultCost = cost[0];

    for(int i=0;i<N-1;i++){

      if(defaultCost > cost[i]){
        defaultCost = cost[i];
      }

      sum += defaultCost * dist[i];

    }

    System.out.println(sum);

  }

}
