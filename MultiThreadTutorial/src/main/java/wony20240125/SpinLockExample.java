package wony20240125;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockExample {

  private AtomicBoolean lock = new AtomicBoolean(false);

  public void lock(){
    while(!lock.compareAndSet(false,true));
  }

  public void unlock(){
    lock.set(false);
  }

  public static void main(String[] args) {
    SpinLockExample spinLockExample = new SpinLockExample();

    Runnable task = () ->{
      spinLockExample.lock();
      System.out.println(Thread.currentThread().getName() + " 가 락을 획득했습니다.");
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }finally {
        System.out.println(Thread.currentThread().getName() + "가 락을 해제 합니다.");
        spinLockExample.unlock();
        System.out.println(Thread.currentThread().getName() + "가 락을 해제 했슴돵.");
      }
    };

    Thread thread = new Thread(task);
    Thread thread1 = new Thread(task);

    thread.start();
    thread1.start();
  }

}
