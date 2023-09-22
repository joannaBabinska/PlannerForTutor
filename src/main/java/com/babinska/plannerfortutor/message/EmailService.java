package com.babinska.plannerfortutor.message;

import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationStudentsDto;
import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationStudentDto;
import com.babinska.plannerfortutor.message.mq.RabbitMQJsonProducer;
import com.babinska.plannerfortutor.student.dto.StudentWelcomeMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

  private final RabbitMQJsonProducer jsonProducer;

  // tu trzeba dodac to automatycvzne sprawdzenie pewnie bedzie trzeba dodać jesszcze jakiś lekcji żeby sprawdzić czy działą
  public Set<LessonReservationStudentsDto> findAllLessonReservationToSentMessage() {
    // lesson reservation zrobic metode do znajdowaia lekcji w przedziale
    return null;
  }

  public void sendWelcomeEmail(StudentWelcomeMessageDto studentWelcomeMessageDto){
    jsonProducer.sendJsonMessage(studentWelcomeMessageDto);
    log.info("Send email");
  }

  public void sendInformEmailLessonReservation(LessonReservationStudentDto lessonReservationStudentDto){
    jsonProducer.sendJsonMessage2(lessonReservationStudentDto);
    log.info("Send email -> {}",lessonReservationStudentDto);

  }

}
