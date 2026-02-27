package com.newgen.employee_management.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorResponse<T> {
    private boolean success;
    private String errorCode;
    private String errorMessage;
    private String message;
    private T data;
    private String uri;
    private LocalDateTime timestamp;
}
