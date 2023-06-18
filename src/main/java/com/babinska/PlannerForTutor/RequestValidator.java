package com.babinska.PlannerForTutor;

import com.babinska.PlannerForTutor.daysoff.DaysOffService;
import com.babinska.PlannerForTutor.exception.DayIsNotWorkingException;
import com.babinska.PlannerForTutor.exception.EndTimeIsBeforeStartTimeException;
import com.babinska.PlannerForTutor.lessonoverlap.LessonOverlapService;
import com.babinska.PlannerForTutor.lessonreservation.dto.LessonReservationRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
@Component
public class RequestValidator {

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
    if (!daysOffService.isDayWorking(reservationDate)) {
      throw new DayIsNotWorkingException();
    }
  }

  private void checkLessonOverlap(LocalTime startTime, LocalTime endTime, LocalDate reservationDate) {
  lessonOverlapService.isOverlap(LocalDateTime.of(reservationDate, startTime), LocalDateTime.of(reservationDate, endTime));
  }
}
