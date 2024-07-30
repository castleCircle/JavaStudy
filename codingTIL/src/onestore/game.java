package onestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class game {

  public static int[] solution(int n, int[] entry) {
    int[] gameRooms = new int[n];  // 게임방별 현재 사용자 수를 저장하는 배열
    List<Integer> result = new ArrayList<>();

    for (int e : entry) {
      if (e > 0) {
        // 입장 처리
        gameRooms[e - 1]++;
        result.add(e);
      } else {
        // 퇴장 처리
        int maxUsers = -1;
        List<Integer> maxRooms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          if (gameRooms[i] > maxUsers) {
            maxUsers = gameRooms[i];
            maxRooms.clear();
            maxRooms.add(i);
          } else if (gameRooms[i] == maxUsers) {
            maxRooms.add(i);
          }
        }
        if (!maxRooms.isEmpty()) {
          // 여러 방이 같은 최대 사용자 수를 가질 경우, 번호가 작은 방에서 퇴장
          Collections.sort(maxRooms);
          int maxRoom = maxRooms.get(0);
          gameRooms[maxRoom]--;
          result.add(maxRoom + 1);
        }
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }


  public static void main(String[] args) {
    int n1 = 2;
    int[] entry1 = {0, 0, 0, 1, 0, 0, 0};
    System.out.println(Arrays.toString(solution(n1, entry1)));  // [1, 1, 2, 2, 3, 3]

    int n2 = 3;
    int[] entry2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 1, 1, 2, 0, 0, 0};
    System.out.println(Arrays.toString(solution(n2, entry2)));  // [1, 2, 3, 4, 4, 3, 2, 1]
  }
}
