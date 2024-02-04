package wony20240204;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ReadWriteLockExample {

  public static void main(String[] args) {
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    SharedData sharedData = new SharedData();

    final Thread reader = new Thread(() -> {
      readWriteLock.readLock().lock();

      try {
        System.out.println("읽기 스레드가 데이터를 익고 있습니다. 데이터: " + sharedData.getDate());
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      } finally {
        readWriteLock.readLock().unlock();
      }
    });

    final Thread reader2 = new Thread(() -> {
      readWriteLock.readLock().lock();

      try {
        System.out.println("읽기 스레드2가 데이터를 익고 있습니다. 데이터: " + sharedData.getDate());
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      } finally {
        readWriteLock.readLock().unlock();
      }
    });

    final Thread writer = new Thread(() -> {
      readWriteLock.writeLock().lock();

      try {
        System.out.println("쓰기 스레드가 데이터를 쓰고 있습니다.");
        sharedData.setDate(40);
        System.out.println("쓰기 스레드가 데이터를 변경 했습니다.");
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        System.out.println("쓰기 스레드가 데이터를 변경 했습니다:" + sharedData.getDate());
      } finally {
        readWriteLock.writeLock().unlock();
      }
    });


    reader.start();
    writer.start();
    reader2.start();

  }

  static class SharedData{
    private int data = 0;
    public int getDate(){
      return data;
    }

    public void setDate(int data){
      this.data =  data;
    }
  }

}
