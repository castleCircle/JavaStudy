package hello.servlet.web.frontcontroller.v4test.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4test.ControllerV4Test;
import java.util.List;
import java.util.Map;

public class MemberListControllerV4Test implements ControllerV4Test {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public String process(Map<String, String> paramMap, Map<String, Object> model) {
    List<Member> members = memberRepository.findAll();
    model.put("members",members);
    return "members";
  }
}
