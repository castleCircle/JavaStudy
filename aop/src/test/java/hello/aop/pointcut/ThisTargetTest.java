package hello.aop.pointcut;

import hello.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * spring.aop.proxy-target-class=true //CGLIB
 * spring.aop.proxy-target-class=false //JDK 동적 프록시
 */
@Slf4j
@SpringBootTest(properties = "spring.aop.proxy-target-class=false")
@Import({ThisTargetTest.ThisTargetAspect.class})
public class ThisTargetTest {

  @Autowired
  MemberService memberService;

  @Test
  void success(){
    log.info("memberSErvice Proxy={}",memberService.getClass());
    memberService.hello("helloA");
  }

  @Slf4j
  @Aspect
  static class ThisTargetAspect{

    @Around("this(hello.aop.member.MemberService)")
    public Object doThisInterFace(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[this-interface] {}",joinPoint.getSignature());
      return joinPoint.proceed();
    }

    @Around("target(hello.aop.member.MemberService)")
    public Object doTargetInterFace(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[target-interface] {}",joinPoint.getSignature());
      return joinPoint.proceed();
    }

    @Around("this(hello.aop.member.MemberServiceImpl)")
    public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[this] {}",joinPoint.getSignature());
      return joinPoint.proceed();
    }

    @Around("target(hello.aop.member.MemberServiceImpl)")
    public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[target] {}",joinPoint.getSignature());
      return joinPoint.proceed();
    }

  }

}
