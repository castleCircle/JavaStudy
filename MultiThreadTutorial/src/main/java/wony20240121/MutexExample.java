package wony20240121;

public class MutexExample {

  public static void main(String[] args) throws InterruptedException {
    SharedData sharedData = new SharedData(new Mutex());
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
