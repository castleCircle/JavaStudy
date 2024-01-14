package wony20240113;

public class FlagThreadStopExample {

  private volatile static boolean running = true;

  public static void main(String[] args) {

    new Thread(()->{
      int count = 0;
      while(running){
        //volatile을 안써도 sleep을 하면 컨텍스트 문맥을 바꿔야 해서 cpu 캐시를 지워야 하므로 중단이 된다.
//        try{
//          Thread.sleep(1);
//        }catch (Exception e){
//          e.printStackTrace();
//        }
        count++;
      }
      System.out.println("스레드 1 종료,count" + count);
    }).start();

    new Thread(()->{
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println("스레드 2 종료");
      running = false;
    }).start();


  }

}
