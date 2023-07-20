package com.babinska.plannerfortutor.message.mq;

import com.babinska.plannerfortutor.message.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController()
public class PublisherMq {

  private final RabbitMQJsonProducer jsonProducer;

  @PostMapping("/studentInformation")
  public ResponseEntity<Email> sendJsonMessage(@RequestBody Email email){
    jsonProducer.sendJsonMessage(email);
    return ResponseEntity.ok(email);
  }

}
