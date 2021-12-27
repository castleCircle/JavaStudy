package hello.servlet.web.frontcontroller.v4test;

import java.util.Map;

public interface ControllerV4Test {
  String process(Map<String,String> paramMap, Map<String,Object> model);
}
