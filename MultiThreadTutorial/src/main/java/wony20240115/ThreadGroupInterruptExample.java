package wony20240115;

public class ThreadGroupInterruptExample {

  public static void main(String[] args) throws InterruptedException {

    ThreadGroup topGroup = new ThreadGroup("최상위 스레드 그룹");
    ThreadGroup subGroup = new ThreadGroup(topGroup,"하위 스레드 그룹");

    Thread topGroupThread = new Thread(topGroup,()->{
      while(true){
        System.out.println("상위 그룹 실행중");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    },"상위 그룹 스레드");

    Thread subGroupThread = new Thread(subGroup,()->{
      while(true){
        System.out.println("하위 그룹 실행중");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    },"하위 그룹위스레드");

    topGroupThread.start();
    subGroupThread.start();

    Thread.sleep(3000);

    System.out.println("중지");

    topGroupThread.interrupt();
    subGroupThread.interrupt();

  }

}
