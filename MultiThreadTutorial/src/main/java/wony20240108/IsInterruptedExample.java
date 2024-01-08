package wony20240108;

public class IsInterruptedExample {

  public static void main(String[] args) {

    Thread thread = new Thread(()->{
      while(!Thread.currentThread().isInterrupted()){
        System.out.println("스레드가 작동 중입니다.");
      }
      System.out.println("스레드가 인터럽트 되었습니다.");
      System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
    });

    thread.start();

    try{
      Thread.sleep(1000);
    }catch (InterruptedException ex){
      ex.printStackTrace();
    }

    thread.interrupt();

  }

}
