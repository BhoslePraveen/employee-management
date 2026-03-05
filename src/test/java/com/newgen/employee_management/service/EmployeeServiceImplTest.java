package com.newgen.employee_management.service;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.newgen.employee_management.dto.response.EmployeeDto;
import com.newgen.employee_management.model.Employee;
import com.newgen.employee_management.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.BDDMockito;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository  employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    public void testGetAllEmployees(){
        // Given
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Praveen");
        employee.setLastName("Bhosle");
        employee.setEmail("Praveen@gmail.com");
        employee.setPhoneNumber("7412589632");
        employee.setDesignation("IT");
        employee.setSalary(new BigDecimal("45000"));
        employee.setDateOfBirth(LocalDate.now());
        employee.setJoiningDate(LocalDate.now());

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

}
