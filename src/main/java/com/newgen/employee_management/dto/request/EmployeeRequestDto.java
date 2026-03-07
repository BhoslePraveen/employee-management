package com.newgen.employee_management.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class EmployeeRequestDto {
    @NotBlank(message = "First name is Required")
    private String firstName;

    @NotBlank(message = "Last name is Required")
    private String lastName;

    @Email(message = "Invalid Email Details")
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Mobile Number is Required")
    @Size(min = 10, max = 10, message = "Invalid Mobile Number")
    private String phoneNumber;

    @NotBlank(message = "Designation is required")
    private String designation;

    @Past
    private LocalDate dateOfBirth;

    @FutureOrPresent
    private LocalDate joiningDate;
}
