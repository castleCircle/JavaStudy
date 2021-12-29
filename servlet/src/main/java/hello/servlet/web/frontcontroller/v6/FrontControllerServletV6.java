package hello.servlet.web.frontcontroller.v6;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v6.adapter.ControllerV3TestHandlerAdapter;
import hello.servlet.web.frontcontroller.v6.adapter.ControllerV4TestHandlerAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="frontControllerServlet6",urlPatterns = "/front-controller/v6/*")
public class FrontControllerServletV6 extends HttpServlet {

  private final Map<String,Object> handlerMappingMap = new HashMap<>();
  private final List<MyTestHandlerAdapter> handlerAdapters = new ArrayList<>();

  public FrontControllerServletV6(){
    initHandlerMappingMap();
    initHandlerAdapters();
  }

  private void initHandlerMappingMap(){
    this.handlerMappingMap.put("/front-controller/v6/v3/members/new-form",new MemberFormControllerV3());
    this.handlerMappingMap.put("/front-controller/v6/v3/members/save",new MemberSaveControllerV3());
    this.handlerMappingMap.put("/front-controller/v6/v3/members",new MemberListControllerV3());

    this.handlerMappingMap.put("/front-controller/v6/v4/members/new-form",new MemberFormControllerV4());
    this.handlerMappingMap.put("/front-controller/v6/v4/members/save",new MemberSaveControllerV4());
    this.handlerMappingMap.put("/front-controller/v6/v4/members",new MemberListControllerV4());
  }

  private void initHandlerAdapters(){
    handlerAdapters.add(new ControllerV3TestHandlerAdapter());
    handlerAdapters.add(new ControllerV4TestHandlerAdapter());
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Object handler = getHandler(req);

    if(handler == null ){
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    MyTestHandlerAdapter handlerAdapter = getHandlerAdapter(handler);
    ModelView mv = handlerAdapter.handler(req,resp,handler);

    String viewName = mv.getViewName();
    MyView view = viewResolver(viewName);

    view.render(mv.getModel(),req,resp);
  }

  private MyTestHandlerAdapter getHandlerAdapter(Object handler){
    for(MyTestHandlerAdapter adapter : handlerAdapters){
      if(adapter.supports(handler)){
        return adapter;
      }
    }
    throw new IllegalArgumentException("error");
  }

  private MyView viewResolver(String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }

  private Object getHandler(HttpServletRequest request){
    String url = request.getRequestURI();
    return handlerMappingMap.get(url);
  }
}
