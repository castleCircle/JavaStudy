package wony20240129;

public class InstanceBlockSynchronizedExamples {

  private int count = 0;

  private Object lockObject = new Object();

  public void incrementBlockThis(){
//    synchronized (lockObject){
    synchronized (this){
      count++;
      System.out.println(Thread.currentThread().getName() + " 가 This 에 의해 블록 동기화 함: " + count);
    }
  }

  public void incrementBlockLockObject(){
    synchronized (lockObject){
      count++;
      System.out.println(Thread.currentThread().getName() + " 가 LockObject 에 의해 블록 동기화 함: " + count);
    }
  }

  public int getCount(){
    return count;
  }


  public static void main(String[] args) throws InterruptedException {

    InstanceBlockSynchronizedExamples examples = new InstanceBlockSynchronizedExamples();


    final Thread thread = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        examples.incrementBlockThis();
      }
    },"스레드1");

    final Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        examples.incrementBlockThis();
      }
    },"스레드2");

    final Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        examples.incrementBlockLockObject();
      }
    },"스레드3");

    final Thread thread3 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        examples.incrementBlockLockObject();
      }
    },"스레드4");

    thread.start();
    thread1.start();
    thread2.start();
    thread3.start();

    thread.join();
    thread1.join();
    thread2.join();
    thread3.join();

    System.out.println("초종값음:" + examples.getCount());
  }
}
