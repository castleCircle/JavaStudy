package hello.servlet.web.frontcontroller.v1test;

import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletTestV1", urlPatterns = "/front-controller/test/v1/*")
public class FrontControllerServletTestV1 extends HttpServlet {

  private Map<String, ControllerV1> controllerMap = new HashMap<>();

  public FrontControllerServletTestV1(){
    controllerMap.put("/front-controller/test/v1/members/new-form",new MemberFormControllerV1());
    controllerMap.put("/front-controller/test/v1/members/save",new MemberSaveControllerV1());
    controllerMap.put("/front-controller/test/v1/members",new MemberListControllerV1());
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("===================Test Sample");

    String urI = req.getRequestURI();
    String urL = req.getRequestURI();
    System.out.println(urI);
    System.out.println(urL);

    ControllerV1 controllerV1 = controllerMap.get(urI);
    controllerV1.process(req,resp);
  }
}
