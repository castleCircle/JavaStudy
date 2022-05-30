package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class AspectV3 {

  @Pointcut("execution(* hello.aop.order..*(..))")
  private void orderInfo(){}

  @Around("orderInfo()")
  public Object doLog(ProceedingJoinPoint joinPoint) throws  Throwable{
    log.info("==========do log========: {}",joinPoint.getSignature());
    final Object proceed = joinPoint.proceed();
    return proceed;
  }

}
