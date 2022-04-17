package hello.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exception.TestException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class TestHandlerExceptionResolver implements HandlerExceptionResolver {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {

    try {
      if (ex instanceof TestException) {

        final String accept = request.getHeader("accept");

        final Map<String,Object> result = new HashMap<>();
        result.put("input","Test");
        result.put("test",true);

        String message = objectMapper.writeValueAsString(result);

        log.info("========accept:{}=======", accept);

        if (accept.equals(MediaType.APPLICATION_JSON_VALUE)) {

          response.setContentType(MediaType.APPLICATION_JSON_VALUE);
          response.setCharacterEncoding("UTF-8");
          response.getWriter().write(message);
          return new ModelAndView();
        } else {
          return new ModelAndView("error/500");
        }
      }
    }catch(IOException e){
      log.error("logger ex",e);
    }

    return null;
  }
}
