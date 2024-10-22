package com.example.demo.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.mock.FakeMailSender;
import com.example.demo.user.service.port.MailSender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CertificationServiceTest {

  @Test
  public void 이메일과_컨텐츠가_제대로_만들어져서_보내지는지_테스트한다(){
    FakeMailSender fakeMailSender = new FakeMailSender();
    CertificationService certificationService = new CertificationService(fakeMailSender);

    //when
    certificationService.send("kok202@naver.com",1,"aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");

    //then
    assertThat(fakeMailSender.email).isEqualTo("kok202@naver.com");
    assertThat(fakeMailSender.title).isEqualTo("Please certify your email address");

  }

}