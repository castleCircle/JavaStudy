package wony20240104;

public class ThreadStackExample {

  public static void main(String[] args) {
    for(int i=0;i<3;i++){
      Thread thread = new Thread(new MyRunnable(i));
      thread.start();
    }
  }

  static class MyRunnable implements Runnable{

    private final int threadId;

    public MyRunnable(int threadId){
      this.threadId = threadId;
    }

    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + "실행중");
      while(Thread.currentThread().getName().equals("Thread-0")){
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("sss");
      }
      firstMethod(threadId);
    }

    private void firstMethod(int threadId) {
      int localValue = threadId + 100;
      secondMethod(localValue);
    }

    private void secondMethod(int localValue) {
      System.out.println(Thread.currentThread().getName() + " : 스데르 ID :" + threadId + ", value:"+localValue);
    }
  }

}
