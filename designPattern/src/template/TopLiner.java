package template;

public abstract class TopLiner {

  public void flashing(){
    System.out.println("점멸");
  }

  public void warp(){
    System.out.println("순간이동!");
  }

  public abstract void skillQ();

  public abstract void skillR();

}
