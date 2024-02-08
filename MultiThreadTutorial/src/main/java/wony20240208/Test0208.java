package wony20240208;

import java.util.LinkedList;
import java.util.Queue;

public class Test0208 {

  private static Queue<Runnable> test = new LinkedList<>();

  public static void main(String[] args) throws InterruptedException {

    final Thread thread = new Thread(() -> {
      try {
        System.out.println(Thread.currentThread().getName() + "start");
        synchronized (test) {
          test.wait();
        }
        System.out.println(Thread.currentThread().getName() + "end");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    final Thread thread1 = new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + "start");
        System.out.println(Thread.currentThread().getName() + "end");
    });


    final Thread thread2 = new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + "start");
        System.out.println(Thread.currentThread().getName() + "end");
    });

    thread.start();
    thread1.start();
    thread2.start();

    thread.join();
    thread1.join();
    thread2.join();

    System.out.println("end");


  }


}
