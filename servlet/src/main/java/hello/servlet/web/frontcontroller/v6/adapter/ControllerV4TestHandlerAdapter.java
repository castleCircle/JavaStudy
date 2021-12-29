package hello.servlet.web.frontcontroller.v6.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v6.MyTestHandlerAdapter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerV4TestHandlerAdapter implements MyTestHandlerAdapter {

  @Override
  public boolean supports(Object handler) {
    return handler instanceof ControllerV4;
  }

  @Override
  public ModelView handler(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    ControllerV4 controller = (ControllerV4)handler;
    Map<String,String> paramMap = createParamMap(request);
    Map<String,Object> model = new HashMap<>();
    String viewName = controller.process(paramMap,model);
    ModelView mv = new ModelView(viewName);
    mv.setModel(model);
    return mv;
  }


  private Map<String, String> createParamMap(HttpServletRequest request) {
    Map<String,String> paramMap = new HashMap<>();
    request.getParameterNames().asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)) );
    return paramMap;
  }
}
