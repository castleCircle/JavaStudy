package hello.aop.examtest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Slf4j
public class TraceAspectTest {

  @Before("@annotation(hello.aop.examtest.annotation.TraceTest)")
  public void ss(JoinPoint joinPoint){
    log.info("joinPoint: {}, args:{}",joinPoint.getSignature(),joinPoint.getArgs());
  }

}
