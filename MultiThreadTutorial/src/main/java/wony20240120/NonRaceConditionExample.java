package wony20240120;

public class NonRaceConditionExample {

  private static int sharedResource = 0;

  public static void main(String[] args) {

    Thread[]  incrementThread = new Thread[100];
    for(int i=0;i<incrementThread.length;i++){
      incrementThread[i] = new Thread(()->{
        for(int j=0;j<10000;j++){
          synchronized (NonRaceConditionExample.class){
            sharedResource++;
          }
        }
      });
      incrementThread[i].start();
    }


  }

}
