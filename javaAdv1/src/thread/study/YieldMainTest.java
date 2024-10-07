package thread.study;

import static util.ThreadUtils.sleep;

public class YieldMainTest {

  public static void main(String[] args) {
    for(int i=0;i<=1000;i++){
      Thread thread = new Thread(new MyRunnableYield());
      thread.start();
    }
  }

  static class MyRunnableYield implements Runnable {

    @Override
    public void run() {
      for(int i=0;i<=10;i++){
        System.out.println(Thread.currentThread().getName()+":"+i);
        Thread.yield();
      }
    }
  }
}
