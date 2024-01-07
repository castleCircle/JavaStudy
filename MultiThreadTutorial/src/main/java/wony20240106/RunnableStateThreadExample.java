package wony20240106;

public class RunnableStateThreadExample {

  public static void main(String[] args) {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (true){
          for(int i=0;i<10000000;i++){
            if(i%10000 == 0){
              System.out.println("스레드 상태는요: " + Thread.currentThread().getState());
            }
          }
        }
      }
    });
    thread.start();
  }

}
