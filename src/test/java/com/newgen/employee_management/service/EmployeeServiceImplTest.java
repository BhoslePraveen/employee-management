package com.newgen.employee_management.service;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.newgen.employee_management.dto.request.EmployeeRequestDto;
import com.newgen.employee_management.dto.response.EmployeeDto;
import com.newgen.employee_management.model.Employee;
import com.newgen.employee_management.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.BDDMockito;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository  employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    private Employee employee;
    private EmployeeRequestDto employeeDto;

    @BeforeEach
    public void setUp(){
        employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Praveen");
        employee.setLastName("Bhosle");
        employee.setEmail("Praveen@gmail.com");
        employee.setPhoneNumber("7412589632");
        employee.setDesignation("IT");
        employee.setSalary(new BigDecimal("45000"));
        employee.setDateOfBirth(LocalDate.now());
        employee.setJoiningDate(LocalDate.now());

        employeeDto = EmployeeRequestDto.builder()
                .firstName("Praveen")
                .lastName("Bhosle")
                .email("Praveen@gmail.com")
                .phoneNumber(String.valueOf(123455689))
                .designation("IT")
                .joiningDate(LocalDate.now())
                .dateOfBirth(LocalDate.now())
                .build();
    }


    @Test
    public void testGetAllEmployees(){
        // Given
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);

        BDDMockito.given(employeeRepository.findAll()).willReturn(employees);

        // When
        List<EmployeeDto> allEmployees = employeeServiceImpl.getAllEmployees();

        // Then
        Assertions.assertNotNull(allEmployees);
        Assertions.assertEquals(employees.size(), allEmployees.size());
        Assertions.assertEquals("Praveen", allEmployees.get(0).getFirstName());
    }

    @Test
    public void testCreateEmployee(){
        // Given
        BDDMockito.given(employeeRepository.save(Mockito.any(Employee.class))).willReturn(employee);

        // When
        EmployeeDto response = employeeServiceImpl.createEmployee(employeeDto);

        //Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Praveen", response.getFirstName());

        // Verify
        BDDMockito.then(employeeRepository)
                .should(Mockito.times(1))
                .save(Mockito.any(Employee.class));

    }

}
