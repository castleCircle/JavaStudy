package wony20240203;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {

  public static void main(String[] args) {

    Lock lock = new ReentrantLock();

    final Thread thread = new Thread(() -> {
      boolean acquired = false;
      while (!acquired) {
        acquired = lock.tryLock();
        if (acquired) {
          System.out.println("스레드 1이 락을 획득했습니다.");
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
          }finally {
            lock.unlock();
            System.out.println("스레드 1이 락을 해제 했습니다.");
          }
        } else {
          System.out.println("스레드 1이 락을 획득하지 못했음");
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            throw new RuntimeException();
          }
        }
      }
    });

    final Thread thread2 = new Thread(() -> {
      boolean acquired = false;
      while (!acquired) {
        acquired = lock.tryLock();
        if (acquired) {
          System.out.println("스레드 2이 락을 획득했습니다.");
          try{

          }finally {
            lock.unlock();
            System.out.println("스레드 2이 락을 해제 했습니다.");
          }
        } else {
          System.out.println("스레드 2이 락을 획득하지 못했음");
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            throw new RuntimeException();
          }
        }
      }
    });

    thread.start();
    thread2.start();

  }

}
