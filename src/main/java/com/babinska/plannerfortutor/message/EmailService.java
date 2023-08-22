package com.babinska.plannerfortutor.message;

import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationStudentDto;
import com.babinska.plannerfortutor.message.dto.EmailStudentInformationDto;
import com.babinska.plannerfortutor.message.mq.PublisherMq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

  private final PublisherMq publisherMq;
  // tu trzeba dodac to automatycvzne sprawdzenie pewnie bedzie trzeba dodać jesszcze jakiś lekcji żeby sprawdzić czy działą
  public Set<LessonReservationStudentDto> findAllLessonReservationToSentMessage() {
    // lesson reservation zrobic metode do znajdowaia lekcji w przedziale
    return null;
  }

  public EmailStudentInformationDto getAllInformationToSendLessonReservationMessage(Long lessonReservationId) {
    return null;
  }

  public String sendWelcomeEmail(Long studentId){
    log.info("Send email");
    return "Wecome in Our community!";
  }


}
