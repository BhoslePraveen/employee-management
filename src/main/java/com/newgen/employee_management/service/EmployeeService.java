package com.newgen.employee_management.service;

import com.newgen.employee_management.model.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long empId);

    Employee updateEmployee(Long empId, Employee employee);

    Employee updateEmployeeSalary(Long empId, BigDecimal salary);
}
