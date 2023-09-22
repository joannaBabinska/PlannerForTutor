package com.babinska.plannerfortutor.message.mq;

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

  @Value("${rabbitmq.exchange.name2}")
  private String exchange2;

  @Value("${rabbitmq.routing.json.key2}")
  private String routingJsonKey2;

  private final RabbitTemplate rabbitTemplate;

  public void sendJsonMessage(Object object) {
    log.info("Json message sent -> {}", object);
    rabbitTemplate.convertAndSend(exchange, routingJsonKey, object);
  }

  public void sendJsonMessage2(Object object) {
    log.info("Json message sent -> {}", object);
    rabbitTemplate.convertAndSend(exchange2, routingJsonKey2, object);
  }

}
