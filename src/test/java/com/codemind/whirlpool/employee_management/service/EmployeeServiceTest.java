package com.codemind.whirlpool.employee_management.service;

import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.codemind.whirlpool.employee_management.dto.EmployeeDto;
import com.codemind.whirlpool.employee_management.model.Employee;
import com.codemind.whirlpool.employee_management.repository.EmployeeRepository;
import com.codemind.whirlpool.employee_management.util.EmployeeConverter;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:/application-junit.properties")
@ActiveProfiles("junit")
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@MockBean
	private EmailService emailService;

	@BeforeEach
	public void init() {
		employeeRepository.deleteAll();
	}

	@Test
	public void testSaveEmployee() {
		// Given
		EmployeeDto dto = new EmployeeDto();
		dto.setName("Praveen Bhosle");
		dto.setGender("MALE");
		dto.setDob(LocalDate.of(1990, 02, 15));
		dto.setDepartment("COMPUTER_SCIENCE");
		dto.setEmail("XYZ@gmail.com");
		dto.setFaculty("Science");
		dto.setJoinDate(LocalDate.of(2023, 06, 25));
		dto.setUserName("Praveen");
		dto.setPhoneNumber("789512103");
		// When
		when(emailService.sendEmail("dummy", "Dummy", "Dummy")).thenReturn("Mail Sent Successfully");
		EmployeeDto response = employeeService.saveEmployee(dto);
		// Then
		assertNotNull(response);
		// TODO : Add more assertMEthods.
	}

	@Test
	public void testGetAllEmployees() {
		// Given
		EmployeeDto dto = new EmployeeDto();
		dto.setName("Praveen Bhosle");
		dto.setGender("MALE");
		dto.setDob(LocalDate.of(1990, 02, 15));
		dto.setDepartment("COMPUTER_SCIENCE");
		dto.setEmail("XYZ@gmail.com");
		dto.setFaculty("Science");
		dto.setJoinDate(LocalDate.of(2023, 06, 25));
		dto.setUserName("Praveen");
		dto.setPhoneNumber("789512103");

		EmployeeDto dto1 = new EmployeeDto();
		dto1.setName("Vishal Kumar");
		dto1.setGender("MALE");
		dto1.setDob(LocalDate.of(1990, 02, 15));
		dto1.setDepartment("COMPUTER_SCIENCE");
		dto1.setEmail("XYZ@gmail.com");
		dto1.setFaculty("Science");
		dto1.setJoinDate(LocalDate.of(2023, 06, 25));
		dto1.setUserName("Vishal");
		dto1.setPhoneNumber("785246334");

		EmployeeDto dto2 = new EmployeeDto();
		dto2.setName("Vivek Mudar");
		dto2.setGender("MALE");
		dto2.setDob(LocalDate.of(1990, 02, 15));
		dto2.setDepartment("COMPUTER_SCIENCE");
		dto2.setEmail("XYZ@gmail.com");
		dto2.setFaculty("Science");
		dto2.setJoinDate(LocalDate.of(2023, 06, 25));
		dto2.setUserName("Vivek");
		dto2.setPhoneNumber("963541012");

		List<Employee> empList = new ArrayList<>();
		empList.add(EmployeeConverter.getEmployeeEntity(dto));
		empList.add(EmployeeConverter.getEmployeeEntity(dto1));
		empList.add(EmployeeConverter.getEmployeeEntity(dto2));

		employeeRepository.saveAll(empList);
		assertEquals(3, employeeRepository.count());
		// When
		List<EmployeeDto> response = employeeService.getAllEmployees();
		// Then
		assertNotNull(response);
		assertEquals(3, response.size());
		// TODO : Add more assertMEthods.
	}

	// TODO: Complete the remaining methods

}
