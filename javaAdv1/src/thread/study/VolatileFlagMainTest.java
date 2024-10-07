package thread.study;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMainTest {

  public static void main(String[] args) {
    final MyVolatileFlagTest myVolatileFlagTest = new MyVolatileFlagTest();
    final Thread thread = new Thread(myVolatileFlagTest);
    thread.start();

    sleep(1000);

    myVolatileFlagTest.runFlag = false;

  }


  static class MyVolatileFlagTest implements Runnable{

    volatile boolean runFlag = true;

    @Override
    public void run() {
      while(runFlag){
      }
      log("end");
    }
  }



}
