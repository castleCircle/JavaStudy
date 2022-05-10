package hello.proxy.pureproxy.proxy.testcode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealTestSubject implements TestSubject{

  @Override
  public String operation() {
    log.info("Call Real Test Subject");
    sleep(2000);
    return "select data";
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    }catch (InterruptedException e){
      e.printStackTrace();
    }
  }
}
