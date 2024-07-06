package com.codemind.whirlpool.employee_management;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:/application-junit.properties")
@ActiveProfiles("junit")
class EmployeeManagementApplicationTests {

	@Test
	void contextLoads() {
	}

}
