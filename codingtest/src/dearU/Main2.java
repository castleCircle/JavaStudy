/**
 * 보험 항목별 보장 금액이 순서대로 들어있는 배열이 있습니다. 주어진 항목 중에서 K개 이상의 항목을 선택하여 새 보험 상품을 만들되, 새  보험 상품에 포함되는 각 항목의 보장 금액의 합이 t 이하가 되도력 하려고 합니다. 다음은 항목별 보장 금액이 들어있는 배열[2,5,3,8,1], k=3 , t=11이 주어진 경우의 예시입니다.
 * 먼저 주어진 배열은 5개의 보험 항목의 보장 금액이 순서대로[2,5,3,8,1]임을 나타냅니다. 이때 3개 이상의 항목을 선택하는 방법은 총 16가지가 있는데 , 이중 , 선택된 항목들의 보장 금액의 합이 11이하가 되는 경우는 다음과 같이 6가지가 있습니다.
 * [2,5,3] , [2,5,1] , [5,3,1] , [2,3,1] , [2,8,1] , [2,5,3,1]
 * 예를들어 선택한 항목의 보장 금액이 [2,5,3,1] 인 경우 보장 금액의 합은 2 + 5 + 3 + 1 = 11 이므로 11 이하가 됩니다. 그러나 만약 선택한 항목의 보장 금액이 [5,3,8]인 경우 보장 금액의 합은 5+3+8 = 16이므로 이 경우는 불가능한 방법이 됩니다.
 * 항목별 보장 금액이 순서대로 들어있는 배열 arr와 k,t가 매개변수로 주어질 때 , arr에서 k개 이ㅣ상의 항목을 선택했을때 , 금액 합이 t 이하가 되도록 하는 경우의 수를 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * - arr는 각 보험 항목별 금액이 순서대로 들어있는 배열이며, 길이는 1이상 15 이하 입니다.
 * - arr의 각 원소는 1이상 100,000 이하의 자연수 입니다.
 * - k는 1 이상 15이하의 자연수이며, 항상 arr의 길이 이하인 경우만 주어집니다.
 * - t는 1이상 1,000,000 이하의 자연수입니다.
 *
 * 예시
 * arr = [2,5,3,8,1]이고 k =3, t = 11인 경우에는 6을 반환하게 됩니다.
 * arr = [1,1,2,2]이고 k =2, t = 3인 경우에는 5을 반환하게 됩니다.
 * arr = [1,2,3,2]이고 k =2, t = 2인 경우에는 0을 반환하게 됩니다.
 */

package dearU;

public class Main2 {

  public static void main(String[] args) {
//    System.out.println(3/4);
//    System.out.println(solution(new int[]{2,5,3,8,1},3,11));
    System.out.println(solution(new int[]{1,1,2,2},2,3));
//    System.out.println(solution(new int[]{1,2,3,2},2,2));
  }

  static int answer = 0;

  public static int solution(int[] arr, int k, int t) {
    int n = arr.length;
    int answer = 0;

    for(int i=0; i<Math.pow(2, n); i++){ // 모든 부분집합에 대해서
      int sum = 0;
      int cnt = 0;
      for(int j=0; j<n; j++){ // 현재 부분집합에 속한 항목들의 보장 금액의 합을 계산
        if((i / (int)Math.pow(2, j)) % 2 == 1){
          System.out.println(String.format("i:%s,j:%s",i,j));
          sum += arr[j];
          cnt++;
        }
      }
      if(cnt >= k && sum <= t) answer++; // 조건을 만족하면 answer 증가
    }
    return answer;
  }

}
