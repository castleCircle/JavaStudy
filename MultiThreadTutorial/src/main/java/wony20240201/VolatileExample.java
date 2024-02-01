package wony20240201;

public class VolatileExample {

//  boolean flag = true; volatile을 안붙이면 무한루프에 빠짐. 메인메모리에 동기화가 안되서
  volatile boolean flag = true;

  public void test(){

    new Thread(()->{
      int count =0;
      while(flag){
        count++;
      }
      System.out.println("Thread 1종료. Count:" + count);
    }).start();

    new Thread(()->{
       try{
         Thread.sleep(1000);
       }catch (InterruptedException interruptedException){

       }
      System.out.println("thread2 종료중");
       flag = false;
    }).start();

  }

  public static void main(String[] args) {
    new VolatileExample().test();
  }

}
