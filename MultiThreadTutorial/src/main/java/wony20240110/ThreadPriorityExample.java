package wony20240110;

public class ThreadPriorityExample {

  public static void main(String[] args) throws InterruptedException {
    final CountingThread highThread = new CountingThread("우선 순위가 높은 스레드", Thread.MAX_PRIORITY);
    final CountingThread normThread = new CountingThread("우선 norm가 높은 스레드", Thread.NORM_PRIORITY);
    final CountingThread minThread = new CountingThread("우선 min가 높은 스레드", Thread.MIN_PRIORITY);

    highThread.start();
    normThread.start();
    minThread.start();

    highThread.join();
    normThread.join();
    minThread.join();

    System.out.println("작업 종료");
  }

  static class CountingThread extends Thread{

    private final String threadName;

    private int count = 0;

    public CountingThread(String threadName,int priority){
      this.threadName = threadName;
      setPriority(priority);
    }

    @Override
    public void run() {
      while(count < 1000000000){
        count++;
      }
      System.out.println(threadName + ": " +count);
    }
  }

}
