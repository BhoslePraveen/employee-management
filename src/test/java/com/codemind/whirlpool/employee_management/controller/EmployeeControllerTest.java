package com.codemind.whirlpool.employee_management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.codemind.whirlpool.employee_management.configuration.SecurityConfig;
import com.codemind.whirlpool.employee_management.dto.EmployeeDto;
import com.codemind.whirlpool.employee_management.enums.Status;
import com.codemind.whirlpool.employee_management.model.Employee;
import com.codemind.whirlpool.employee_management.repository.EmployeeRepository;
import com.codemind.whirlpool.employee_management.service.EmployeeService;
import com.codemind.whirlpool.employee_management.util.EmployeeConverter;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:/application-junit.properties")
@ActiveProfiles("junit")
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeControllerTest {

	private static final String SAVE_API = "/emp/save";
	private static final String GET_ALL_API = "/emp/all";
	private static final String GET_BY_ID = "/emp/";
	private static final String PUT_BY_ID = "/emp/";
	private static final String DELETE_BY_ID = "/emp/";

	@LocalServerPort
	private int port;


	@Autowired
	private EmployeeRepository employeeRepository;

	private RequestSpecification requestSpecification;

	@PostConstruct
	public void initRequestSpecification() {
		RequestSpecBuilder specBuilder = new RequestSpecBuilder();
		requestSpecification = specBuilder.setBaseUri("http://localhost:" + port).setContentType(ContentType.JSON)
				.build();
	}

	@BeforeEach
	public void init() {
		employeeRepository.deleteAll();
	}

	@Test
	@Order(1)
	public void testSaveEmployeeData() {
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
		ValidatableResponse restResponse = RestAssured.given(requestSpecification).basePath(SAVE_API).body(dto).when()
				.post().then();

		// Then
		EmployeeDto response = restResponse.extract().body().as(EmployeeDto.class);
		assertEquals(HttpStatus.CREATED.value(), restResponse.extract().statusCode());
		assertEquals("Praveen Bhosle", response.getName());
		assertNotNull(response.getId());

	}

	@Test
	@Order(2)
	public void testfetchAllEmployee() {
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
		ValidatableResponse restResponse = RestAssured.given(requestSpecification).basePath(GET_ALL_API).when().get()
				.then();

		// Then
		EmployeeDto[] response = restResponse.extract().body().as(EmployeeDto[].class);
		assertEquals(HttpStatus.OK.value(), restResponse.extract().statusCode());
		assertEquals(3, response.length);
		// TODO:TRY for checking names of employee

	}

	@Test
	@Order(3)
	public void testFetchEmployeeById() {
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

		Employee savedEmployee = employeeRepository.save(EmployeeConverter.getEmployeeEntity(dto));
		assertEquals(1, employeeRepository.count());

		// When
		ValidatableResponse restResponse = RestAssured.given(requestSpecification)
				.basePath(GET_BY_ID + savedEmployee.getEmpId()).when().get().then();

		// Then
		EmployeeDto response = restResponse.extract().body().as(EmployeeDto.class);
		assertEquals(HttpStatus.OK.value(), restResponse.extract().statusCode());
		assertEquals("Praveen Bhosle", response.getName());
		assertEquals(savedEmployee.getEmpId(), response.getId());

	}

	@Test
	@Order(4)
	public void testDeleteEmployeeData() {
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

		Employee savedEmp = employeeRepository.save(EmployeeConverter.getEmployeeEntity(dto));
		assertEquals(1, employeeRepository.count());

		// When
		ValidatableResponse restResponse = RestAssured.given(requestSpecification)
				.basePath(DELETE_BY_ID + savedEmp.getEmpId()).when().delete().then();

		// Then
		assertEquals(HttpStatus.OK.value(), restResponse.extract().statusCode());
		assertEquals(1, employeeRepository.count());
		String response = restResponse.extract().body().asString();
		assertEquals("Data Deleted for id : " + savedEmp.getEmpId(), response);

		Optional<Employee> data = employeeRepository.findById(savedEmp.getEmpId());
		Employee employee = data.get();
		assertEquals(Status.IN_ACTIVE, employee.getIsActive());

	}
	
	//TODO: COmplete the put API Testing Here.

}
