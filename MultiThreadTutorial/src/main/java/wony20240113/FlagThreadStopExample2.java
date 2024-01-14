package wony20240113;

import java.util.concurrent.atomic.AtomicBoolean;

public class FlagThreadStopExample2 {

  //메모리에서 바로 변경되는 값
  private AtomicBoolean running = new AtomicBoolean(true);

  public static void main(String[] args) {
    new FlagThreadStopExample2().flagTest();

  }

  private void flagTest(){

    new Thread(()->{
      int count = 0;
      while(running.get()){
        count++;
      }
      System.out.println("스레드 1 종료,count" + count);
    }).start();

    new Thread(()->{
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println("스레드 2 종료");
      running.set(false);
    }).start();

  }


}
