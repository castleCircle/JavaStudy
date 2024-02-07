package wony20240206;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {

  private static AtomicInteger counter = new AtomicInteger(0);
  private static int NUM_THREADS = 3;


  public static void main(String[] args) throws InterruptedException {
    Thread[] threads = new Thread[NUM_THREADS];
    for(int i=0;i<NUM_THREADS;i++){
      threads[i] = new Thread(()->{
        for(int j=0;j<100000;j++) {
          counter.incrementAndGet();
        }
      });
      threads[i].start();
    }

    for(Thread thread:threads){
      thread.join();
    }

    System.out.println(counter.get());

  }

}
