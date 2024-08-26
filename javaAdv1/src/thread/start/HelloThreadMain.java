package thread.start;

public class HelloThreadMain {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + ": main() start");

    HelloThread helloThread = new HelloThread();
    System.out.println(Thread.currentThread().getName() + " start() 호출 전");
    //main thread가 run을 호출하는게 아니다. 새로운 thread가 run을 호출한다.
    helloThread.start();
    System.out.println(Thread.currentThread().getName() + " start() 호출 후");

    System.out.println(Thread.currentThread().getName() + ": main() end");
  }

}
