public class Main {

  public static void main(String[] args) {
    System.out.println(solution(5));
    System.out.println(solution(15));
  }

  public static int solution(int n){
    int start = 1;
    int length = 1;
    long count = 9;

    while (n > length * count) {
      n -= length * count;
      length++;
      start *= 10;
      count *= 10;
    }

    start += (n - 1) / length;
    String number = Integer.toString(start);
    return Character.getNumericValue(number.charAt((n - 1) % length));
  }

}
