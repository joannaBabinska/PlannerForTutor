package com.babinska.plannerfortutor.lessonreservation.dto;

import com.babinska.plannerfortutor.lessonreservation.LessonType;
import com.babinska.plannerfortutor.student.dto.StudentDto;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Builder
public record LessonReservationStudentDto
        (Long id,
         LessonType lessonType,
         String topic,
         Set<StudentDto> students,
         LocalTime startTime,
         LocalTime endTime,
         LocalDate reservationDate,
         int durationInMinutes,
         BigDecimal price) {
}
