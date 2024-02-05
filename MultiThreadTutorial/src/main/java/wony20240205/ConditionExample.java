package wony20240205;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {

  private Lock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();

  private boolean flag = false;

  public void awaiting() throws InterruptedException {
    lock.lock();
    try{
      while(!flag){
        System.out.println("조건이 만족 못해 대기");
        condition.await();
      }
      System.out.println("임계 영역 수행");
    }finally {
      lock.unlock();
    }
  }

  public void signaling(){
     lock.lock();
     try{
       flag = true;
       System.out.println("조건을 만족시키고 깨움");
       condition.signal();
     }finally {
       lock.unlock();
     }
  }

  public static void main(String[] args) throws InterruptedException {

    ConditionExample conditionExample = new ConditionExample();

    final Thread thread = new Thread((() -> {
      try {
        conditionExample.awaiting();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }));

    final Thread thread1 = new Thread((() -> {
        conditionExample.signaling();
    }));

    thread.start();
    thread.sleep(10000);
    thread1.start();

    thread.join();
    thread1.join();


  }

}
