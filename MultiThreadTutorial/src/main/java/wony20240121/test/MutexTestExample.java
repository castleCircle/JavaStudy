package wony20240121.test;

public class MutexTestExample {

  public static void main(String[] args) throws InterruptedException {

    SharedDataTest sharedDataTest = new SharedDataTest(new MutexTest());

    final Thread thread = new Thread(() -> {
      sharedDataTest.start();
    });

    final Thread thread1 = new Thread(() -> {
      sharedDataTest.start();
    });

    thread.start();
    thread1.start();

    thread.join();
    thread1.join();

    System.out.println("전체갯수:" + sharedDataTest.getCount());


  }

}
