package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
public class ExecutionTest {

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
    Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void allMatch(){
    pointcut.setExpression("execution(* *(..))");
    Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void nameMatch(){
    pointcut.setExpression("execution(* hello(..))");
    Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void nameMatchStar1(){
    pointcut.setExpression("execution(* hel*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void nameMatchStar2(){
    pointcut.setExpression("execution(* *el*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void nameMatchFalse(){
    pointcut.setExpression("execution(* nono(..))");
    Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
  }

  @Test
  void packageExactMatch1(){
    pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.hello(..))");
    Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void packageExactMatch2(){
    pointcut.setExpression("execution(* hello.aop.member.*.*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void packageExactFalse(){
    pointcut.setExpression("execution(* hello.aop.*.*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
  }

  @Test
  void packageMatchSubPackage1(){
    pointcut.setExpression("execution(* hello.aop.member..*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void packageMatchSubPackage2(){
    pointcut.setExpression("execution(* hello.aop..*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
  }

  @Test
  void typeExactMatch(){
    pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void typeMatchSuperType(){
    pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void typeMatchInternal() throws NoSuchMethodException {
    pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
    final Method internal = MemberServiceImpl.class.getMethod("internal", String.class);
    Assertions.assertThat(pointcut.matches(internal, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void typeMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
    pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
    final Method internal = MemberServiceImpl.class.getMethod("internal", String.class);
    Assertions.assertThat(pointcut.matches(internal, MemberServiceImpl.class)).isFalse();
  }

  //String 타입의 파라미터 허용
  @Test
  void argsMatch(){
    pointcut.setExpression("execution(* *(String))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void argsMatchNoArgs(){
    pointcut.setExpression("execution(* *())");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
  }

  //정확히 하나의 파라미터 허용, 모든 타입 허용
  @Test
  void argsMatchStar(){
    pointcut.setExpression("execution(* *(*))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void argsMatchAll(){
    pointcut.setExpression("execution(* *(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  //(STring) ,(String,XXX), (String,xxx,xxx)
  @Test
  void argsMatchComplex(){
    pointcut.setExpression("execution(* *(String,..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }


}
