package com.example.sender.module;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@AllArgsConstructor
public class SenderModule {

  final RabbitTemplate rabbitTemplate;

//  @Scheduled(fixedRate = 1000)
  public void sender1(){

    LocalDateTime nowDateTime = LocalDateTime.now();
    String time = nowDateTime.toString();

    System.out.println("<== + time");
    rabbitTemplate.convertAndSend("time","time-first",time);
  }

  @Scheduled(fixedRate = 1000)
  public void sender2(){
    LocalDateTime nowDateTime = LocalDateTime.now();
    String time = nowDateTime.toString();

    Dept dept = new Dept(10,"test","test",time);

    System.out.println(" 1 <== +" + dept.toString());
    rabbitTemplate.convertAndSend("dept","dept-first",dept);
  }

}
