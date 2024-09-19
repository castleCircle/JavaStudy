package thread.cas.spinlock;

import static util.MyLogger.log;

public class SpinLockMain {

  public static void main(String[] args) {
//    SpinLockBad spinLock = new SpinLockBad();
    SpinLock spinLock = new SpinLock();

    final Runnable task = new Runnable() {
      @Override
      public void run() {
        spinLock.lock();
        try {
          //critical section
          log("비지니스 로직 실행");
        } finally {
          spinLock.unlock();
        }
      }
    };

    final Thread thread1 = new Thread(task, "Thread-1");
    final Thread thread2 = new Thread(task, "Thread-2");

    thread1.start();
    thread2.start();

  }

}
