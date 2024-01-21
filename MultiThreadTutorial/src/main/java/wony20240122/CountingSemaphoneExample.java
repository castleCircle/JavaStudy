package wony20240122;

public class CountingSemaphoneExample {

  public static void main(String[] args) throws InterruptedException {
    int permits = 2;
    CommonSemaphore semaphore = new CountingSemaphore(permits);
    SharedResource resource = new SharedResource(semaphore);

    int threadCount = 5;

    Thread[] threads = new Thread[threadCount];

    for(int i=0;i<threadCount;i++){
      threads[i] = new Thread(()->{
        resource.sum();
      });
      threads[i].start();
    }

    for(int i=0;i<threadCount;i++){
      threads[i].join();
    }

    System.out.println("최종값" + resource.getSum());

  }

}
