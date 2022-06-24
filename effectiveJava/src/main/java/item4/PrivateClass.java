package item4;

public class PrivateClass {

  public static PrivateClass instance = new PrivateClass();

  private static Integer count = 0;

  private PrivateClass(){

    count++;

    if(count != 0){
       throw new IllegalStateException("tes");
    }

  }

}
