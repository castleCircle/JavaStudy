package hello.servlet.web.frontcontroller.v4test.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4test.ControllerV4Test;
import java.util.Map;

public class MemberFormControllerV4Test implements ControllerV4Test {

  @Override
  public String process(Map<String, String> paramMap, Map<String, Object> model) {
    return "new-form";
  }
}
