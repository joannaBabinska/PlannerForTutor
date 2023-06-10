package com.babinska.PlannerForTutor.lessonReservation.dto;

import com.babinska.PlannerForTutor.lessonReservation.LessonType;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record LessonReservationRegistrationDto(LessonType lessonType,
                                              String topic,
                                              LocalDateTime startTime,
                                              LocalDateTime endTime,
                                              int durationInMinutes,
                                              double price) {

}
