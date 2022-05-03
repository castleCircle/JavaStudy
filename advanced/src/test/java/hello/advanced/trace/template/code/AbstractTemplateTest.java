package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplateTest {

  public void execute(){
    log.info("start");
    test();
    log.info("end");
  }

  protected abstract void test();

}
