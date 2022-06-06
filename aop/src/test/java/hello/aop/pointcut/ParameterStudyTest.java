package hello.aop.pointcut;

import hello.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import({ParameterStudyTest.ParameterStudyAspect.class})
@SpringBootTest
public class ParameterStudyTest {

  @Autowired
  MemberService memberService;

  @Test
  void success(){
    log.info("memberService Proxy={}",memberService.getClass());
    memberService.hello("helloA");
  }

  @Slf4j
  @Aspect
  static class ParameterStudyAspect{

    @Pointcut("execution(* hello.aop.member..*.*(..))")
    private void allMember(){}

    @Around("allMember()")
    public Object logArgs1(ProceedingJoinPoint joinPoint) throws  Throwable{
      final Object arg = joinPoint.getArgs()[0];
      log.info("[logArgs1]{}, args={}",joinPoint.getSignature(),arg);
      return joinPoint.proceed();
    }

    @Around("allMember() && args(arg,..)")
    public Object logARgs2(ProceedingJoinPoint joinPoint,Object arg)throws Throwable{
      log.info("[logArgs2]{}, arg={}",joinPoint.getSignature(),arg);
      return joinPoint.proceed();
    }

    @Before("allMember() && args(arg,..)")
    public void logArgs3(String arg){
      log.info("[logArgs3] arg={}",arg);
    }

    @Before("allMember() && this(obj)")
    public void thisArgs(JoinPoint joinPoint,MemberService obj){
      log.info("[this]:{},obj={}",joinPoint.getSignature(),obj.getClass());
    }

    @Before("allMember() && target(obj)")
    public void targetArgs(JoinPoint joinPoint,MemberService obj){
      log.info("[target]:{},obj={}",joinPoint.getSignature(),obj.getClass());
    }

  }

}
