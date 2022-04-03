package hello.login.web.filter;

import hello.login.web.SessionConst;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogTestFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("LogTestFilter Init");
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    log.info("LogTestFilter DoFilter");
    final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    final HttpSession session = httpServletRequest.getSession(false);
    final HttpServletResponse httpServletResponse = (HttpServletResponse)response;
    final var redirectURL = httpServletRequest.getRequestURI();

    if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) !=null){
      httpServletResponse.sendRedirect("/login?redirectURL="+redirectURL);
      return;
    }

    chain.doFilter(request,response);
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
