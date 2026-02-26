package com.newgen.employee_management.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class EmployeeDto {
    private Long  empId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String designation;
    private BigDecimal salary;
    private LocalDate dob;
    private LocalDate jd;
}
