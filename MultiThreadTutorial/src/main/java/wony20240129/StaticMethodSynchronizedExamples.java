package wony20240129;

public class StaticMethodSynchronizedExamples {


  private static int count = 0;

  public static synchronized  void increment(){
    count++;
    System.out.println(Thread.currentThread().getName() + " 가 증가시켰습니다. 현재 값:" + count);
  }

  public static synchronized  void decrement(){
    count--;
    System.out.println(Thread.currentThread().getName() + " 가 감소시켰습니다. 현재 값:" + count);
  }

  public static int getCount(){
    return count;
  }

  public static void main(String[] args) throws InterruptedException {


    final Thread thread = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        StaticMethodSynchronizedExamples.increment();
      }
    });

    final Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        StaticMethodSynchronizedExamples.decrement();
      }
    });

    thread.start();
    thread1.start();

    thread.join();
    thread1.join();

    System.out.println("최종값은:" + StaticMethodSynchronizedExamples.getCount());
  }


}
