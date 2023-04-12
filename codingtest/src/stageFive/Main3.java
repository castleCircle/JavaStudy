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

  private static class Player {
    private final int rating;

    public Player(int rating) {
      this.rating = rating;
    }

    public int getRating() {
      return rating;
    }
  }

  private static class Team {
    private final List<Player> players;

    public Team(int initialRating) {
      this.players = new ArrayList<>();
      this.players.add(new Player(initialRating));
    }

    public int getTeamSize() {
      return players.size();
    }

    public boolean canAddPlayer(Player player) {
      for (Player teamPlayer : players) {
        if (teamPlayer.getRating() <= player.getRating()) {
          return false;
        }
      }
      return true;
    }

    public void addPlayer(Player player) {
      players.add(player);
    }
  }

}
