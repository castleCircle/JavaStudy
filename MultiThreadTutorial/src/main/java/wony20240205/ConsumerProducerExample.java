package wony20240205;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducerExample {

  private static int CAPACITY = 5;

  private Queue<Integer> queue = new LinkedList<>();

  private Lock lock = new ReentrantLock();

  private Condition notEmpty = lock.newCondition();
  private Condition notFull = lock.newCondition();

  public void produce() throws InterruptedException {
    int value = 0;
    while(true){
      lock.lock();
      try{
        while(queue.size() == CAPACITY){
          System.out.println("큐가 가득 차서 대기함");
          notFull.await();
        }
        queue.offer(value);
        System.out.println("생산: " + value + ", 큐 크기:" + queue.size());
        value++;
        notEmpty.signal();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
      Thread.sleep(2000);
    }
  }

  public void consume() throws InterruptedException {
    while(true){
      lock.lock();
      try{
        while(queue.isEmpty()){
          System.out.println("큐가 비어있음");
          notEmpty.await();
        }
        int value = queue.poll();
        System.out.println("소비: " + value + ", 큐 크기:" + queue.size());
        notFull.signal();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
      Thread.sleep(2000);
    }
  }

  public static void main(String[] args) {
    final ConsumerProducerExample consumerProducerExample = new ConsumerProducerExample();

    final Thread thread = new Thread(() -> {
      try {
        consumerProducerExample.produce();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    final Thread thread1 = new Thread(() -> {
      try {
        consumerProducerExample.consume();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    thread.start();
    thread1.start();

  }

}
