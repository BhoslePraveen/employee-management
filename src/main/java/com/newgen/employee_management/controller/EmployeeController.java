package com.newgen.employee_management.controller;

import com.newgen.employee_management.model.Employee;
import com.newgen.employee_management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    /* CREATING DATA ON SERVER */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee emp = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(emp);
    }

    /* READING DATA ON SERVER */
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(
            @PathVariable(name = "id") Long empId) {
        Employee employee = employeeService.getEmployeeById(empId);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(
            @RequestBody Employee employee, @PathVariable(name = "employeeId") Long empId) {
        Employee updatedEmployee = employeeService.updateEmployee(empId,employee);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
    }

    @PatchMapping("/{employeeId}/salary")
    public ResponseEntity<Employee> updateEmployeeSalary(
            @PathVariable(name = "employeeId") Long empId, @RequestParam BigDecimal salary) {
        Employee employee = employeeService.updateEmployeeSalary(empId,salary);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }


}
