package com.babinska.plannerfortutor.message.dto;

import lombok.Data;


public record EmailDto(String studentEmail,
                       String topic,
                       String message
                       ) {
}
