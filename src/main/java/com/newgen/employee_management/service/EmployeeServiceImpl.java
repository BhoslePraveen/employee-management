package com.newgen.employee_management.service;

import java.time.LocalDate;

import com.newgen.employee_management.dto.request.EmployeeRequestDto;
import com.newgen.employee_management.dto.response.EmployeeDto;
import com.newgen.employee_management.mapper.EmployeeMapper;
import com.newgen.employee_management.model.Employee;
import com.newgen.employee_management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeRequestDto employeeDto) {
        Employee savedEmployee = employeeRepository.save(employeeMapper.toEntity(employeeDto));
        return employeeMapper.toDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.
                stream()
                .map(emp -> employeeMapper.toDto(emp))
                .collect(Collectors.toList());

    }

    @Override
    public EmployeeDto getEmployeeById(Long empId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        return optionalEmployee.map(emp -> employeeMapper.toDto(emp)).orElse(null);
    }

    @Override
    public EmployeeDto updateEmployee(Long empId, Employee employee) {
        employee.setId(empId);
        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(updatedEmployee);
    }

    @Override
    public EmployeeDto updateEmployeeSalary(Long empId, BigDecimal salary) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setSalary(salary);
            Employee updatedEmp = employeeRepository.save(employee);
            return employeeMapper.toDto(updatedEmp);
        } else {
            throw new RuntimeException("Employee not found");
        }
    }

    @Override
    public void deleteEmployeeById(Long empId) {
        employeeRepository.deleteById(empId);
    }

    /*EmployeeDto toDto(Employee employee) {
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
    }*/
}
