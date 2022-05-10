package hello.proxy.pureproxy.proxy.testcode;

import org.junit.jupiter.api.Test;

public class ProxyTestPatternTest {

  @Test
  public void noProxy(){
    RealTestSubject subject = new RealTestSubject();
    ProxyTestPatternClient proxyTestPatternTest = new ProxyTestPatternClient(subject);
    proxyTestPatternTest.execute();
    proxyTestPatternTest.execute();
    proxyTestPatternTest.execute();
  }

  @Test
  public void proxy(){
    RealTestSubject subject = new RealTestSubject();
    CacheTestProxy proxy = new CacheTestProxy(subject);
    ProxyTestPatternClient client = new ProxyTestPatternClient(proxy);
    client.execute();
    client.execute();
    client.execute();
  }

}
