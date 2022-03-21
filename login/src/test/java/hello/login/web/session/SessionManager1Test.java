package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class SessionManager1Test {

  SessionManager sessionManager = new SessionManager();

  @Test
  public void test(){

    MockHttpServletResponse response = new MockHttpServletResponse();
    Member m = new Member();
    sessionManager.createSession(m,response);

    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setCookies(response.getCookies());

    final var member = sessionManager.getSession(request);
    Assertions.assertThat(member).isEqualTo(m);

    sessionManager.expire(request);
    final var member1 = sessionManager.getSession(request);
    Assertions.assertThat(member1).isNull();


  }

}
