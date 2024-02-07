package wony20240208;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

  public static void main(String[] args) throws InterruptedException {
    int numThreads = 5;
    CountDownLatch startSignal = new CountDownLatch(1);
    CountDownLatch doneSignal = new CountDownLatch(5);

    for(int i=0;i<numThreads;i++){
      new Thread(new Worker(startSignal,doneSignal)).start();
    }

    startSignal.countDown();// 이 코드가 빠지면 전체 작업이 멈춘다. ㅋㅋ
    System.out.println("시작신호를 알렸습니다.");
    doneSignal.await();
    System.out.println("메인 스레드 작업 시작");
  }

  static class Worker implements Runnable{

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    public Worker(CountDownLatch startSignal,CountDownLatch doneSignal){
      this.startSignal = startSignal;
      this.doneSignal = doneSignal;
    }

    @Override
    public void run() {

      try {
        startSignal.await();
        System.out.println(Thread.currentThread().getName() + " 가 작업을 수행하고 있습니다.");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " 가 작업을 완료함");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }finally {
        doneSignal.countDown();
      }

    }
  }

}
