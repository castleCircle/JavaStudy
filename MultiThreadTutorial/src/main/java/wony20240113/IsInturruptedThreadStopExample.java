package wony20240113;

public class IsInturruptedThreadStopExample {

  public static void main(String[] args) throws InterruptedException {
    Thread worker = new Thread(()->{
      while(!Thread.currentThread().isInterrupted()){
        System.out.println("작업 스레드가 실행 중입니다.");
      }
      System.out.println("인터럽트 상태:" + Thread.currentThread().isInterrupted());
      System.out.println("작업 스레드가 중단되었습니다.");
    });

    Thread stoper = new Thread(()->{
      try{
        Thread.sleep(1000);
      }catch (InterruptedException e){
        e.printStackTrace();
      }
      worker.interrupt();
      System.out.println("중단 스레드가 작업 스레드를 중단시킴");
    });

    worker.start();
    stoper.start();

    worker.join();
    stoper.join();

    System.out.println("메인 끝");
  }

}
