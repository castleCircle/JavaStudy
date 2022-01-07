package hello.springmvc.basic.requestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {

  private Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping({"/hello-basic" , "/hello-go"})
  public String helloBasic(){
    log.info("helloBasic");
    return "ok";
  }

  @GetMapping("/mapping/{userId}")
  public String mappingPath(@PathVariable("userId") String data){
    log.info(data);
    return "ok";
  }

  //consumes : 서버가 받는 포맷
  //produces : 서버가 보내는 포맷 => 클라이언트가 보낼때 header의 accept에 해당함

  @PostMapping(value = "/mapping-consume",consumes = MediaType.APPLICATION_JSON_VALUE)
  public String mappingConsumes(){
    System.out.println("test");
    return "ok";
  }
}
