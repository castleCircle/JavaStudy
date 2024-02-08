package wony20240208;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import wony20240208.CountDownLatchExample.Worker;


public class CyclicBarrierExample {

  static int[] parallelSum = new int[2];

  public static void main(String[] args) {
    int[] numbers = {1,2,3,4,5,6,7,8,9,10};
    int numThreads = 2;

    CyclicBarrier barrier = new CyclicBarrier(numThreads,new BarrierAction(parallelSum));

    for(int i=0;i<numThreads;i++){
      new Thread(new Worker1(i,numbers,barrier,parallelSum)).start();
    }

  }
}

class BarrierAction implements Runnable{

  private final int[] parallelSum;

  public BarrierAction(int[] parallelSum){
    this.parallelSum = parallelSum;
  }

  @Override
  public void run() {
    int finalSum = 0;
    for(int sum: parallelSum){{
      finalSum += sum;
    }}
    System.out.println("최종합계:" +finalSum);
  }
}

class Worker1 implements Runnable{

  private int id;
  private int[] numbers;
  private CyclicBarrier barrier;
  private int[] parallerSum;

  public Worker1(int id,int[] numbers,CyclicBarrier barrier,int[] parallerSum){
    this.id = id;
    this.numbers = numbers;
    this.barrier = barrier;
    this.parallerSum = parallerSum;
  }


  @Override
  public void run() {
    int start = id * (numbers.length / 2);
    int end = (id+1) * (numbers.length / 2);
    int sum = 0;
    for(int i=start;i<end;i++){
      sum+=numbers[i];
    }

    parallerSum[id] = sum;
    try {
      barrier.await();
      System.out.println("첫번째 모든 대기에서 풀려남");
      barrier.await();
      System.out.println("두번째 모든 대기에서 풀려남");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
  }
}