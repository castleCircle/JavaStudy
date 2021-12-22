package hello.servlet.web.frontcontroller.v1test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerTestV1 {

  void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
