package com.babinska.plannerfortutor.message.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PublisherMq {

  private final RabbitTemplate rabbitTemplate;

  @GetMapping("/addMessage")
  public String get(@RequestParam String message) {
    rabbitTemplate.convertAndSend("email",message);
    return "sent";

  }
}
