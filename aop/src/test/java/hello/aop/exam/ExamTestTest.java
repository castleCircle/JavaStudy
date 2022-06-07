package hello.aop.exam;

import hello.aop.examtest.ExamTestService;
import hello.aop.examtest.aop.TraceAspectTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({TraceAspectTest.class})
public class ExamTestTest {

  @Autowired
  ExamTestService examTestService;

  @Test
  public void test(){
    examTestService.save("12");
  }

}
