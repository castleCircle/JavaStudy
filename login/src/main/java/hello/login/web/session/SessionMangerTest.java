package hello.login.web.session;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SessionMangerTest {

  public static final String SESSION_COOKIE_NAME = "mySessionID";
  private Map<String,Object> sessionStore = new ConcurrentHashMap<>();

  public void createSession(Object value, HttpServletResponse response){
    String key = UUID.randomUUID().toString();
    sessionStore.put(key,value);

    response.addCookie(new Cookie(SESSION_COOKIE_NAME,key));
  }

  public Object findSession(HttpServletRequest request){
    Cookie cookie = findCookie(request);
    if(cookie==null){
      return null;
    }

    return sessionStore.get(cookie.getValue());
  }

  public void expire(HttpServletRequest request){
    Cookie cookie = findCookie(request);
    if(cookie!=null){
      sessionStore.remove(cookie.getValue());
    }
    log.info("sessionStore Size:{}",sessionStore.size());
  }

  private Cookie findCookie(HttpServletRequest request){
    Cookie[] cookies = request.getCookies();

    if(cookies == null){
      return null;
    }

    return Arrays.stream(cookies).filter(cookie ->
        cookie.getName().equals(SESSION_COOKIE_NAME)
    ).findAny().orElse(null);
  }

}
