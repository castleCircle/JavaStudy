package hello.servlet.web.frontcontroller.v4test;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v4test.controller.MemberFormControllerV4Test;
import hello.servlet.web.frontcontroller.v4test.controller.MemberListControllerV4Test;
import hello.servlet.web.frontcontroller.v4test.controller.MemberSaveControllerV4Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="frontControllerServletV4Test", urlPatterns = "/front-controller/v4test/*")
public class FrontControllerServletV4Test extends HttpServlet {

  private Map<String, ControllerV4Test> controllerMap = new HashMap<>();

  public FrontControllerServletV4Test(){
    controllerMap.put("/front-controller/v4test/members/new-form",new MemberFormControllerV4Test());
    controllerMap.put("/front-controller/v4test/members/save",new MemberSaveControllerV4Test());
    controllerMap.put("/front-controller/v4test/members",new MemberListControllerV4Test());
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String url = request.getRequestURI();

    ControllerV4Test controller = controllerMap.get(url);

    if(controller == null){
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    Map<String, String> paramMap = createParamMap(request);
    Map<String,Object> model = new HashMap<>();
    String viewName = controller.process(paramMap,model);

    MyView view = viewResolver(viewName);
    view.render(model,request,response);
  }

  private MyView viewResolver(String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }

  private Map<String, String> createParamMap(HttpServletRequest request) {
    Map<String,String> paramMap = new HashMap<>();
    request.getParameterNames().asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)) );
    return paramMap;
  }
}
