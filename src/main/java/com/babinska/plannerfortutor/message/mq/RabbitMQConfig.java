package com.babinska.plannerfortutor.message.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Value("${rabbitmq.queue.json.name}")
  private String jsonQueue;

  @Value("${rabbitmq.exchange.name}")
  private String exchange;

  @Value("${rabbitmq.routing.json.key}")
  private String routingJsonKey;

  @Value("${rabbitmq.queue.json.name2}")
  private String jsonQueue2;

  @Value("${rabbitmq.exchange.name2}")
  private String exchange2;

  @Value("${rabbitmq.routing.json.key2}")
  private String routingJsonKey2;

  @Bean
  public org.springframework.amqp.core.Queue jsonQueue() {
    return new org.springframework.amqp.core.Queue(jsonQueue);
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(exchange);
  }

  @Bean
  public Binding jsonBinding() {
    return BindingBuilder
            .bind(jsonQueue())
            .to(exchange())
            .with(routingJsonKey);
  }

  @Bean
  public org.springframework.amqp.core.Queue jsonQueue2() {
    return new org.springframework.amqp.core.Queue(jsonQueue2);
  }

  @Bean
  public TopicExchange exchange2() {
    return new TopicExchange(exchange2);
  }

  @Bean
  public Binding jsonBinding2() {
    return BindingBuilder
            .bind(jsonQueue2())
            .to(exchange2())
            .with(routingJsonKey2);
  }

  @Bean
  public MessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(converter());
    return rabbitTemplate;
  }

}