package ohouse;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    System.out.println(solution(new String[]{"2", "3", "D"}));
  }

  public static int solution(String[] rolls){

    List<String> answer = new ArrayList<>();

    for(String roll : rolls){
      if(roll.equals("+")){
        answer.add(Integer.parseInt(answer.get(answer.size()-1)) + Integer.parseInt(answer.get(answer.size()-2)) + "");
      }else if(roll.equals("D")) {
        answer.add(Integer.parseInt(answer.get(answer.size()-1)) * 2 + "");
      }else if(roll.equals("R")){
        answer.remove(answer.size()-1);
      }else{
        answer.add(roll);
      }
    }

    return answer.stream().mapToInt(Integer::parseInt).sum();
  }

}
