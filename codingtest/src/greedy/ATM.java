package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class ATM {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int[] pList = new int[N];

    for(int i=0;i<N;i++){
      pList[i] = sc.nextInt();
    }

    Arrays.sort(pList);
    int sum = 0;

    for(int i=0;i<N;i++){

      for(int j=0;j<=i;j++){
        sum += pList[j];
      }
    }

    System.out.println(sum);
  }

}
