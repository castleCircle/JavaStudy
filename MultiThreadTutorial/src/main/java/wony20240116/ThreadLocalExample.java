package wony20240116;

public class ThreadLocalExample {

//  private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
  private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(()-> "Hello World");

  public static void main(String[] args) {

    new Thread(()->{
      System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
      threadLocal.set("스레드1의 값");
      System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
      threadLocal.remove();
      System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
    },"Thread-1").start();

    new Thread(()->{
      System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
      threadLocal.set("스레드2의 값");
      System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
    },"Thread-2").start();

  }

}
