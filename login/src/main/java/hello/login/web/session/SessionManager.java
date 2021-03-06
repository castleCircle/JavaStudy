package hello.login.web.session;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {

  public static final String SESSION_COOKIE_NAME = "mySessionID";
  private Map<String,Object> sessionStore = new ConcurrentHashMap<>();

  /**
   * 세션 생성
   * sessionId 생성
   * 세션 저장소에 sessionId와 보관할값 저장
   */
  public void createSession(Object value, HttpServletResponse response){
    String sessionId = UUID.randomUUID().toString();
    sessionStore.put(sessionId,value);

    //쿠키 생성
    Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME,sessionId);
    response.addCookie(mySessionCookie);
  }

  /**
   * 세션조회
   */
  public Object getSession(HttpServletRequest request){
    Cookie sessionCookie = findCookie(request,SESSION_COOKIE_NAME);
    if(sessionCookie == null){
      return  null;
    }
    return sessionStore.get(sessionCookie.getValue());
  }

  /**
   * 세션말료
   * @param request
   * @param cookieName
   * @return
   */
  public void expire(HttpServletRequest request){
    Cookie sessionCookie= findCookie(request,SESSION_COOKIE_NAME);
    if(sessionCookie != null){
      sessionStore.remove(sessionCookie.getValue());
    }
  }

  public Cookie findCookie(HttpServletRequest request,String cookieName){
    Cookie[] cookies = request.getCookies();
    if(cookies == null){
      return  null;
    }

    return Arrays.stream(cookies)
        .filter(cookie -> cookie.getName().equals(cookieName))
        .findAny()
        .orElse(null);
  }

}
