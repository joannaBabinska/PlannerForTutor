package com.babinska.plannerfortutor.email;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Email {

  default String getEmailIdentifier() {
    return UUID.randomUUID().toString();
  }

  default LocalDateTime getPublicationDateTime() {
    return LocalDateTime.now();
  }

  default String getFrom() {
    return "planner@planner.com";
  }

  String getTo();

  String getSubject();

  String getBody();

  EmailType getEmailType();

}
