package programmers;

import java.util.ArrayList;

public class 수식_최대화 {

  public static void main(String[] args) {
    System.out.println(solution("100-200*300-500+20"));
    System.out.println(solution("50*6-3*2"));
  }

  public static long solution(String expression) {

    long answer = Long.MIN_VALUE;
    String op[][] = { { "+", "-", "*" }, { "+", "*", "-" }, { "-", "*", "+" },
        { "-", "+", "*" }, { "*", "-", "+" }, { "*", "+", "-" } };

    ArrayList<String> list = new ArrayList<String>();
    int start = 0;
    for (int i = 0; i < expression.length(); i++) {
      if (expression.charAt(i) == '-' || expression.charAt(i) == '+' || expression.charAt(i) == '*') {
        list.add(expression.substring(start, i)); // 연산자 앞 숫자 추가
        list.add(expression.charAt(i) + ""); // 연산자 추가
        start = i + 1;
      }
    }
    list.add(expression.substring(start)); // 마지막 숫자 추가

    for(int i=0;i<op.length;i++){

      ArrayList<String> subList = new ArrayList<>(list);

      for(int k=0;k<3;k++){
        for(int j=0;j<subList.size();j++){
          if(op[i][k].equals(subList.get(j))){
            subList.set(j-1,calc(subList.get(j-1),subList.get(j),subList.get(j+1)));
            subList.remove(j);
            subList.remove(j);
            j--;
          }
        }
      }

      answer = Math.max(answer,Math.abs(Long.parseLong(subList.get(0))));
    }

    return answer;
  }

  public static String calc(String num1,String op,String num2){

    long n1 = Long.parseLong(num1);
    long n2 = Long.parseLong(num2);

    if(op.equals("+")){
      return n1 + n2 + "";
    }else if(op.equals("-")){
      return n1 - n2 + "";
    }

    return n1 * n2 + "";
  }

}
