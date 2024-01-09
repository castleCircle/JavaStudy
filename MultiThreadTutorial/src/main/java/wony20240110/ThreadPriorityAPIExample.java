package wony20240110;

public class ThreadPriorityAPIExample {

  public static void main(String[] args) {
    Thread thread = new Thread();
    System.out.println("기본 우선순위" + thread.getPriority());
    thread.start();

    final Thread minThread = new Thread(() -> {
      System.out.println("최소 우선순위 : " + Thread.currentThread().getPriority());
    });
    minThread.setPriority(Thread.MIN_PRIORITY);
    minThread.start();

    final Thread normThread = new Thread(() -> {
      System.out.println("NORM 우선순위 : " + Thread.currentThread().getPriority());
    });
    normThread.setPriority(Thread.NORM_PRIORITY);
    normThread.start();

    final Thread maxThread = new Thread(() -> {
      System.out.println("maxThread 우선순위 : " + Thread.currentThread().getPriority());
    });
    maxThread.setPriority(Thread.MAX_PRIORITY);
    maxThread.start();
  }

}
