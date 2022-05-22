package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreateService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

  @Test
  void cglib(){
    ConcreateService target = new ConcreateService();

    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(ConcreateService.class);
    enhancer.setCallback(new TimeMethodInterceptor(target));
    final ConcreateService proxy= (ConcreateService)enhancer.create();
    log.info("targetClass={}",target.getClass());
    log.info("proxyClass={}",proxy.getClass());

    proxy.call();
  }

  @Test
  void cglib1(){
    ConcreateService concreateService = new ConcreateService();

    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(ConcreateService.class);
    enhancer.setCallback(new TimeMethodInterceptor(concreateService));
    final ConcreateService concreateService1 = (ConcreateService) enhancer.create();

    concreateService1.call();

    enhancer.create();
  }

}
