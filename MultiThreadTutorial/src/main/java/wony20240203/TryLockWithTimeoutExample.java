package wony20240203;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockWithTimeoutExample {


  public static void main(String[] args) {

    Lock lock = new ReentrantLock();

    final Thread thread = new Thread(() -> {
      try {
        if (lock.tryLock(2, TimeUnit.SECONDS)) {
          System.out.println("스레드 1이 락을 획득했습니다.");
          try {
            Thread.sleep(4000);
          } catch (InterruptedException e) {
          }finally {
            lock.unlock();
            System.out.println("스레드 1이 락을 해제 했습니다.");
          }
        } else {
          System.out.println("스레드 1이 락을 획득하지 못했음");
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    final Thread thread2 = new Thread(() -> {
      try {
        if (lock.tryLock(2, TimeUnit.SECONDS)) {
          System.out.println("스레드 2이 락을 획득했습니다.");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
          }finally {
            lock.unlock();
            System.out.println("스레드 2이 락을 해제 했습니다.");
          }
        } else {
          System.out.println("스레드 2이 락을 획득하지 못했음");
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    thread.start();
    thread2.start();

  }

}
