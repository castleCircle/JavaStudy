package wony20240203;

public class DeadlockOrderExample {

  private static final Object lock1 = new Object();
  private static final Object lock2 = new Object();

  public static void main(String[] args) {
    Thread thread1 = new Thread(()->{
      process(lock1,lock2);
    });

    Thread thread2 = new Thread(()->{
      process(lock2,lock1);
    });

    thread1.start();
    thread2.start();
  }

  private static void process(Object lockA,Object lockB){

    synchronized (lockA){
      System.out.println("LockA");
      synchronized (lockB){
        System.out.println("LockB");
      }
    }

  }

}
