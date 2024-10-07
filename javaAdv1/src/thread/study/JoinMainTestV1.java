package thread.study;

import static util.ThreadUtils.sleep;

public class JoinMainTestV1 {

  public static void main(String[] args) throws InterruptedException {
    final JoainMainRunnable joainMainRunnable = new JoainMainRunnable(1, 50);
    final JoainMainRunnable joainMainRunnable2 = new JoainMainRunnable(51, 100);
    final Thread thread = new Thread(joainMainRunnable);
    final Thread thread1 = new Thread(joainMainRunnable2);

    thread.start();
    thread1.start();

//    thread.join();
//    thread1.join();

    while(thread1.getState() != Thread.State.TERMINATED && thread.getState() != Thread.State.TERMINATED) {

    }

    sleep(1000);

    System.out.println(joainMainRunnable2.sum + joainMainRunnable.sum);
  }

  public static class JoainMainRunnable implements Runnable {

    private int start;
    private int end;
    int sum;

    public JoainMainRunnable(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public void run() {
      for(int i=start;i<=end;i++) {
        sum+=i;
      }
    }
  }

}
