package com.codemind.whirlpool.employee_management.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codemind.whirlpool.employee_management.dto.EmployeeDto;
import com.codemind.whirlpool.employee_management.dto.PageResponse;
import com.codemind.whirlpool.employee_management.enums.Department;
import com.codemind.whirlpool.employee_management.enums.ErrorCode;
import com.codemind.whirlpool.employee_management.enums.Gender;
import com.codemind.whirlpool.employee_management.enums.Status;
import com.codemind.whirlpool.employee_management.exception.DataNotFoundException;
import com.codemind.whirlpool.employee_management.exception.ResourceNotFoundException;
import com.codemind.whirlpool.employee_management.model.Employee;
import com.codemind.whirlpool.employee_management.repository.EmployeeRepository;
import com.codemind.whirlpool.employee_management.util.EmployeeConverter;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final EmployeeRepository employeeRepository;
	private final EmailService emailService;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmailService emailService) {
		this.employeeRepository = employeeRepository;
		this.emailService = emailService;
	}

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		log.info("START:EmployeeServiceImpl --->saveEmployee");
		log.debug("Converting the dto to entity");
		Employee employee = EmployeeConverter.getEmployeeEntity(employeeDto);
		Employee savedEmp = employeeRepository.save(employee);
		log.debug("Converting the entity to dto ");
		EmployeeDto response = EmployeeConverter.getEmployeeDto(savedEmp);
		triggerMailService(response.getName(), response.getEmail());
		log.info("END:EmployeeServiceImpl --->saveEmployee");
		return response;
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeDto> employeeDtos = new ArrayList<>();
		for (Employee emp : employees) {
			if ("ACTIVE".equalsIgnoreCase(emp.getIsActive().toString())) {
				EmployeeDto employeeDto = EmployeeConverter.getEmployeeDto(emp);
				employeeDtos.add(employeeDto);
			}

		}

		return employeeDtos;
	}

	// TODO: Create a API for this
	public PageResponse getAllByPageData(int pageNo, int pageSize) {
		// Sorting
		Sort sorting = Sort.by("firstName").ascending();
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sorting);

		Page<Employee> pageResponse = employeeRepository.findAll(pageRequest);

		List<Employee> content = pageResponse.getContent();
		List<EmployeeDto> employeeDtos = new ArrayList<>();
		content.forEach(employeeEntity -> {
			EmployeeDto dto = EmployeeConverter.getEmployeeDto(employeeEntity);
			employeeDtos.add(dto);
		});

		PageResponse response = new PageResponse();
		response.setEmployeeDtos(employeeDtos);
		response.setTotalPages(pageResponse.getTotalPages());
		response.setTotalElements(pageResponse.getTotalElements());
		response.setCurrentPageElements(pageResponse.getNumberOfElements());
		return response;
	}

	public void getDataByExample() {
		Employee employee = new Employee();
		employee.setGender(Gender.MALE);
		employee.setFaculty("Science");

		Example<Employee> exmaple = Example.of(employee);
		List<Employee> response = employeeRepository.findAll(exmaple);
		System.out.println(response);
	}
	
	public void getDataByFindBy() {
		List<Employee> byUserName = employeeRepository.findByUserName("vishal");
		List<Employee> byIsActive = employeeRepository.findByIsActive(Status.IN_ACTIVE);
		System.out.println(byUserName);
		System.out.println(byIsActive);
	}
	
	public void getDataByNativeAndHQL() {
		List<Employee> byUserName = employeeRepository.getAllEmployess();
		List<Employee> byIsActive = employeeRepository.getAllEmployessNative();
		System.out.println(byUserName);
		System.out.println(byIsActive);
	}

	@Override
	public EmployeeDto getEmployeeById(Long id) {
		Optional<Employee> employeeData = employeeRepository.findById(id);
		if (employeeData.isPresent()) {
			Employee employee = employeeData.get();
			EmployeeDto employeeDto = EmployeeConverter.getEmployeeDto(employee);
			return employeeDto;
		} else {
			throw new DataNotFoundException(ErrorCode.DATA_NOT_FOUND.getCode(), ErrorCode.DATA_NOT_FOUND.getValue());
		}
	}

	@Override
	public void deleteEmployeeById(Long id) {
		// employeeRepository.deleteById(id); ---> Hard Delete ---> Data is inconsistent
		// in DB.

		Optional<Employee> employeeData = employeeRepository.findById(id);
		if (employeeData.isPresent()) {
			Employee employee = employeeData.get();
			employee.setIsActive(Status.IN_ACTIVE);
			employeeRepository.save(employee);
		} else {
			throw new RuntimeException("Employee with given Id Does not exists");
		}
	}

	@Override
	public EmployeeDto updateEmployeeById(Long id, EmployeeDto dto) {
		// Step 1: Check if this Data already Exists
		Optional<Employee> employeeData = employeeRepository.findById(id);
		if (employeeData.isPresent()) {
			Employee existingEmployeeData = employeeData.get();
			Employee newEmployeeData = EmployeeConverter.getEmployeeEntity(dto);
			BeanUtils.copyProperties(newEmployeeData, existingEmployeeData);
			existingEmployeeData.setEmpId(id);
			Employee updatedEmpData = employeeRepository.save(existingEmployeeData);
			return EmployeeConverter.getEmployeeDto(updatedEmpData);
		} else {
			Employee newEmployeeData = EmployeeConverter.getEmployeeEntity(dto);
			Employee savedEmpData = employeeRepository.save(newEmployeeData);
			return EmployeeConverter.getEmployeeDto(savedEmpData);
		}
	}

	public String triggerMailService(String name, String toEmail) {
		String sub = "Welcome to Praveen IT Course";
		String body = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Welcome to the Java Course</title>\r\n" + "    <style>\r\n" + "        body {\r\n"
				+ "            font-family: Arial, sans-serif;\r\n" + "            background-color: #f4f4f4;\r\n"
				+ "            color: #333;\r\n" + "            margin: 0;\r\n" + "            padding: 0;\r\n"
				+ "        }\r\n" + "        .container {\r\n" + "            width: 100%;\r\n"
				+ "            max-width: 600px;\r\n" + "            margin: 0 auto;\r\n"
				+ "            background-color: #fff;\r\n" + "            padding: 20px;\r\n"
				+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" + "        }\r\n" + "        .header {\r\n"
				+ "            text-align: center;\r\n" + "            padding: 20px 0;\r\n" + "        }\r\n"
				+ "        .header img {\r\n" + "            max-width: 100px;\r\n" + "        }\r\n"
				+ "        .content {\r\n" + "            padding: 20px;\r\n" + "        }\r\n"
				+ "        .content h1 {\r\n" + "            color: #0066cc;\r\n" + "        }\r\n"
				+ "        .content p {\r\n" + "            line-height: 1.6;\r\n" + "        }\r\n"
				+ "        .button {\r\n" + "            display: block;\r\n" + "            width: 200px;\r\n"
				+ "            margin: 20px auto;\r\n" + "            padding: 10px;\r\n"
				+ "            background-color: #0066cc;\r\n" + "            color: #fff;\r\n"
				+ "            text-align: center;\r\n" + "            text-decoration: none;\r\n"
				+ "            border-radius: 5px;\r\n" + "        }\r\n" + "        .footer {\r\n"
				+ "            text-align: center;\r\n" + "            padding: 20px;\r\n"
				+ "            font-size: 12px;\r\n" + "            color: #777;\r\n" + "        }\r\n"
				+ "    </style>\r\n" + "</head>\r\n" + "<body>\r\n" + "    <div class=\"container\">\r\n"
				+ "        <div class=\"header\">\r\n"
				+ "            <img src=\"https://example.com/logo.png\" alt=\"Course Logo\">\r\n"
				+ "        </div>\r\n" + "        <div class=\"content\">\r\n"
				+ "            <h1>Welcome to the Java Course!</h1>\r\n" + "            <p>Dear Guru,</p>\r\n"
				+ "            <p>We are thrilled to have you join our Java course. This course is designed to help you master the fundamentals of Java programming and build a strong foundation for your future development projects.</p>\r\n"
				+ "            <p>Here are a few important details to get you started:</p>\r\n" + "            <ul>\r\n"
				+ "                <li><strong>Course Start Date:</strong> 2024-08-08</li>\r\n"
				+ "                <li><strong>Course Duration:</strong> 5 Months</li>\r\n"
				+ "                <li><strong>Instructor:</strong> Praveen Bhosle/li>\r\n"
				+ "                <li><strong>Course Platform:</strong> Java</li>\r\n" + "            </ul>\r\n"
				+ "            <p>Please ensure you have the following ready before the course begins:</p>\r\n"
				+ "            <ul>\r\n" + "                <li>A computer with internet access</li>\r\n"
				+ "                <li>Java Development Kit (JDK) installed</li>\r\n"
				+ "                <li>An Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse</li>\r\n"
				+ "            </ul>\r\n"
				+ "            <p>If you have any questions or need assistance, feel free to reach out to our support team at [Support Email].</p>\r\n"
				+ "            <p>We look forward to helping you on your journey to becoming a Java expert!</p>\r\n"
				+ "            <p>Best regards,</p>\r\n" + "            <p>The velocity Team</p>\r\n"
				+ "            <a class=\"button\">Get Started</a>\r\n" + "        </div>\r\n"
				+ "        <div class=\"footer\">\r\n"
				+ "            <p>&copy; 2024 Velocity. All rights reserved.</p>\r\n" + "            <p>Pune</p>\r\n"
				+ "        </div>\r\n" + "    </div>\r\n" + "</body>\r\n" + "</html>\r\n";
		String response = emailService.sendEmail(toEmail, sub, body);
		return response;
	}

}
