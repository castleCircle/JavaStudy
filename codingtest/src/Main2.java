/**
 * 1부터 자연수를 이어쓰면 12345678910111213..가 됩니다. 이렇게 이어쓴 숫자를 A라 할떄, n 번째에 위치하는 숫자를 반환하는 함수
 *
 * n:5 , result:  5
 * n:15 , result:  2
 */
public class Main2 {

  public static void main(String[] args) {


//    System.out.println(solution(5));
//    x`System.out.println(solution(15));`
  }

  public static int solution(long n){
    long digits = 1; // 자릿수
    long base = 1; // 자릿수에 따른 숫자의 시작 값
    long count = 9; // 자릿수에 따른 숫자의 개수

    while (n > digits * count) {
      n -= digits * count;
      digits++;
      base *= 10;
      count *= 10;
    }

    // n번째 자릿수를 포함하는 숫자를 구합니다.
    long number = base + (n - 1) / digits;

    // n번째 자릿수를 구합니다.
    String s = Long.toString(number);
    int index = (int) ((n - 1) % digits);
    return Character.getNumericValue(s.charAt(index));
  }

}
