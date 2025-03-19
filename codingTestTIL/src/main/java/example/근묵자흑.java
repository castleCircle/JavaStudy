package example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class 근묵자흑 {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int k = sc.nextInt();

    int[] number  = new int[n];

    for(int i=0;i<n;i++){
      number[i] = sc.nextInt();
    }

    System.out.println(Arrays.toString(number));

    int position = 0;
    int ans = 0;
    while(true){
      if(position >= n) break;
      if(position == 0) position = k;
      else position = position + k -1;
      ans++;

    }

    System.out.println(ans);

    sc.close();
  }

}
