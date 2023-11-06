package com.babinska.plannerfortutor.email.student;

import com.babinska.plannerfortutor.email.EmailType;

import java.time.LocalDateTime;
import java.util.UUID;

class StudentWelcomeEmail implements StudentEmail {

  public static final String WELCOME_EMAIL_MESSAGE_SUBJECT = "Email powitalny";
  public static final String WELCOME_EMAIL_MESSAGE_TEMPLATE = """
      Witaj w Planner!
      Cześć %s, dziękujemy za rejestrację.
      Pozdrawiamy, zespół Planner.
      """;

  private final String to;
  private final String name;
  private final String subject;
  private final String body;
  private final EmailType emailType;

  public StudentWelcomeEmail(final String to, final String name) {
    this.to = to;
    this.name = name;
    this.subject =
    this.body = WELCOME_EMAIL_MESSAGE_TEMPLATE.formatted(name);
    this.emailType = EmailType.STUDENT_WELCOME_EMAIL;
  }

  @Override
  public String getTo() {
    return to;
  }

  @Override
  public String getSubject() {
    return subject;
  }

  @Override
  public String getBody() {
    return body;
  }

  @Override
  public EmailType getEmailType() {
    return emailType;
  }

  @Override
  public String getStudentFirstName() {
    return name;
  }
}
