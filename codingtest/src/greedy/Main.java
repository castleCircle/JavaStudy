package greedy;

public class Main{

  static int[][] map;

  public static void main(String[] args) {
    System.out.println(solution(new int[]{10,17},new int[]{5,20}));
    System.out.println(solution(new int[]{10,20},new int[]{5,17}));
//    System.out.println(solution(100,97,new int[][]{{1,1,100,97}}));
  }

  public static int gcd(int a,int b){
    if(a%b == 0) return b;
    return gcd(b,a%b);
  }

  public static int solution(int[] arrayA, int[] arrayB) {
    int answer = 0;
    int gcdA = arrayA[0];
    int gcdB = arrayB[0];

    for(int i=1;i<arrayA.length;i++){
      gcdA = gcd(gcdA,arrayA[i]);
      gcdB = gcd(gcdB,arrayB[i]);
    }

    if(notDivisible(arrayB,gcdA)){

      if(gcdA != 1){
        return gcdA;
      }
    }

    if(notDivisible(arrayA,gcdB)){

      if(gcdB != 1){
        return gcdB;
      }
    }


    return answer;
  }

  public static boolean notDivisible(int[] arr,int num){

    for(int n: arr){
      if(n%num == 0){
        return false;
      }
    }


    return true;
  }

}


