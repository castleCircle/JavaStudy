package hello.advanced.trace.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

//템플릿 메서드는 변하는 부분과 부분하지 않는 부분을 분리하는것이 중요하다.

@Slf4j
public class TemplateMethodTest {

  @Test
  void templateMethodV0(){
    logic1();
    logic2();
  }

  private void logic1() {
    long startTime = System.currentTimeMillis();

    log.info("비지니스 로직1 실행");

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTiem={}",resultTime);
  }

  private void logic2() {
    long startTime = System.currentTimeMillis();

    log.info("비지니스 로직1 실행");

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTiem={}",resultTime);
  }

}
