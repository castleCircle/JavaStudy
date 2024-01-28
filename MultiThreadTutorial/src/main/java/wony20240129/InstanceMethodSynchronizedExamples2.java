package wony20240129;

public class InstanceMethodSynchronizedExamples2 {


  private int count = 0;

  public synchronized void increment(){
    count++;
    System.out.println(Thread.currentThread().getName() + " 가 증가시켰습니다. 현재 값:" + count);
  }

  public synchronized void decrement(){
    count--;
    System.out.println(Thread.currentThread().getName() + " 가 감소시켰습니다. 현재 값:" + count);
  }

  public int getCount(){
    return count;
  }

  public static void main(String[] args) throws InterruptedException {


    InstanceMethodSynchronizedExamples2 counter1 = new InstanceMethodSynchronizedExamples2();
    InstanceMethodSynchronizedExamples2 counter2 = new InstanceMethodSynchronizedExamples2();

    final Thread thread = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        counter1.increment();
        counter2.decrement();
      }
    });

    final Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        counter1.decrement();
        counter2.increment();
      }
    });

    thread.start();
    thread1.start();

    thread.join();
    thread1.join();

    System.out.println("최종값은:" + counter1.getCount());
    System.out.println("최종값은:" + counter2.getCount());
  }

}
