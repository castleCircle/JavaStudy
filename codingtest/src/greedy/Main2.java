package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main2 {

  public static void main(String[] args) {
    final int[] solution2 = solution(new int[]{4, 7, -5});

    for (int a : solution2){
      System.out.println(a);
    }

    final int[] solution = solution(new int[]{3,-3});
    for (int a : solution){
      System.out.println(a);
    }
    final int[] solution1 = solution(new int[]{8,4,-13});
    for (int a : solution1){
      System.out.println(a);
    }
  }

  public static int[] solution(int[] balls){

    final Stack<Integer> stack = new Stack<>();

    for(int ball : balls){
      if(stack.empty()){
        stack.push(ball);
        continue;
      }

      final int peek = stack.peek();
      if(Integer.signum(peek) == Integer.signum(ball)){
        stack.push(ball);
        continue;
      }else{

          final int pop = stack.pop();
          final int sum = ball + pop;

          if(sum == 0 || stack.isEmpty()){
            continue;
          }

          if(Integer.signum(sum) == Integer.signum(stack.peek())){
            stack.push(sum);
            continue;
          }

        stack.push(sum);

          while(true){

            if(stack.isEmpty() || stack.size() == 1){
              break;
            }

            final int subPop = stack.pop();

            if(Integer.signum(subPop) == Integer.signum(stack.peek())){
              stack.push(subPop);
              break;
            }

            final int subSum = subPop + stack.peek();
            final int subTop = stack.peek();


            if(Integer.signum(subSum) == Integer.signum(subTop)){
              break;
            }


            stack.pop();
            stack.push(subSum);

          }

      }
    }


    return Arrays.stream(stack.toArray()).mapToInt(o->(int)o).toArray();
  }

}
