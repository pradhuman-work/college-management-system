package com.collegeManagementSystem.learning.advices;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;
}
