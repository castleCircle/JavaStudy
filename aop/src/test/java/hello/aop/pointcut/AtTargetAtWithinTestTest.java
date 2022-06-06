package hello.aop.pointcut;

import hello.aop.member.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Import({AtTargetAtWithinTestTest.Config.class})
@SpringBootTest
public class AtTargetAtWithinTestTest {

  @Autowired
  Child child;

  @Test
  void success(){
    log.info("child Proxy={}",child.getClass());
    child.childMethod();
    child.parentMethod();
  }

  static class Config{
    @Bean
    public Parent parent(){
      return new Parent();
    }

    @Bean
    public Child child(){
      return new Child();
    }

    @Bean AtTargetAtWithinAspect atTargetAtWithinAspect(){
      return new AtTargetAtWithinAspect();
    }

  }

  static class Parent{
    public void parentMethod(){

    }
  }

  @ClassAop
  static class Child extends Parent{
    public void childMethod(){

    }
  }

  @Slf4j
  @Aspect
  static class AtTargetAtWithinAspect{

    @Around("execution(* hello.aop..*(..)) && @target(hello.aop.member.annotation.ClassAop)")
    public Object atTarget(ProceedingJoinPoint joinPoint) throws  Throwable{
      log.info("[@Target] {}",joinPoint.getSignature());
      return joinPoint.proceed();
    }

  }

}
