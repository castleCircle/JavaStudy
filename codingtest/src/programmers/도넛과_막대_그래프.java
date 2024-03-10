package programmers;

public class 도넛과_막대_그래프 {


  public static void main(String[] args) {
    System.out.println(solution(new int[][]{{2, 3}, {4, 3}, {1, 1}, {2, 1}}));
  }

  public static int[] solution(int[][] edges) {
    final int EDGES = 1_000_000;
    int[] ingoing = new int[EDGES];
    int[] outgoing = new int[EDGES];

    for (int[] row : edges) {
      outgoing[row[0] - 1]++;
      ingoing[row[1] - 1]++;
    }

    int created = 0;
    int stick = 0;
    int number8 = 0;

    for (int i = 0; i < edges.length; i++) {
      if (outgoing[i] >= 2){
        if(ingoing[i] == 0){
          created = i;
        }else{
          number8++;
        }

      }else if(ingoing[i] > 0 && outgoing[i] ==  0){
          stick++;
      }

    }

    return new int[]{created+1,outgoing[created] - stick - number8,stick,number8};
  }


}
