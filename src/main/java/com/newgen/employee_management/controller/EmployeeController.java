package com.newgen.employee_management.controller;

import com.newgen.employee_management.dto.request.EmployeeRequestDto;
import com.newgen.employee_management.dto.response.EmployeeDto;
import com.newgen.employee_management.model.Employee;
import com.newgen.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
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
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeRequestDto employee) {
        EmployeeDto emp = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(emp);
    }

    /* READING DATA ON SERVER */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(
            @PathVariable(name = "id") Long empId) {
        EmployeeDto employee = employeeService.getEmployeeById(empId);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @RequestBody Employee employee, @PathVariable(name = "employeeId") Long empId) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(empId,employee);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
    }

    @PatchMapping("/{employeeId}/salary")
    public ResponseEntity<EmployeeDto> updateEmployeeSalary(
            @PathVariable(name = "employeeId") Long empId, @RequestParam BigDecimal salary) {
        EmployeeDto employee = employeeService.updateEmployeeSalary(empId,salary);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(name = "employeeId") Long empId) {
        employeeService.deleteEmployeeById(empId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
