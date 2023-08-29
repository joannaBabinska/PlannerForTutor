package com.babinska.plannerfortutor.student.dto;
import lombok.Builder;

@Builder
public record StudentWelcomeMessageDto(Long id,
                                       String firstName,
                                       String lastName,
                                       String email) {
}
