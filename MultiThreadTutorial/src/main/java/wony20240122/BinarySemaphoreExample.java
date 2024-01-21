package wony20240122;

import wony20240121.Mutex;
import wony20240121.SharedData;

public class BinarySemaphoreExample {

  public static void main(String[] args) throws InterruptedException {
    SharedResource sharedData = new SharedResource(new BinarySemaphore());
    final Thread thread1 = new Thread(() -> {
      sharedData.sum();
    });

    final Thread thread2 = new Thread(() -> {
      sharedData.sum();
    });

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    System.out.println("최종합계 : " + sharedData.getSum());
  }

}

class SharedResource {

  private int sharedValue = 0;

  private CommonSemaphore commonSemaphore;

  public SharedResource(CommonSemaphore commonSemaphore){
    this.commonSemaphore = commonSemaphore;
  }

  public void sum(){
    commonSemaphore.acquired();
    try{
      for(int i=0;i<1000000;i++){
        sharedValue++;
      }
    }catch (Exception e){

    }finally {
      commonSemaphore.release();
    }
  }

  public int getSum(){
    return sharedValue;
  }

}
