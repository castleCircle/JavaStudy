package hello.exception.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ServletExController {

  //내가 의도하지 않은 예외는 500
  //내가 의도해서 reponse.error는 내가 담아준 코드에 맞도록 에러가 남

  @GetMapping("/error-ex")
  public void errorEx(){
    throw new RuntimeException("예외 발생");
  }

  @GetMapping("/error-404")
  public void error404(HttpServletResponse response) throws IOException {
    response.sendError(404,"404 오류!");
  }

  @GetMapping("/error-500")
  public void error500(HttpServletResponse response) throws IOException {
    response.sendError(500);
  }
}
