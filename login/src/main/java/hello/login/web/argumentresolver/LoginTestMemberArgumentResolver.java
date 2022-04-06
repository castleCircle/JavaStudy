package hello.login.web.argumentresolver;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginTestMemberArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    final boolean loginParameter = parameter.hasParameterAnnotation(Login.class);
    final boolean assignableFrom = Member.class.isAssignableFrom(parameter.getParameterType());

    return loginParameter && assignableFrom;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

    final HttpServletRequest nativeRequest = (HttpServletRequest) webRequest.getNativeRequest();
    HttpSession session = nativeRequest.getSession(false);

    if(session == null){
      return null;
    }

    return session.getAttribute(SessionConst.LOGIN_MEMBER);
  }
}
