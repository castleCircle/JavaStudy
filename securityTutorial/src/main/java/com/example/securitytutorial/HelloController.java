package com.example.securitytutorial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello(){
    System.out.println("sss");
    return "Hello";
  }

  @GetMapping("/ciao")
  public String ciao(){
    return "Ciao!";
  }

  @GetMapping("/hola")
  public String hola(){
    return "hola!";
  }

}
