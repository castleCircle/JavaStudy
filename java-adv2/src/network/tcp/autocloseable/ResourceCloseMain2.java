package network.tcp.autocloseable;

public class ResourceCloseMain2 {

  /**
   * 문제는 자원정리하다가 exception 발생한것보다 서비스 로직을 처리하다 exception 발생을 처리하는게 더 중요하다.
   * 아래와 같은 코드는 서비스 로직처리중 에러가 났는데 fianlly로 가면서 자원정리중 에러가 발생하면서 서비스 예외가 먹혀버린다.
   */
  public static void main(String[] args) {
    try {
      logic();
    }catch (CallException e){
      System.out.println("CallExcpetion 예외 처리");
      throw new RuntimeException(e);
    }catch (CloseException e){
      System.out.println("CloseException 예외 처리");
      throw new RuntimeException(e);
    }
  }

  private static void logic() throws CallException, CloseException {
    ResourceV1 resource1 = null;
    ResourceV1 resource2 = null;

    try {
      resource1 = new ResourceV1("resource1");
      resource2 = new ResourceV1("resource2");

      resource1.call();
      resource2.callEx();
    }catch (CallException e){
      System.out.println("ex: " + e);
      throw e;
    }finally {
      if(resource2 != null){
        resource2.closeEx(); // CloseException 발생
      }
      if(resource1 != null){
        resource1.closeEx(); // 이 코드 호출 안댐
      }
    }
  }

}
