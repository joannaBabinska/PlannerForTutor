package com.babinska.plannerfortutor.message.dto;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record EmailRegistrationDto(Long studentId,
                                   String studentEmail,
                                   String studentName,
                                   String topic,
                                   LocalDateTime messageTime) {
}
