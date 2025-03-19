package example;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class 문제선정하기 {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int n = Integer.parseInt(sc.nextLine().trim());

    String[] input = sc.nextLine().trim().split(" ");
    int[] question = new int[n];

    Set<Integer> set = new HashSet<>();

    for(int i = 0; i < n; i++) {
      set.add(Integer.parseInt(input[i]));
    }

    if(set.size() >= 3){
      System.out.println("YES");
    }else{
      System.out.println("NO");
    }



    sc.close();


  }

}
