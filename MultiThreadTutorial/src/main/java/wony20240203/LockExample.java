package wony20240203;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

  private int count = 0;

  private Lock lock = new ReentrantLock();

  public void increment() {
    lock.lock();
    try {
      count++;
    } finally {
      lock.unlock();
    }
  }

  public int getCount() {
    lock.lock();
    try {
      return count;
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) throws InterruptedException {

    LockExample example = new LockExample();

    final Thread thread = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        example.increment();
      }
    });

    final Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        example.increment();
      }
    });

    thread.start();
    thread2.start();

    thread.join();
    thread2.join();

    System.out.println(example.getCount());

  }

}
