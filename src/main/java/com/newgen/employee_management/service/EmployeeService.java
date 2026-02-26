package com.newgen.employee_management.service;

import com.newgen.employee_management.dto.request.EmployeeRequestDto;
import com.newgen.employee_management.dto.response.EmployeeDto;
import com.newgen.employee_management.model.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeRequestDto employee);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long empId);

    EmployeeDto updateEmployee(Long empId, Employee employee);

    EmployeeDto updateEmployeeSalary(Long empId, BigDecimal salary);

    void deleteEmployeeById(Long empId);
}
