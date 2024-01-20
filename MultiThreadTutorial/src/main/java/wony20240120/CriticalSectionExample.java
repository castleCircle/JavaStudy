package wony20240120;

public class CriticalSectionExample {

  public static void main(String[] args) {

    SharedResource sharedResource = new SharedResource();
    Thread thread = new Thread(sharedResource::increment);
    Thread thread1 = new Thread(sharedResource::increment);

    thread.start();
    thread1.start();

  }

}

class SharedResource{
  private int counter =0 ;

  public void increment(){
    for(int i=0;i<100000;i++){


      synchronized (this){// Entry Section

        //Critical Section
        counter++;
        System.out.println(Thread.currentThread().getName() + ":" + counter );

      }//Exit Section

    }

    doOtherWork();
  }

  private void doOtherWork() {
    System.out.println(Thread.currentThread().getName() + " 는 critical Section 외부에서 작업을 수행하고 있다.");
  }

}
