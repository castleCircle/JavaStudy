package thread.control.printer;

import static util.ThreadUtils.sleep;

import thread.start.HelloRunnable;

public class YieldMain {

  static final int THREAD_COUNT = 1000;

  public static void main(String[] args) {
    for(int i=0;i<THREAD_COUNT;i++){
      Thread thread = new Thread(new MyRunnable());
      thread.start();
    }
  }

  static class MyRunnable implements  Runnable{

    @Override
    public void run() {

      for(int i=0;i<10;i++){
        System.out.println(Thread.currentThread().getName()+ " - " + i);
//        sleep(1);
        Thread.yield(); // runnable 상태로 실행대기큐에 들어가 있다.
      }

    }
  }

}
