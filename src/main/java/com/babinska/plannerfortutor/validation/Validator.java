package com.babinska.plannerfortutor.validation;

import com.babinska.plannerfortutor.daysoff.DaysOffService;
import com.babinska.plannerfortutor.exception.EndTimeIsBeforeStartTimeException;
import com.babinska.plannerfortutor.exception.LessonOverlapException;
import com.babinska.plannerfortutor.lessonoverlap.LessonOverlapService;
import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
@Component
public class Validator {

  private final DaysOffService daysOffService;
  private final LessonOverlapService lessonOverlapService;

  public void validate(LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    checkLessonOverlap(lessonReservationRegistrationDto.startTime(),
                          lessonReservationRegistrationDto.endTime(),
                          lessonReservationRegistrationDto.reservationDate());
    checkDaysOff(lessonReservationRegistrationDto.reservationDate());
    checkStartEndTime(lessonReservationRegistrationDto.startTime(),
                      lessonReservationRegistrationDto.endTime());
  }

  private void checkStartEndTime(LocalTime startTime, LocalTime endTime) {
    if (endTime.isBefore(startTime)) {
      throw new EndTimeIsBeforeStartTimeException();
    }
  }

  private void checkDaysOff(LocalDate reservationDate) {
    daysOffService.isDayWorking(reservationDate);
  }

  private void checkLessonOverlap(LocalTime startTime, LocalTime endTime, LocalDate reservationDate) {
  if(lessonOverlapService.isOverlap(LocalDateTime.of(reservationDate, startTime), LocalDateTime.of(reservationDate, endTime)))
    throw new LessonOverlapException();
  }
}
