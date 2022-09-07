package inflearn.item1;

public class Settings {

  private boolean useAutoSteering;

  private boolean useABS;

  private Difficulty difficulty;

  private Settings(){}

  private static final Settings SETTINGS = new Settings();

  public static Settings newInstance(){
    return SETTINGS;
  }

  public static void main(String[] args) {
    System.out.println(Settings.newInstance());
    System.out.println(Settings.newInstance());

    Boolean.valueOf(true);
  }


}
