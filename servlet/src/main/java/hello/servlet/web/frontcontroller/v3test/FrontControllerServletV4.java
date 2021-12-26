package hello.servlet.web.frontcontroller.v3test;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3test.controller.MemberFormControllerTestV3;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletTestV3",urlPatterns = "/front-controller/v3/test/*")
public class FrontControllerServletV4 extends HttpServlet {

  private Map<String,ControllerTestV3> controllerMap = new HashMap<>();

  public FrontControllerServletV4(){
    controllerMap.put("/front-controller/v3/test/members/new-form",new MemberFormControllerTestV3());
    controllerMap.put("/front-controller/v3/test/members/save",new MemberFormControllerTestV3());
    controllerMap.put("/front-controller/v3/test/members",new MemberFormControllerTestV3());
  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String url = request.getRequestURI();

    ControllerTestV3 controller = controllerMap.get(url);

    if(controller == null){
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    Map<String,String> paramMap = new HashMap<>();
    request.getParameterNames().asIterator()
        .forEachRemaining(paramName -> {
          paramMap.put(paramName,request.getParameter(paramName));
        });

    ModelView modelView = controller.process(paramMap);
    String viewName = modelView.getViewName();
    MyView view = viewResolver(viewName);

    view.render(modelView.getModel(),request,response);

  }

  private MyView viewResolver(String viewName){
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }


}
