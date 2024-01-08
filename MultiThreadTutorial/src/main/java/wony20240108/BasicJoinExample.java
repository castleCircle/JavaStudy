package wony20240108;

public class BasicJoinExample {

  public static void main(String[] args) {

    Thread thread = new Thread(()->{
      try{
        System.out.println("스레드가 3초 동안 작동합니다");
        Thread.sleep(3000);
        System.out.println("스레드 작동 완료.");
      }catch (InterruptedException ex){
        ex.printStackTrace();
      }
    });

    thread.start();

    try {
      System.out.println("join 시작하기전");
      thread.join();
      System.out.println("join 끝났엉");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

}
