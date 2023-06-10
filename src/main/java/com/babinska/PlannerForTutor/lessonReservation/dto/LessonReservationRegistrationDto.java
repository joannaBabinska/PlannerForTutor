package com.babinska.PlannerForTutor.lessonReservation.dto;

import com.babinska.PlannerForTutor.lessonReservation.LessonType;
import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record LessonReservationRegistrationDto(LessonType lessonType,
                                              String topic,
                                              LocalTime startTime,
                                              LocalTime endTime,
                                              LocalDate reservationDate,
                                              int durationInMinutes,
                                              double price) {

}
