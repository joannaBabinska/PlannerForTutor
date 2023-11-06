package com.babinska.plannerfortutor.email.student;

import com.babinska.plannerfortutor.email.EmailType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class StudentGoodbyeEmail implements StudentEmail {

  private final String to;
  private final String name;

  @Override
  public String getTo() {
    return null;
  }

  @Override
  public String getSubject() {
    return "Email pożegnalny";
  }

  @Override
  public String getBody() {
    return """
        Szkoda że odchodzisz!
        Do zobaczenia z powrotem %s
        Pozdrawiamy, zespół Planner.
        """.formatted(getStudentFirstName());
  }

  @Override
  public EmailType getEmailType() {
    return EmailType.STUDENT_GOODBYE_EMAIL;
  }

  @Override
  public String getStudentFirstName() {
    return null;
  }

}
