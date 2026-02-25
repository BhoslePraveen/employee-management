package com.newgen.employee_management.service;

import com.newgen.employee_management.model.Employee;
import com.newgen.employee_management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        return optionalEmployee.orElse(null);
    }

    @Override
    public Employee updateEmployee(Long empId, Employee employee) {
        employee.setId(empId);
        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }

    @Override
    public Employee updateEmployeeSalary(Long empId, BigDecimal salary) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        if(optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            employee.setSalary(salary);
            Employee updatedEmp = employeeRepository.save(employee);
            return updatedEmp;
        } else {
            throw new RuntimeException("Employee not found");
        }
    }
}
