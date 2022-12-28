package greedy;

import java.util.Scanner;

public class 동전 {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int K = sc.nextInt();

    int[] array = new int[N];

    for(int i=0;i<N;i++){
      array[i] = sc.nextInt();
    }

    int min = Integer.MAX_VALUE;

    for(int i=0;i<N;i++){

      if(array[i] > K){
        continue;
      }

      int count = 0;
      int temp = K;

      for(int j=i;j>=0;j--){

        final int i1 = temp % array[j];

        if(i1 == 0){
          int tempGap = temp / array[j];
          if( tempGap < min ){
            count = count + tempGap;
          }
          break;
        }

        count = count + (temp / array[j]);
        temp = i1;

      }

      if(min > count){
        min = count;
      }
    }

    System.out.println(min);

  }
}
