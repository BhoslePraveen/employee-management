package com.newgen.employee_management.service;

import java.time.LocalDate;

import com.newgen.employee_management.dto.request.EmployeeRequestDto;
import com.newgen.employee_management.dto.response.EmployeeDto;
import com.newgen.employee_management.model.Employee;
import com.newgen.employee_management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeRequestDto employeeDto) {
        Employee savedEmployee = employeeRepository.save(toEntity(employeeDto));
        return toDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        log.info("START:EmployeeServiceImpl-->getAllEmployees");
        List<Employee> employees = employeeRepository.findAll();
        log.info("END:EmployeeServiceImpl-->getAllEmployees");
        return employees.
                stream()
                .map(emp -> toDto(emp))
                .collect(Collectors.toList());

    }

    @Override
    public EmployeeDto getEmployeeById(Long empId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        return optionalEmployee.map(emp -> toDto(emp)).orElse(null);
    }

    @Override
    public EmployeeDto updateEmployee(Long empId, Employee employee) {
        employee.setId(empId);
        Employee updatedEmployee = employeeRepository.save(employee);
        return toDto(updatedEmployee);
    }

    @Override
    public EmployeeDto updateEmployeeSalary(Long empId, BigDecimal salary) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setSalary(salary);
            Employee updatedEmp = employeeRepository.save(employee);
            return toDto(updatedEmp);
        } else {
            throw new RuntimeException("Employee not found");
        }
    }

    @Override
    public void deleteEmployeeById(Long empId) {
        employeeRepository.deleteById(empId);
    }

    EmployeeDto toDto(Employee employee) {
        return EmployeeDto.builder()
                .empId(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .email(employee.getEmail())
                .designation(employee.getDesignation())
                .phoneNumber(employee.getPhoneNumber())
                .dob(employee.getDateOfBirth())
                .jd(employee.getJoiningDate())
                .build();
    }

    Employee toEntity(EmployeeRequestDto dto) {
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setDesignation(dto.getDesignation());
        employee.setSalary(new BigDecimal("0"));
        employee.setDateOfBirth(dto.getDateOfBirth());
        employee.setJoiningDate(dto.getJoiningDate());
        return employee;
    }
}
