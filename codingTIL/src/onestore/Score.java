package onestore;

public class Score {

  public static void main(String[] args) {
    System.out.println(solution(new String[]{"AAFAFA","FEECAA","FABBCB","CBEDEE","CCCCCC"}));
    System.out.println(solution(new String[]{"BCD","ABB","FEE"}));
  }

  public static int solution(String[] scores) {
    int count = 0;
    for(String score : scores){
      if(isEligibleForPass(score)){
        count++;
      }
    }

    return count;
  }

  public static boolean isEligibleForPass(String input){
    int countA = 0;
    int countF = 0;
    int totalScore = 0;
    int minScore = Integer.MAX_VALUE;
    int maxScore = Integer.MIN_VALUE;
    int scoreCount = 0;

    for (char ch : input.toCharArray()) {
      int score;
      switch (ch) {
        case 'A':
          countA++;
          score = 5;
          break;
        case 'B':
          score = 4;
          break;
        case 'C':
          score = 3;
          break;
        case 'D':
          score = 2;
          break;
        case 'E':
          score = 1;
          break;
        case 'F':
          countF++;
          score = 0;
          break;
        default:
          continue;
      }

      if (countF >= 2) {
        return false;
      }

      totalScore += score;
      minScore = Math.min(minScore, score);
      maxScore = Math.max(maxScore, score);
      scoreCount++;
    }

    if (countA >= 2) {
      return true;
    }

    totalScore -= minScore + maxScore;
    return totalScore / (scoreCount - 2) >= 3;
  }



}
