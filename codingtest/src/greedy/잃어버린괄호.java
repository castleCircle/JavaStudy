package greedy;

import java.util.Scanner;

public class 잃어버린괄호 {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    final String N = sc.nextLine();

    String[] split = N.split("-");

    int sum = 0;

    for(int i=0;i<split.length;i++){

      final String[] split1 = split[i].split("\\+");

      int temp = 0;

      if(split1.length > 2){

        for(int j=0;j<split1.length;j++){
          temp += Integer.parseInt(split1[j]);
        }

      }
      else if(split1.length == 2){
        temp = (Integer.parseInt(split1[0]) + Integer.parseInt(split1[1]));
      }else{
        temp = Integer.parseInt(split1[0]);
      }

      if(i==0){
        sum += temp;
      }else{
        sum -= temp;
      }

    }
    System.out.println(sum);


  }

}
