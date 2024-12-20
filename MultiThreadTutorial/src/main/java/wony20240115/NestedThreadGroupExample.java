package wony20240115;

import wony20240115.ThreadGroupExample.GroupRunnable;

public class NestedThreadGroupExample {

  public static void main(String[] args) throws InterruptedException {
    ThreadGroup topGroup = new ThreadGroup("최상위 스레드 그룹");
    ThreadGroup subGroup = new ThreadGroup(topGroup,"하위 스레드 그룹");

    final Thread topGroupThread = new Thread(topGroup, new GroupRunnable(), "TopGroupThread");
    final Thread subgroupthread = new Thread(subGroup, new GroupRunnable(), "subGroupThread");

    topGroupThread.start();
    subgroupthread.start();

    Thread.sleep(1000);

    System.out.println("---------");
    System.out.println("최상위 스레드 그룹의 정보");
    topGroup.list();

  }

  static class GroupRunnable implements Runnable{

    @Override
    public void run() {
      Thread currentThread = Thread.currentThread();
      System.out.println(currentThread.getName() + " 는 " + currentThread.getThreadGroup().getName() + " 에 속한다");
    }
  }

}
