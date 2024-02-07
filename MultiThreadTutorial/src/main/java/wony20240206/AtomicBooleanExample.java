package wony20240206;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {

  private static AtomicBoolean flag = new AtomicBoolean(false);

  public static void main(String[] args) {

    final Thread thread = new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        while (!flag.compareAndSet(false, true)) {
          System.out.println("스레드 1이 바쁜 대기중...." + flag.get());
        }
        System.out.println("스레드 1이 임계영역 수행중..");
        flag.set(false);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    final Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        while (!flag.compareAndSet(false, true)) {
          System.out.println("스레드 2이 바쁜 대기중...." + flag.get());
        }
        System.out.println("스레드 2이 임계영역 수행중..");
        flag.set(false);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });


    thread.start();
    thread2.start();

  }



}
