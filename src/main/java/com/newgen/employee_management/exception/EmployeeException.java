package com.newgen.employee_management.exception;

import lombok.Getter;

@Getter
public class EmployeeException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public EmployeeException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public EmployeeException(String errorMessage) {
        super(errorMessage);
    }

}
