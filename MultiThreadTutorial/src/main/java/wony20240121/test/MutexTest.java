package wony20240121.test;

public class MutexTest {

  private boolean lock = false;

  public synchronized void acquired(){
    while(lock){
      try{
        wait();
      }catch (InterruptedException e){
        throw new RuntimeException(e);
      }
    }
    lock = true;
  }

  public synchronized void release(){
    this.lock = false;
    this.notify();
  }

//  void wait() : 현재 스레드를 다른 스레드가 이 객체에 대한 notify() 또는 notifyAll() 메소드를 호출할때까지 대기합니다.
//  void wait(long timeout) : 현재 스레드를 다른 스레드가 이 객체에 대한 notify() 또는 notifyAll() 메소드를 호출하거나 timeout 시간동안 대기합니다.
//  void notify() : 이 객체에 대해 대기중인 스레드 하나를 깨웁니다.
//  void notifyAll() :이 객체에 대해 대기중인 모든 스레드를 깨웁니다.

}
