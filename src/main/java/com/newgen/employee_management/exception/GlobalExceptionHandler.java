package com.newgen.employee_management.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ApiErrorResponse<String>> handleEmployeeException
            (EmployeeException ex, HttpServletRequest request) {
        // Create the response first
        ApiErrorResponse<String> response = ApiErrorResponse.<String>builder()
                .success(false)
                .message("Client Error")
                .errorCode(ex.getErrorCode())
                .data(ex.getErrorMessage())
                .uri(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        // Create the Response Entity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse<Object>> handleValidationException
            (MethodArgumentNotValidException ex, HttpServletRequest request) {

        // First extract the validation errors
        Map<String, String> errorData = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(fe -> fe.getField(), fe -> fe.getDefaultMessage()));

        ApiErrorResponse<Object> response = ApiErrorResponse.builder()
                .success(false)
                .message("Client Error")
                .errorCode("VALIDATION_ERROR")
                .data(errorData)
                .uri(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
