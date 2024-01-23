package acmpic;

import java.util.Scanner;

public class solve4963 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while(true) {
      int w = sc.nextInt();
      int h = sc.nextInt();

      if(w == 0 && h == 0){
        break;
      }

      final int[][] maps = new int[h][w];

      for(int i=0;i<h;i++){
        for(int j=0;j<w;j++){
          maps[i][j] = sc.nextInt();
        }
      }

      int sum= 0;

      for(int i=0;i<h;i++){
        for(int j=0;j<w;j++){
          if(start(i,j,h,w,maps,0) > 0){
            sum++;
          }
        }
      }

      System.out.println(sum);
    }


  }

  public static int start(int i,int j,int h,int w,int[][] maps,int sum){

    if(i<0 || i>=h || j <0 || j>=w){
      return 0;
    }

    if(maps[i][j] != 1){
      return 0;
    }

    if(maps[i][j] == 1){
      maps[i][j] = 2;
      sum++;
      return sum + start(i-1,j-1,h,w,maps,sum) + start(i-1,j,h,w,maps,sum)
          +start(i-1,j+1,h,w,maps,sum) + start(i,j+1,h,w,maps,sum)
          +start(i+1,j+1,h,w,maps,sum) + start(i+1,j,h,w,maps,sum)
          +start(i+1,j-1,h,w,maps,sum) + start(i,j-1,h,w,maps,sum);
    }

    return sum;
  }

}
