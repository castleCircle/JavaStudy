package hello.proxy.pureproxy.proxy.testcode;

public class ProxyTestPatternClient {

  private TestSubject subject;

  public ProxyTestPatternClient(TestSubject subject) {
    this.subject = subject;
  }

  public void execute(){
    subject.operation();
  }
}
