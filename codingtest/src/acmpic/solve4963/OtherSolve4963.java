package acmpic.solve4963;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point{
  int ty;
  int tx;

  public Point(int ty, int tx) {
    this.ty = ty;
    this.tx = tx;
  }
}

public class OtherSolve4963 {

  private static int[] y = {-1,-1,-1, 0, 1, 1, 1, 0};
  private static int[] x = {-1, 0, 1, 1, 1, 0,-1,-1};

  public static void dfs(int[][] temp,int[][] maps,int ty,int tx,int count) {

    Queue<Point> points = new LinkedList<>();
    points.add(new Point(ty,tx));

    while(!points.isEmpty()){
      Point t = points.remove();

      int tempY = t.ty;
      int tempX = t.tx;

      temp[tempY][tempX] = count;

      for(int i=0;i<8;i++){

        int iy = tempY + y[i];
        int ix = tempX + x[i];

        if(maps[iy][ix] == 1 && temp[iy][ix] == 0){
          points.add(new Point(iy,ix));
        }
      }

    }

  }


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while(true) {
      int w = sc.nextInt(); // 행
      int h = sc.nextInt(); // 열

      if(w == 0 && h == 0){
        break;
      }

      final int[][] maps = new int[h][w];

      for(int i=0;i<h;i++){
        for(int j=0;j<w;j++){
          maps[i][j] = sc.nextInt();
        }
      }

      int count = 0;
      int[][] temp = new int[h][w];

      for(int i=0;i<h;i++){
        for(int j=0;j<w;j++){
          if(maps[i][j] == 1 && temp[i][j] == 0){
            count++;
          }

        }
      }

      System.out.println(count);
    }
  }

}
