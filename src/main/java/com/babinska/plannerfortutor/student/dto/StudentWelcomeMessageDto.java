package com.babinska.plannerfortutor.student.dto;
import lombok.Builder;

@Builder
public record StudentWelcomeMessageDto(String firstName,
                                String lastName,
                                String email) {
}
