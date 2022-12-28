package greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class 회의실배정 {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    final int N = sc.nextInt();

    int[][] time = new int[N][2];

    for(int i=0;i<N;i++){

      time[i][0] = sc.nextInt();
      time[i][1] = sc.nextInt();
    }

    Arrays.sort(time, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {

        if(o1[1] == o2[1]){
          return o1[0] - o2[0];
        }

        return o1[1] - o2[1];
      }
    });

    int count = 1;
    int temp = time[0][1];

    for(int i=1;i<N;i++){

      if(temp <= time[i][0]){
        count++;
        temp = time[i][1];
      }

    }

    System.out.println(count);


  }

}
