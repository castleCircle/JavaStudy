package hello.servlet.web.frontcontroller.v3test;

import hello.servlet.web.frontcontroller.ModelView;
import java.util.Map;

public interface ControllerTestV3 {

  ModelView process(Map<String,String> paramMap);

}
