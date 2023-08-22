package com.babinska.plannerfortutor.message.mq;

import com.babinska.plannerfortutor.message.Email;
import com.babinska.plannerfortutor.student.dto.StudentWelcomeMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQJsonProducer {

  @Value("${rabbitmq.exchange.name}")
  private String exchange;

  @Value("${rabbitmq.routing.json.key}")
  private String routingJsonKey;

  private final RabbitTemplate rabbitTemplate;

  public void sendJsonMessage(Email email){
    log.info(String.format("Json message sent -> %s", email.toString()));
    rabbitTemplate.convertAndSend(exchange, routingJsonKey, email);
  }
  public void sendWelcomeMessage(StudentWelcomeMessageDto studentWelcomeMessageDto){
    log.info(String.format("Json message sent -> %s", studentWelcomeMessageDto.toString()));
    rabbitTemplate.convertAndSend(exchange, routingJsonKey, studentWelcomeMessageDto);
  }

}
