package sample.tddstartproject;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String result = sc.nextLine();
    final String[] parts = result.split(" ");
    long num1 = Long.parseLong(parts[0]);
    long num2 = Long.parseLong(parts[2]);
    String operator = parts[1];
    long answer = 0;
    switch (operator){
      case "+":
        answer = num1 + num2;
        break;
      case "-":
        answer = num1 - num2;
        break;
      case "*":
        answer = num1 * num2;
        break;
      case "/":
        answer = num1 / num2;
        break;
      default:
        throw new InvalidOperationException();
    }

    System.out.println(answer);
  }
}