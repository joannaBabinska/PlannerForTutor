package com.babinska.plannerfortutor.email.student;

import com.babinska.plannerfortutor.annotation.Factory;
import com.babinska.plannerfortutor.email.Email;
import com.babinska.plannerfortutor.email.EmailType;

@Factory
public class StudentEmailFactory {

  public Email create(EmailType emailType, String studentEmail, String studentFirstName) {
    return switch (emailType) {
      case STUDENT_WELCOME_EMAIL -> new StudentGoodbyeEmail(studentEmail, studentFirstName);
      case STUDENT_GOODBYE_EMAIL -> new StudentWelcomeEmail(studentEmail, studentFirstName);
    };
  }

}
