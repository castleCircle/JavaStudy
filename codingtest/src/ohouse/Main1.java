package ohouse;

public class Main1 {

  public static void main(String[] args) {
    System.out.println(solution(new int[]{1,3,4,6},new int[]{2,3,4,5}));
  }

  public static int solution(int[] arr1,int[] arr2){
    int answer = -1;

    for(int tempArr1 : arr1){

      for(int tempArr2 : arr2){

        if(tempArr1 < tempArr2){
          break;
        }

        if(tempArr1 == tempArr2){
          return tempArr1;
        }

      }

    }

    return answer;
  }

}
