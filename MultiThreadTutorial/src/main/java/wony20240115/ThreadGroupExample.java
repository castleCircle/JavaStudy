package wony20240115;

public class ThreadGroupExample {

  public static void main(String[] args) {
    ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
    ThreadGroup customThreadGroup = new ThreadGroup("Custom Thread Group");

    final Thread defaultGroupThread = new Thread(new GroupRunnable(), "DefaultGroupThread");
    final Thread mainGroupThread = new Thread(mainThreadGroup, new GroupRunnable(), "MainGroupThread");
    final Thread customGroup = new Thread(customThreadGroup, new GroupRunnable(), "customerGourp");

    defaultGroupThread.start();
    mainGroupThread.start();
    customGroup.start();

  }

  static class GroupRunnable implements Runnable{

    @Override
    public void run() {
      Thread currentThread = Thread.currentThread();
      System.out.println(currentThread.getName() + " 는 " + currentThread.getThreadGroup().getName() + " 에 속한다");
    }
  }

}
