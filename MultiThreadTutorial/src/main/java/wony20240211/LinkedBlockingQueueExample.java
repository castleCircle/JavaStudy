package wony20240211;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {

  public static void main(String[] args) {

    LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

    Thread producer = new Thread(()->{
      try{
        for(int i=1;i<=10;i++){
          System.out.println("Producing: " + i);
          queue.put(i); //데이터를 큐에넣음(큐가 가득차면 블록됨)
          Thread.sleep(10000);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });

    Thread consumer = new Thread(()->{
      try{
        for(int i=1;i<=10;i++){
          final int data = queue.take();
          System.out.println("consumer: " + data);
          Thread.sleep(100);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });

    producer.start();
    consumer.start();

  }

}
