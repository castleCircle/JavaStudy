package wony20240129;

public class InstanceStaticMethodSynchronizedExamples {

  private static int staticCount = 0;

  private int instanceCount = 0;

  public synchronized void incrementInstanceCount(){ //this가 모니터가 된다.
    instanceCount++;
    System.out.println(Thread.currentThread().getName() + " 가 증가시켰습니다. 현재 값:" + instanceCount);
  }

  public static synchronized void incrementStaticCount(){ //InstanceStaticMethodSynchronizedExamples가 모니터가 된다.
    staticCount++;
    System.out.println(Thread.currentThread().getName() + " 가 증가시켰습니다. 현재 값:" + staticCount);
  }

  public static void main(String[] args) throws InterruptedException {

    InstanceStaticMethodSynchronizedExamples examples = new InstanceStaticMethodSynchronizedExamples();


    final Thread thread = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        examples.incrementInstanceCount();
      }
    },"스레드1");

    final Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        examples.incrementInstanceCount();
      }
    },"스레드2");

    final Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        InstanceStaticMethodSynchronizedExamples.incrementStaticCount();
      }
    },"스레드3");

    final Thread thread3 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        InstanceStaticMethodSynchronizedExamples.incrementStaticCount();
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

    System.out.println("최종값은:" + examples.instanceCount);
    System.out.println("최종값은:" + staticCount);
  }
}
