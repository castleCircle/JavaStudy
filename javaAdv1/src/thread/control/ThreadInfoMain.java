package thread.control;

import static util.MyLogger.log;

import thread.start.HelloRunnable;

public class ThreadInfoMain {

  public static void main(String[] args) {
    //main 스레드
    final Thread mainThread = Thread.currentThread();
    log("mainThread = " + mainThread);
    log("mainThread.threadId()=" + mainThread.threadId());
    log("mainThread.getName()=" + mainThread.getName());
    log("mainThread.getState()=" + mainThread.getState());

    System.out.println("====================");
    Thread myThread = new Thread(new HelloRunnable(),"myThread");
    log("myThread.threadId()=" + myThread.threadId());
    log("myThread.getName()=" + myThread.getName());
    log("myThread.getState()=" + myThread.getState());
  }

}