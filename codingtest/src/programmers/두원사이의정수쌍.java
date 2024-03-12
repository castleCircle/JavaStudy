package programmers;

public class 두원사이의정수쌍 {

  public static void main(String[] args) {
    System.out.println(solution(2,3));
  }

  public static long solution(int r1, int r2) {
    long answer = 0;

    for(int i=1;i<=r2;i++){
      long minY = (long)Math.ceil(Math.sqrt(Math.pow(r1,2) - Math.pow(i,2)));
      long maxY = (long)Math.floor(Math.sqrt(Math.pow(r2,2) - Math.pow(i,2)));
      answer+=(maxY - minY+ 1);
    }

    return answer * 4;
  }

}
