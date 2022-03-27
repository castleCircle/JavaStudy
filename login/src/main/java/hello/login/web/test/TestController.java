package hello.login.web.test;

import hello.login.web.session.SessionManager;
import hello.login.web.session.SessionMangerTest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

  private final SessionMangerTest sessionManager;

  @GetMapping("/testMain")
  public String call(HttpServletRequest request, HttpServletResponse response){

    Object o = sessionManager.findSession(request);

    if(o ==null){
      sessionManager.createSession("test",response);
      return "없음";
    }

    log.info("test:{}",(String)o);
    return "있음";
  }

  @GetMapping("/testLogout")
  public String logout(HttpServletRequest request){
    sessionManager.expire(request);
    return "로그아웃됨";
  }

}
