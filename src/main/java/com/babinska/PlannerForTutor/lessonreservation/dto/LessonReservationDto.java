package com.babinska.PlannerForTutor.lessonreservation.dto;

import com.babinska.PlannerForTutor.lessonreservation.LessonType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record LessonReservationDto (Long id,
                             LessonType lessonType,
                             String topic,
                             LocalTime startTime,
                             LocalTime endTime,
                             LocalDate reservationDate,
                             int durationInMinutes,
                             BigDecimal price) {
}

