package wony20240208;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierTest {

  private static int[] count = new int[2];

  public static void main(String[] args) {

    CyclicBarrier barrier = new CyclicBarrier(2,()->{
      for(int n : count){
        System.out.println("가보자");
        System.out.println(n);
      }
    });

    for(int i=0;i<2;i++){
      new Thread(new TestBarrier(i,count,barrier)).start();
    }

  }


}

class TestBarrier implements Runnable{

  private int tempCount;
  private int[] temp;
  private CyclicBarrier cyclicBarrier;

  public TestBarrier(int count,int[] temp,CyclicBarrier cyclicBarrier){
    this.tempCount = count;
    this.temp = temp;
    this.cyclicBarrier = cyclicBarrier;
  }

  @Override
  public void run() {
    System.out.println("count:" +tempCount);
    temp[tempCount] = tempCount;
    try {
      cyclicBarrier.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
  }
}
