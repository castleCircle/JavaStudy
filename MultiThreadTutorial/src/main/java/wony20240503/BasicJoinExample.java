package wony20240503;

public class BasicJoinExample {

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(()->{
      try{
        System.out.println("스레드가 3초 동안 작동");
        Thread.sleep(3000);
        System.out.println("스데르 작동 완료");
      }catch (InterruptedException e){
        System.out.println("스레드1 인터럽트 받음");
      }
    });

    thread.start();
    thread.join();

    System.out.println("main thread 종료");
  }

}
