package wony20240208;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleThreadPool {

  private int numThreads;
  private Queue<Runnable> taskQueue;
  private Thread[] threads;
  private volatile boolean isShutdown;

  public SimpleThreadPool(int numThreads) {
    this.numThreads = numThreads;
    this.taskQueue = new LinkedList<>();
    this.threads = new Thread[numThreads];
    this.isShutdown = false;

    for(int i=0;i<numThreads;i++){
      threads[i] = new WorkerThread();
      threads[i].start();
    }
  }

  public void submit(Runnable task){
    if(!isShutdown){
      synchronized (taskQueue) {
        taskQueue.offer(task);
        taskQueue.notifyAll();
      }
    }
  }


  public void shutdown(){
    isShutdown = true;
    synchronized (taskQueue){
      taskQueue.notifyAll();
    }

    for(Thread thread:threads){
      try{
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private class WorkerThread extends Thread{

    @Override
    public void run() {
      while(!isShutdown){
        Runnable task;
        synchronized (taskQueue){
          while(taskQueue.isEmpty() && !isShutdown){
            try {
              System.out.println("자러 들어간다. start");
              taskQueue.wait();
              System.out.println("자러 들어간다. end");
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
        if(!taskQueue.isEmpty()) {
          task = taskQueue.poll();
        }else{
          continue;
        }
        task.run();
      }
    }
  }



}
