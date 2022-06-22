package item2;

import item2.NutritionFacts.Builder;

public class Main {

  public static void main(String[] args) {
    final NutritionFacts build = new Builder().calories(1).build();
    System.out.println(build);
  }

}
