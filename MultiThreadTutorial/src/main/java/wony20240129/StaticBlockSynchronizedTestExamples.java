package wony20240129;

class MethodBlock1{}

public class StaticBlockSynchronizedTestExamples {

  private static int count = 0;

  public void incrementBlockClass(){
    synchronized (StaticBlockSynchronizedExamples.class){
      count++;
      System.out.println(Thread.currentThread().getName() + " 가 StaticBlockSynchronizedExamples 에 의해 블록 동기화 함: " + count);
    }
  }

  public  void incrementBlockOtherClass(){
    synchronized (StaticBlockSynchronizedExamples.class){
      count++;
      System.out.println(Thread.currentThread().getName() + " 가 MethodBlock 에 의해 블록 동기화 함: " + count);
    }
  }


  public static void main(String[] args) throws InterruptedException {

    StaticBlockSynchronizedTestExamples s = new StaticBlockSynchronizedTestExamples();

    final Thread thread = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        s.incrementBlockClass();
      }
    },"스레드1");

    final Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        s.incrementBlockOtherClass();
      }
    },"스레드2");

    thread.start();
    thread1.start();

    thread.join();
    thread1.join();

    //
    System.out.println("최종값음:" + count);
  }

}
