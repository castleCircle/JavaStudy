package hello.servlet.web.frontcontroller.v6;

import hello.servlet.web.frontcontroller.ModelView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyTestHandlerAdapter {

  boolean supports(Object handler);

  ModelView handler(HttpServletRequest request, HttpServletResponse response, Object handler);

}
