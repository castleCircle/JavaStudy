package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {

  @Test
  void checked_catch(){
    final Service service = new Service();
    service.callCatch();
  }

  @Test
  void checked_throw() throws MyCheckedException {
    final Service service = new Service();
    Assertions.assertThatThrownBy(()->service.callThrow()).isInstanceOf(MyCheckedException.class);
  }

  /**
   * Exception을 상속받은 예외는 체크 예외가 됨
   */
  static class MyCheckedException extends Exception{
    public MyCheckedException(String message){
      super(message);
    }
  }

  /**
   * 체크 익센션은 잡거나 던지거나 둘중해야함
   */
  static class Service{
    Repository repository = new Repository();

    /**
     * 예외를 잡아서 처리하는 코드
     */
    public void callCatch(){
      try {
        repository.call();
      } catch (MyCheckedException e) {
        //예외 처리 로직
        log.info("예외 처리,message={}",e.getMessage(),e);
      }
    }

    public void callThrow() throws MyCheckedException {
      repository.call();
    }
  }

  static class Repository{
    public void call() throws MyCheckedException {
      throw new MyCheckedException("ex");
    }
  }

}
