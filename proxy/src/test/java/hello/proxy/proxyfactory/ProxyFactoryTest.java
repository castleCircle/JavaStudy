package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.advice.TimeTestAdvice;
import hello.proxy.common.service.ConcreateService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
public class ProxyFactoryTest {

  @Test
  @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
  void interfaceProxy(){
    ServiceInterface target = new ServiceImpl();
    final ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());
    final ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
    log.info("targetClass={}",target.getClass());
    log.info("proxyyClass={}",proxy.getClass());

    proxy.save();

    Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
    Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
  }

  @Test
  @DisplayName("proxyFactory interFace 구현")
  public void interfaceTestProxy(){
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeTestAdvice());

    final ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
    log.info("targetClass={}",target.getClass());
    log.info("proxyyClass={}",proxy.getClass());
    proxy.save();
  }


  @Test
  @DisplayName("구체클래스만 있으면 CGLIB 사용")
  void concreteaProxy(){
    ConcreateService target = new ConcreateService();
    final ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());
    final ConcreateService proxy = (ConcreateService)proxyFactory.getProxy();
    log.info("targetClass={}",target.getClass());
    log.info("proxyyClass={}",proxy.getClass());

    proxy.call();

    Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
    Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
  }

  @Test
  @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIT를 사용하고 , 클래스 기반 프록시 사용")
  void proxyTargetClass(){
    ServiceInterface target = new ServiceImpl();
    final ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(true);
    proxyFactory.addAdvice(new TimeAdvice());
    final ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
    log.info("targetClass={}",target.getClass());
    log.info("proxyyClass={}",proxy.getClass());

    proxy.save();

    Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
    Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
  }

  @Test
  void proxyTargetClassTest(){
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(true);
    //default false이고
    //true를 하게 되면 jdk, cglib둘중 무조건 cglib를 사용한다.
    proxyFactory.addAdvice(new TimeAdvice());

    final ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();

    proxy.save();

    Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();


  }

}
