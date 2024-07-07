package com.codemind.whirlpool.employee_management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.codemind.whirlpool.employee_management.dto.PageResponse;
import com.codemind.whirlpool.employee_management.service.EmployeeServiceImpl;

@SpringBootApplication
public class EmployeeManagementApplication {
	

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(EmployeeManagementApplication.class, args);
		EmployeeServiceImpl serviceImpl = applicationContext.getBean(EmployeeServiceImpl.class);
		serviceImpl.getDataByNativeAndHQL();
	}

}
