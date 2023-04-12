/**
 * N으로 속도차가 남
 */
public class Main {

  public static void main(String[] args) {
    System.out.println(solution(5));
    System.out.println(solution(15));
  }

  public static int solution(int n){
    int i = 1; // 자연수의 시작값
    int sum = 0; // 자연수의 합

    // 자연수의 합이 n 이상이 될 때까지 반복
    while (sum < n) {
      sum += Integer.toString(i).length(); // 자연수를 문자열로 변환하여 자릿수만큼 더함
      i++;
    }

    // 마지막 자연수의 자릿수 중 n번째 숫자를 찾음
    String s = Integer.toString(i - 1);
    int numDigits = s.length();
    int digitIndex = n - (sum - numDigits);
    return Character.getNumericValue(s.charAt(digitIndex - 1));
  }

}
