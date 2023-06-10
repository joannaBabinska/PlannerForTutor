package com.babinska.PlannerForTutor.lessonReservation.dto;

import com.babinska.PlannerForTutor.lessonReservation.LessonType;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record LessonReservationDto (Long id,
                             LessonType lessonType,
                             String topic,
                             LocalDateTime startTime,
                             LocalDateTime endTime,
                             int durationInMinutes,
                             double price) {
}

