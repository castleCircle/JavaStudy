package sample.cafekiosk.spring;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.client.mail.MailSendClient;


/**
 * @DataJpaTest는 @Transacional이 붙어있지만 @springboottest는 없기 때문에 사용하는곳에서 별도로 추가
 */
@ActiveProfiles("test")
@SpringBootTest
public abstract class IntegrationTestSupport {

  @MockBean
  protected MailSendClient mailSendClient;

}
