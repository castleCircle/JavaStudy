package wony20240129;

public class InstanceBlockSynchronizedExamples2 {


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

    //2개의 모니터를 가짐
    InstanceBlockSynchronizedExamples2 examples1 = new InstanceBlockSynchronizedExamples2();

    //2개의 모니터를 가짐
    InstanceBlockSynchronizedExamples2 examples2 = new InstanceBlockSynchronizedExamples2();

//    examples1 ,examples2 합쳐서 총 4개의 모니터를 가지게 됨


    final Thread thread = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        examples1.incrementBlockThis();
      }
    },"스레드1");

    final Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        examples2.incrementBlockThis();
      }
    },"스레드2");

    final Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        examples1.incrementBlockLockObject();
      }
    },"스레드3");

    final Thread thread3 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        examples2.incrementBlockLockObject();
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

    //
    System.out.println("최종값음:" + examples1.getCount());
    System.out.println("최종값음:" + examples2.getCount());
  }

}
