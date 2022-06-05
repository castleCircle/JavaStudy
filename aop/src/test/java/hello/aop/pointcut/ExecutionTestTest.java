package hello.aop.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
public class ExecutionTestTest {

  AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
  Method helloMethod;

  @BeforeEach
  public void init() throws NoSuchMethodException {
    helloMethod = MemberServiceImpl.class.getMethod("hello",String.class);
  }

  @Test
  void printMethod(){
    log.info("helloMethod={}",helloMethod);
  }

  @Test
  void exactMatch(){
    pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
    assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void textMatch(){
    pointcut.setExpression("execution(* hello(..))");
    assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void textMatch1(){
    pointcut.setExpression("execution(* hello..*llo(..))");
    assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void typeMatchInternal() throws NoSuchMethodException {
    pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
    final Method internal = MemberServiceImpl.class.getMethod("internal", String.class);
    assertThat(pointcut.matches(internal,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void typeNotMatchInternal() throws NoSuchMethodException {
    pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
    final Method internal = MemberServiceImpl.class.getMethod("internal", String.class);
    assertThat(pointcut.matches(internal,MemberServiceImpl.class)).isFalse();
  }

  @Test
  void typeMatchSuperTye(){
    pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void argsMatch(){
    pointcut.setExpression("execution(* *(String))");
    assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void argsMatchComplex(){
    pointcut.setExpression("execution(* *(String,..))");
    assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }




}
