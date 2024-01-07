package wony20240104;

public class ThreadExample {

  public static void main(String[] args) {
//    final TestThread testThread = new TestThread();
//    testThread.start();
//
//    final Thread thread = new Thread(new MyRunnable());
//    thread.start();
//
//    new Thread(){
//      @Override
//      public void run() {
//        System.out.println("익명 이요");
//      }
//    }.start();

    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("스레드 실행중");
      }
    });

    thread1.start();
  }

}

class TestThread extends Thread{

  @Override
  public void run() {
    System.out.println("TestThread");
  }
}
//
//class MyRunnable implements Runnable{
//
//  @Override
//  public void run() {
//    System.out.println("runnable");
//  }
//}