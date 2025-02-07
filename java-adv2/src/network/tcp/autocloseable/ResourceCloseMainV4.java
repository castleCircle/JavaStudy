package network.tcp.autocloseable;

public class ResourceCloseMainV4 {

  /**
   * AutoCloseable는 close에서 호출하는 exception를
   * @param args
   */
  public static void main(String[] args) {
    try {
      logic();
    }catch (CallException e){
      System.out.println("CallExcpetion 예외 처리");

      Throwable[] suppressed = e.getSuppressed();
      for(Throwable throwable : suppressed) {
        System.out.println("suppressed: " + throwable);
      }

      throw new RuntimeException(e);
    }catch (CloseException e){
      System.out.println("CloseException 예외 처리");
      throw new RuntimeException(e);
    }
  }

  /*
  try 블록이 끝나면 -> close  처리 -> catch 순서로 빠짐
   */
  private static void logic() throws CallException, CloseException {
    try(ResourceV2 resourceV1 = new ResourceV2("resource1");
    ResourceV2 resourceV2 = new ResourceV2("resource2");){
      resourceV1.call();
      resourceV2.callEx(); // CAllEaception
    }catch (CallException e){
      System.out.println("ex: " + e.getMessage());
//      throw e;
    }finally {
      throw new RuntimeException("sfasd");
    }
  }

}
