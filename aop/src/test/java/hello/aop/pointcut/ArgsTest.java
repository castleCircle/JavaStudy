package hello.aop.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import hello.aop.member.MemberServiceImpl;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
public class ArgsTest {

  Method helloMethod;

  @BeforeEach
  public void init() throws NoSuchMethodException {
    helloMethod = MemberServiceImpl.class.getMethod("hello",String.class);
  }

  private AspectJExpressionPointcut pointcut(String expression){
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression(expression);
    return pointcut;
  }

  @Test
  void args(){
    assertThat(pointcut("args(String)")
        .matches(helloMethod,MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(Object)")
        .matches(helloMethod,MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args()")
        .matches(helloMethod,MemberServiceImpl.class)).isFalse();
    assertThat(pointcut("args(..)")
        .matches(helloMethod,MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(*)")
        .matches(helloMethod,MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(String,..)")
        .matches(helloMethod,MemberServiceImpl.class)).isTrue();

  }

  //execution은 메소드의 시그니처만 보고 판단해서 딱 맞아야한다. (정적)
  //args은 메소드의 동적 데이터만 보고 판단한다. (동적)
  @Test
  void argsVsExecution(){
    assertThat(pointcut("args(String)")
        .matches(helloMethod,MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(java.io.Serializable)")
        .matches(helloMethod,MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(Object)")
        .matches(helloMethod,MemberServiceImpl.class)).isTrue();

    assertThat(pointcut("execution(* *(String))")
        .matches(helloMethod,MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("execution(* *(java.io.Serializable))")
        .matches(helloMethod,MemberServiceImpl.class)).isFalse();
    assertThat(pointcut("execution(* *(Object))")
        .matches(helloMethod,MemberServiceImpl.class)).isFalse();
  }

}
