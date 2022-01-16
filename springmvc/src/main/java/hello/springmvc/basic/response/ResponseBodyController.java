package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ResponseBodyController {

  @GetMapping("/response-body-string-v1")
  public void responseBodyV1(HttpServletResponse response) throws IOException {
    response.getWriter().write("ok");
  }


  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/response-body-json-v1")
  public HelloData responseBodyJsonV1(){
    HelloData helloData = new HelloData();
    helloData.setUsername("userA");
    helloData.setAge(20);
    return helloData;
  }

  /**
   * @ResponseBody , HttpEntity
   * 응답을 내보낼때도 ReturnValueHandler가 있다.
   * 이떄도 Http 메시지 컨버터를 사용해서 보낸다.
   */
}
