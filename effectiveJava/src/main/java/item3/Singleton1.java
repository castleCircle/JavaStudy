package item3;


public class Singleton1 {

  public static final Singleton1 singleton = new Singleton1();

  int count;

  private Singleton1(){
    System.out.println("count" + count);
    count++;
    if(count!=1){
      throw new IllegalStateException("this object singleton");
    }
  }

}
