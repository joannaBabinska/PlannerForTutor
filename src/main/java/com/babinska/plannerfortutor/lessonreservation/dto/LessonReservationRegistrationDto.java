package com.babinska.plannerfortutor.lessonreservation.dto;

import com.babinska.plannerfortutor.lessonreservation.LessonType;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record LessonReservationRegistrationDto(LessonType lessonType,
                                               String topic,
                                               LocalTime startTime,
                                               LocalTime endTime,
                                               LocalDate reservationDate,
                                               @Positive int durationInMinutes,
                                               @Positive BigDecimal price) {

}
