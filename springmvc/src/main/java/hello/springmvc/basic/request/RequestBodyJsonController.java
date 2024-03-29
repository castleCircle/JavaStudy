package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestBodyJsonController {

  private ObjectMapper objectMapper = new ObjectMapper();

  @PostMapping("/request-body-json-v1")
  public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    log.info("messageBdoy={}",messageBody);
    HelloData helloData = objectMapper.readValue(messageBody,HelloData.class);
    log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());

    response.getWriter().write("ok");
  }

  @ResponseBody
  @PostMapping("/request-body-json-v2")
  public String requestBodyJsonV2(@RequestBody String messageBody)
      throws IOException {
    log.info("messageBdoy={}",messageBody);
    HelloData helloData = objectMapper.readValue(messageBody,HelloData.class);
    log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());

    return "ok";
  }

  @ResponseBody
  @PostMapping("/request-body-json-v3")
  public String requestBodyJsonV3(@RequestBody HelloData helloData)
      throws IOException {
    log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());

    return "ok";
  }

  @ResponseBody
  @PostMapping("/request-body-json-v4")
  public String requestBodyJsonV4(HttpEntity<HelloData> helloData)
      throws IOException {
    HelloData helloData1 = helloData.getBody();
    log.info("username={}, age={}",helloData1.getUsername(),helloData1.getAge());

    return "ok";
  }

  //@RequestBody , HttpEntity
  //JSON 요청 => HTTP 컨버터 => 객체


  //@ResponseBody
  //객체 => HTTP 컨버터 => JSON 요청

  /**
   * @RequestBody , HttpEntity
   * 핸들러 어댑터에서 Controller(핸들러)를 실행하기전에 컨트롤러의 메소드 아규먼트에 적절한 형태로 데이터를 주기 위해
   * Argument Resolver가 동작을 하게 된다.
   * 그때 Http 메시지 컨버터를 사용한다.
   * 메시지 컨버터의 종류로는 byte, String , 객체(사용자지정) ... 등이 있다.
   * 객체는 content-type이 application/json 이여야 하지만 byte, String은 미디어 타입이 상관없다.
   */

}
