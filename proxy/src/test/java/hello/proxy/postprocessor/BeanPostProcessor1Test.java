package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessor1Test {

  @Test
  public void test(){
    ApplicationContext context = new AnnotationConfigApplicationContext(ConfigFile.class);
    final B beanB = context.getBean("beanA", B.class);
    beanB.callB();

    Assertions.assertThatThrownBy(()->context.getBean(A.class)).isInstanceOf(
        NoSuchBeanDefinitionException.class);
  }

  @Slf4j
  static class A{

    public void callA(){
      log.info("callA");
    }
  }

  @Slf4j
  static class B{

    public void callB(){
      log.info("callB");
    }
  }

  @Configuration
  static class ConfigFile{

    @Bean("beanA")
    public A call(){
      return new A();
    }

    @Bean
    public BeanPostProcessor beanPostProcessor(){
      return new BeanPostTest();
    }

  }

  static class BeanPostTest implements BeanPostProcessor{

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
        throws BeansException {

      if(bean instanceof A){
        return new B();
      }

      return bean;
    }
  }

}
