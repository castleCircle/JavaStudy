package item2;


import lombok.ToString;

@ToString
public class NutritionFacts {

  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;

  public static class Builder{

    private int calories;
    private int fat;
    private int sodium;
    private int carbohydrate;

    public Builder calories(int val){
      calories = val;
      return this;
    }

    public Builder fat(int val){
      fat = val;
      return this;
    }

    public Builder sodium(int val){
      sodium = val;
      return this;
    }

    public Builder carbohydrate(int val){
      carbohydrate = val;
      return this;
    }

    public NutritionFacts build(){
      return new NutritionFacts(this);
    }
  }

  private NutritionFacts(Builder builder){
    calories = builder.calories;
    fat = builder.fat;
    sodium = builder.sodium;
    carbohydrate = builder.carbohydrate;
  }

}
