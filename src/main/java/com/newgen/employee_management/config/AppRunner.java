package com.newgen.employee_management.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newgen.employee_management.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Employee emp1 = new Employee();
//        emp1.setFirstName("Vishal");
//        emp1.setLastName("Kumar");
//        emp1.setEmail("Vishal@gmail.com");
//        emp1.setPhoneNumber("8075158965");
//        emp1.setDesignation("IT");
//        emp1.setSalary(new BigDecimal("25000.00"));
//        emp1.setDateOfBirth(LocalDate.of(1990, 1, 1));
//
//        Employee emp2 = new Employee();
//        emp2.setFirstName("Bhaskar");
//        emp2.setLastName("Kumar");
//        emp2.setEmail("Bhaskar@gmail.com");
//        emp2.setPhoneNumber("8075158965");
//        emp2.setDesignation("IT");
//        emp2.setSalary(new BigDecimal("25000.00"));
//
//        List<Employee> employees = Arrays.asList(emp1, emp2);
//        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employees);
//        System.out.println(jsonString);
//
//        String json2 = "[ {\n" +
//                "  \"id\" : null,\n" +
//                "  \"firstName\" : \"Vishal\",\n" +
//                "  \"lastName\" : \"Kumar\",\n" +
//                "  \"email\" : \"Vishal@gmail.com\",\n" +
//                "  \"phoneNumber\" : \"8075158965\",\n" +
//                "  \"designation\" : \"IT\",\n" +
//                "  \"salary\" : 25000.00,\n" +
//                "  \"dateOfBirth\" : \"1990-01-01\",\n" +
//                "  \"joiningDate\" : null\n" +
//                "}, {\n" +
//                "  \"id\" : null,\n" +
//                "  \"firstName\" : \"Bhaskar\",\n" +
//                "  \"lastName\" : \"Kumar\",\n" +
//                "  \"email\" : \"Bhaskar@gmail.com\",\n" +
//                "  \"phoneNumber\" : \"8075158965\",\n" +
//                "  \"designation\" : \"IT\",\n" +
//                "  \"salary\" : 25000.00,\n" +
//                "  \"dateOfBirth\" : null,\n" +
//                "  \"is_Active\" : true,\n" +
//                "  \"joiningDate\" : null\n" +
//                "} ]";
//
//
//        // Coverting to Object
//        List<Employee> employeeList = objectMapper.
//                readValue(json2, new TypeReference<List<Employee>>() {});
//        employeeList.forEach(System.out::println);

    }
}
