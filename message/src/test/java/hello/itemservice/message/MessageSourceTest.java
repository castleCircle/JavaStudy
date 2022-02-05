package hello.itemservice.message;

import java.util.Locale;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

@SpringBootTest
public class MessageSourceTest {

  @Autowired
  MessageSource messageSource;

  @Test
  void helloMessage(){
    String result = messageSource.getMessage("hello",null,null);
    Assertions.assertThat(result).isEqualTo("안녕");
  }

  @Test
  void notFountMessageCode(){
    Assertions.assertThatThrownBy(()-> messageSource.getMessage("no_code",null,null)).isInstanceOf(
        NoSuchMessageException.class);
  }

  @Test
  void notFoundDefaultMessageCode(){
    String test = messageSource.getMessage("no_code",null,"default message",null);
    Assertions.assertThat(test).isEqualTo("default message");
  }

  @Test
  void argumentMessage(){
    String message = messageSource.getMessage("hello.name",new Object[]{"spring"},null);
    Assertions.assertThat(message).isEqualTo("안녕 spring");
  }

  @Test
  void defaultLang(){
    Assertions.assertThat(messageSource.getMessage("hello",null,null)).isEqualTo("안녕");
    Assertions.assertThat(messageSource.getMessage("hello",null, Locale.US)).isNotEqualTo("안녕");
    Assertions.assertThat(messageSource.getMessage("hello",null, Locale.US)).isEqualTo("hello");
  }
}
