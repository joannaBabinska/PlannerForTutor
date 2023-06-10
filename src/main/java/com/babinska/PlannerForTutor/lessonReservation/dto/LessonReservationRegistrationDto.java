package com.babinska.PlannerForTutor.lessonReservation.dto;

import com.babinska.PlannerForTutor.lessonReservation.LessonType;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record LessonReservationRegistrationDto(LessonType lessonType,
                                              String topic,
                                              LocalDateTime startTime,
                                              LocalDateTime endTime,
                                              @Positive int durationInMinutes,
                                              @Positive double price) {

}
