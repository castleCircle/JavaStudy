package com.example.sender.module;


import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiverModule {

  @RabbitListener(bindings = @QueueBinding(
      exchange = @Exchange(name="dept",type= ExchangeTypes.TOPIC),
      value= @Queue(name="dept-four"),
      key="dept-third"
  ))
  public void receiver(Dept dept){
    System.out.println("4 ===> " + dept.toString());
  }

}
