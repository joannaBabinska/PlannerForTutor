package com.babinska.plannerfortutor.lessonreservation.dto;

import com.babinska.plannerfortutor.lessonreservation.LessonType;
import com.babinska.plannerfortutor.student.dto.StudentWelcomeMessageDto;
import lombok.Builder;
import java.math.BigDecimal;


@Builder
public record LessonReservationStudentDto (Long id,
                                           LessonType lessonType,
                                           String topic,
                                           StudentWelcomeMessageDto student,
                                           String startTime,
                                           String endTime,
                                           String reservationDate,
                                           int durationInMinutes,
                                           BigDecimal price){
}
