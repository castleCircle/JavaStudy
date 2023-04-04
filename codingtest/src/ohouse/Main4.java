package ohouse;

public class Main4 {

  public static void main(String[] args) {
    System.out.println(solution(new int[]{9,10,6,0,4,6,10}));
    System.out.println(solution(new int[]{6,10,3,9,4,10,3,9,3,10,6}));
    System.out.println(solution(new int[]{5,3,1,3,6,4}));
    System.out.println(solution(new int[]{9,9,9,9}));
  }

  public static int solution(int[] happiness){
    int answer = 0;

    int[] d = new int[happiness.length];

    for(int i=0;i<happiness.length;i++){
      for(int j=0;j<=i;j++){
        int gap = happiness[j] > 8 ? 1 : -1;
        if(d[i] + gap >= 0){
          d[i] = d[j] + gap;
        }
      }
    }

    for(int i=0;i<happiness.length;i++){
      if(d[i] > answer){
        answer = d[i];
      }
    }

    return answer;
  }

  public static int count(int[] happiness,int index,int count,int days){

    if(index >= happiness.length){
      return count > 1 ? days : days-1;
    }

    if(days > 1 && count==0 && happiness[index] <=8){
      return days-1;
    }

    if(happiness[index] > 8){
      return count(happiness,index+1,++count,++days);
    }else{
      return count(happiness,index+1,--count,++days);
    }

  }

}
