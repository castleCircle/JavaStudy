package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BImpl implements BInterface{

  @Override
  public String call() {
    log.info("B 호출");
    return "b";
  }

  @Override
  public String test() {
    log.info("test 호출");
    return "test";
  }
}
