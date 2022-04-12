package hello.exception.resolver;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {

    log.info("=============tetsetset====");

    try {
      if (ex instanceof IllegalArgumentException) {
        log.info("IllegalArgumentException resolver to 400");
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());

        //return ModelAndView를 하면 그냥 에러로 인식해서 was까지 올라간후 500에러를 낸다.
        //하지만 ModelAndView를 리턴하면 정상 흐름으로 인식해서 정상적인 흐름으로 was까지 올라갔을때 response를 보고 특정 에러가 있네 그럼 그 에러를 제공해줄께
        //BasicController가 자동으로 에러에 맞는

        return new ModelAndView();
      }
    }catch(IOException e){
      log.error("resolver ex",e);
    }

    return null;
  }
}
