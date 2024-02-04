package wony20240203;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStateExample {

  private static final Lock lock = new ReentrantLock();

  public static void main(String[] args) {
    new Thread(()->{
      lock.lock();
      try{
        System.out.println("스레드가 락을 1번 획득했습니다.");
        lock.lock();
        try{
          System.out.println("스레드가 락을 2번 획득했습니다.");
          lock.lock();
          try{
            System.out.println("스레드가 락을 3번 획득했습니다.");
          }finally {
            lock.unlock();
            System.out.println("스레드가 락을 1번 해제했습니다.");
          }
        }finally {
          lock.unlock();
          System.out.println("스레드가 락을 2번 해제했습니다.");
        }
      }finally {
//        lock.unlock();
        lock.unlock(); // 주석해버리면 thread2는 lock이 걸려서 무한 대기.. 왜냐하면 lock을 한 갯수만큼 풀어줘야지 다른 스레드에서는 시작 가능
        System.out.println("스레드 락을 3번 해제했습니다.");
      }
    }).start();

    new Thread(()->{
      lock.lock();
      try{
        System.out.println("스레드 2가 락을 획득");
      }finally {
        lock.unlock();
        System.out.println("스레드 2가 락을 해제");
      }
    }).start();
  }


}
