package wony20240116;

public class InheritableThreadLocalExample {

  public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

  public static void main(String[] args) {

    inheritableThreadLocal.set("부모 스레드의 값");

    final Thread childThread = new Thread(() -> {
      System.out.println("자식 스레드에서 부모로부터 상속받은 값:" + inheritableThreadLocal.get());

      inheritableThreadLocal.set("자식 스레드의 새로운 값");
      System.out.println("자식 스레드에서 설정한 후의 값:" + inheritableThreadLocal.get());
    });

    childThread.start();

    try{
      childThread.join();
    }catch(InterruptedException e){}

    System.out.println("");
  }

}
