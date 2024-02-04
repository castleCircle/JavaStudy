package wony20240203;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {


  public static void main(String[] args) {

    Lock lock = new ReentrantLock();

    final Thread thread = new Thread(() -> {
      try {
        lock.lockInterruptibly();
          try {
            System.out.println("스레드 1이 락을 획득했습니다.");
          }finally {
            lock.unlock();
            System.out.println("스레드 1이 락을 해제 했습니다.");
          }
      } catch (InterruptedException e) {
        System.out.println("스레드 1이 인터럽트를 받았습니다.");
      }
    });


    final Thread thread2 = new Thread(() -> {
      try {
        lock.lockInterruptibly();
        try {
          System.out.println("스레드 2이 락을 획득했습니다.");
        }finally {
          lock.unlock();
          System.out.println("스레드 2이 락을 해제 했습니다.");
        }
      } catch (InterruptedException e) {
        System.out.println("스레드 2이 인터럽트를 받았습니다.");
      }
    });

    thread.start();
    thread2.start();

    thread.interrupt();
    thread2.interrupt();

    try {
      thread.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


  }


}
