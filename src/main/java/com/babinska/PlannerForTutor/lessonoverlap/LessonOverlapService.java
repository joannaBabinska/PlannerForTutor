package com.babinska.PlannerForTutor.lessonoverlap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LessonOverlapService {

//  private final LessonReservationRepository lessonReservationRepository;

  public boolean isOverlap(LocalDateTime startTime, LocalDateTime endTime){
//    return !lessonReservationRepository.findAll().stream()
//            .filter(lessonReservation -> lessonReservation.getStartTime().isBefore(startTime)
//                    && lessonReservation.getStartTime().isBefore(endTime))
//            .filter(lessonReservation -> lessonReservation.getEndTime().isBefore(startTime)
//                    && lessonReservation.getEndTime().isBefore(endTime))
//            .findFirst().isPresent();

    throw new UnsupportedOperationException();
  }
}
