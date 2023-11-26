package dearU;

public class TestA {

  TestB b = new TestB();

  public void run(String name){
//    synchronized (b) {
//      System.out.println(name + " lock {stasrt}");
      b.test(name);
    System.out.println("count [" + b.count + "]");
//      System.out.println(name + " lock {end]");
//    }
  }

  public void run1(String name){
    synchronized (b) {
//    System.out.println(name + " lock1 !!!stasrt!!!");
      b.test1(name);
//    System.out.println(name + " lock1 !!!end!!!");
      System.out.println("count1 [" + b.count1 + "]");
    }
  }

  public int getCount(){
    return b.count;
  }
  public int getCount1(){
    return b.count1;
  }

}
