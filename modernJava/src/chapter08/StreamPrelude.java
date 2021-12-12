package chapter08;

public class StreamPrelude {

  public static void main(String[] args) {
    final int abs1 = Math.abs(-1);
    final int abs2 = Math.abs(1);

    System.out.println("ab1 == abs2 is " + (abs1 == abs2));

    final int minInt = Math.abs(Integer.MIN_VALUE);
    System.out.println("minInt: " + minInt);

  }

}
