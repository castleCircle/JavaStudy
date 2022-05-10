package hello.proxy.pureproxy.proxy.testcode;

import hello.proxy.pureproxy.proxy.code.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheTestProxy implements TestSubject{

  private TestSubject subject;
  private String cacheValue;

  public CacheTestProxy(TestSubject subject) {
    this.subject = subject;
  }

  @Override
  public String operation() {
    log.info("cache operation");
    if(cacheValue == null){
      cacheValue = subject.operation();
    }
    return cacheValue;
  }
}
