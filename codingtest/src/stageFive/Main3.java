/**
 * 플레어이가 게임 대기실에 들어온 순서와 플레이어의 등급에 따라 팀을 자동으로 편성해준다. 이때, 팀을 편성하는 기준은 아래와 같다.
 *
 * 1. 먼저 들어온 플레이어의 팀을 먼저 정합니다.
 * 2. 새로 들어온 플레이어는 새 팀에 배정하거나, 혹은 모든 팀원의 등급이 해당 플레이어의 등급보다 낮은 팀에만 배정할 수 있습니다(숫자가 작을수록 낮은 등급입니다).
 * 3. 한 번 정해진 팀은 바꿀수 없습니다.
 * 4. 팀 수는 적을수록 좋습니다.
 *
 * 위의 기준에 맞게 팀을 구성한 경우 , 총 몇 팀이 나오는지 구하려 한다.
 * 대기실 도착 순서에 따른 플레이어의 등급을 담은 배열 stats가 매개변수로 주어질때, 총 몇팀이 만들어 지는지 return하는 solution  함수를 완성해주세요
 *
 * 제한 사항
 * - stats는 플레이어의 등급이 대기실에 입장하는 순서대로 들어있는 배열입니다.
 * - 대기실에 입장하는 플레이어 수는 5명 이상 1,000 명 이하 입니다.
 * - 플레이어의 등급은 1이상 1,000 이하인 정수입니다.
 * - 모든 플레이어의 등급은 서로 다르다.
 *
 * 입출력 예시
 * stats : [5,3,4,6,2,1] , result : 4
 * stats : [6,2,3,4,1,5] , result : 3
 *
 * stats : [6,2,3,4,1,5] result : 3 이 나와야 하고
 * 예시는
 * 팀1: 등급이 [6]인 플레이어
 * 팀2: 등급이 [2,3,4]인 플레이어
 * 팀3: 등급이 [1,5]인 플레이어 로 팀을 구성하면 최소 3팀을 구성할수 있다
 */

package stageFive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main3 {

  public static void main(String[] args) {
    System.out.println(solution(new int[]{5,3,4,6,2,1}));
    System.out.println(solution(new int[]{6,2,3,4,1,5}));
  }

  public static int solution(int[] stats) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    int teamCount = 0;

    for (int i = 0; i < stats.length; i++) {
      int player = i + 1;
      int rank = stats[i];
      boolean assigned = false;

      for (int j = 1; j <= teamCount; j++) {
        List<Integer> team = map.get(j);

        if (team.get(team.size() - 1) < rank) {
          team.add(rank);
          assigned = true;
          break;
        }
      }

      if (!assigned) {
        teamCount++;
        List<Integer> team = new ArrayList<>();
        team.add(rank);
        map.put(teamCount, team);
      }
    }

    return teamCount;
  }

}
