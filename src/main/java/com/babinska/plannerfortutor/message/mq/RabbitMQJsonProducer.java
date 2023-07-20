package com.babinska.plannerfortutor.message.mq;

import com.babinska.plannerfortutor.message.Email;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class RabbitMQJsonProducer {

  @Value("${rabbitmq.exchange.name}")
  private String exchange;

  @Value("${rabbitmq.routing.json.key}")
  private String routingJsonKey;

  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

  private final RabbitTemplate rabbitTemplate;

  public void sendJsonMessage(Email email){
    LOGGER.info(String.format("Json message sent -> %s", email.toString()));
    rabbitTemplate.convertAndSend(exchange, routingJsonKey, email);
  }

}
