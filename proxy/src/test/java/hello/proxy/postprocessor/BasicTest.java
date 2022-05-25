package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

  @Test
  void basicConfig(){
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

    //A는 bean
    final A beanA = applicationContext.getBean("beanA", A.class);
    beanA.helloA();;

    Assertions.assertThrows(NoSuchBeanDefinitionException.class,()->applicationContext.getBean(B.class));

  }

  @Test
  void basicConfigTest(){
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

    org.assertj.core.api.Assertions.assertThatThrownBy(()->applicationContext.getBean(B.class)).isInstanceOf(NoSuchBeanDefinitionException.class);
  }


  @Slf4j
  @Configuration
  static class BasicConfig{
    @Bean(name = "beanA")
    public A a(){
      return new A();
    }
  }

  @Slf4j
  static class A{

    public void helloA(){
      log.info("hello A");
    }

  }

  @Slf4j
  static class B{

    public void helloB(){
      log.info("hello B");
    }

  }

}
