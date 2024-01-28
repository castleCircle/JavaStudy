package wony20240129;

public class InstanceMethodSynchronizedExamples {

  private int count = 0;

  public synchronized  void increment(){
    count++;
    System.out.println(Thread.currentThread().getName() + " 가 증가시켰습니다. 현재 값:" + count);
  }

  public synchronized  void decrement(){
    count--;
    System.out.println(Thread.currentThread().getName() + " 가 감소시켰습니다. 현재 값:" + count);
  }

  public int getCount(){
    return count;
  }

  public static void main(String[] args) throws InterruptedException {

    InstanceMethodSynchronizedExamples examples = new InstanceMethodSynchronizedExamples();

    final Thread thread = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        examples.increment();
      }
    });

    final Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        examples.decrement();
      }
    });

    thread.start();
    thread1.start();

    thread.join();
    thread1.join();

    System.out.println("최종값은:" + examples.getCount());
  }

}
