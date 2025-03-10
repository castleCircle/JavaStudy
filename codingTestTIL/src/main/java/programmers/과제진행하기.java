package programmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

class Node{
  String name;
  long startTime;
  long interval;

  public Node(String name, long startTime, long interval) {
    this.name = name;
    this.startTime = startTime;
    this.interval = interval;
  }
}

public class 과제진행하기 {

  public static String[] solution(String[][] plans) {

    List<Node> nodes = new ArrayList<>();
    List<String> result = new ArrayList<>();

    for(String[] temp: plans){
      final String name = temp[0];
      final String[] splitTime = temp[1].split(":");
      final String interval = temp[2];

      nodes.add(new Node(name,
          Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]),
          Integer.parseInt(interval)));
    }

    nodes.sort(Comparator.comparingLong(h -> h.startTime));
    Stack<Node> stack = new Stack<>();
    for(int i=0;i<nodes.size()-1;i++){
      Node node = nodes.get(i);
      long first = node.startTime;
      long second = nodes.get(i+1).startTime;

      long diff = second - first;

      if(diff < node.interval){
        stack.push(new Node(node.name,node.startTime,node.interval - diff));
      }else if(diff > node.interval){
        result.add(node.name);
        diff = diff - node.interval;
        while(diff > 0 &&!stack.empty()){
          final Node peek = stack.peek();
          if(peek.interval > diff){
            diff = 0;
          }else{
            result.add(peek.name);
            diff = diff - peek.interval;
            stack.pop();
          }
        }
//        while(diff > 0 &&!stack.empty()){
//          final programmers.Node peek = stack.pop();
//          if(peek.interval > diff){
//            stack.push(new programmers.Node(peek.name, peek.startTime,peek.interval - diff));
//            diff = 0;
//          }else if(diff >= peek.interval){
//            result.add(peek.name);
//            diff = diff - peek.interval;
//          }
//        }
      }else {
        result.add(node.name);
      }
    }

    result.add(nodes.get(nodes.size()-1).name);

    while(!stack.isEmpty()){
      final Node pop = stack.pop();
      result.add(pop.name);
    }

    return result.toArray(new String[result.size()]);
  }

  public static void main(String[] args) {
    String[][] schedule = {
        {"korean", "11:40", "30"},
        {"english", "12:10", "20"},
        {"math", "12:30", "40"}
    };

    System.out.println(solution(schedule));
  }

}
