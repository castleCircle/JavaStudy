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
public class SessionManager1 {

  private static final String SESSION_ID = "sessionId";
  private Map<String,Object> sessionStorage = new ConcurrentHashMap<>();

  public void createSession(Object value, HttpServletResponse response){
    final var sessionID = UUID.randomUUID().toString();
    sessionStorage.put(sessionID,value);

    Cookie cookie = new Cookie(SESSION_ID,sessionID);
    response.addCookie(cookie);
  }

  public Object getSession(HttpServletRequest request){
    final var cookie = getFind(request);
    if(cookie == null){
      return null;
    }
    return sessionStorage.get(cookie.getName());
  }

  private Cookie getFind(HttpServletRequest request){
    Cookie[] cookies = request.getCookies();
    return Arrays.stream(cookies)
        .filter(cookie -> cookie.getName().equals(SESSION_ID))
        .findAny()
        .orElse(null);
  }

  public void expired(HttpServletRequest request){
    Cookie cookie = getFind(request);
    if(cookie != null){
      sessionStorage.remove(cookie.getName());
    }
  }

}
