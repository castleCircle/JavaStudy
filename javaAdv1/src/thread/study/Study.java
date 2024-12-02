package thread.study;

import static java.lang.Thread.sleep;

public class Study {

  public static void main(String[] args) throws InterruptedException {
    CounterThread counterThread = new CounterThread(1,50);
    CounterThread counterThread1 = new CounterThread(51,100);

    counterThread.start();
    counterThread1.start();

    counterThread1.join();
    counterThread.join();

    System.out.println(counterThread1.result + counterThread.result);
  }

  public static class CounterThread extends Thread{

    int initValue;
    int endValue;
    int result = 0;

    public CounterThread(int initValue, int endValue) {
      this.initValue = initValue;
      this.endValue = endValue;
    }

    @Override
    public void run() {
      for(int i=initValue;i<=endValue;i++){
        result += i;
      }
    }
  }

}
