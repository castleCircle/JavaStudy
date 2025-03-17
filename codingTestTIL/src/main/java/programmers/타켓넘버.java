package programmers;

public class 타켓넘버 {

  public static void main(String[] args) {
    System.out.println(solution(new int[]{1,1,1,1,1},3));
  }

  static int count = 0;

  public static int solution(int[] numbers, int target) {
    number(numbers,0,0,target);
    return count;
  }

  public static void number(int[] numbers,int position,int sum,int target){
    if(numbers.length == position){
      if(sum == target) {
        count++;
      }
      return;
    }

    number(numbers,position+1,sum + numbers[position],target);
    number(numbers,position+1,sum - numbers[position],target);
  }

}
