package com.newgen.employee_management.repository;

import com.newgen.employee_management.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Emp-Repo-Test")
@Slf4j
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setUp(){
        employee = new  Employee();
        employee.setFirstName("Praveen");
        employee.setLastName("Bhosle");
        employee.setEmail("Praveen@gmail.com");
        employee.setPhoneNumber("123456789");
        employee.setDesignation("IT");
        employee.setSalary(new BigDecimal("45000"));
        employee.setDateOfBirth(LocalDate.of(1995, 1, 1));
        employee.setJoiningDate(LocalDate.now());
    }

    @AfterEach
    public void tearDown(){
        log.info("================================Deleted=============");
        employeeRepository.deleteAll();
    }

    @Test
    public void testSave(){
        // Given
        // When
        Employee response = employeeRepository.save(employee);
        // Then
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals(employee.getFirstName(), response.getFirstName());
    }

    @Test
    public void testGetAll(){
        //Given
        employeeRepository.save(employee);
        //When
        List<Employee> response = employeeRepository.findAll();
        //Then
        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isEmpty());
        Assertions.assertEquals(1, response.size());
    }

    @Test
    public void testGetById(){
        //Given
        Employee savedEmployee = employeeRepository.save(employee);
        //When
        Optional<Employee> optionalEmployee = employeeRepository.findById(savedEmployee.getId());

        //Then
       Assertions.assertTrue(optionalEmployee.isPresent());
       Assertions.assertEquals(savedEmployee.getFirstName(), optionalEmployee.get().getFirstName());
    }

}
