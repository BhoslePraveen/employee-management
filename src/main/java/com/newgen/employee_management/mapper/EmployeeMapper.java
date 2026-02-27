package com.newgen.employee_management.mapper;

import com.newgen.employee_management.dto.request.EmployeeRequestDto;
import com.newgen.employee_management.dto.response.EmployeeDto;
import com.newgen.employee_management.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(source = "id", target = "empId")
    @Mapping(source = "dateOfBirth", target = "dob")
    @Mapping(source = "joiningDate", target = "jd")
    @Mapping(target = "fullName",expression = "java(employee.getFirstName()+\" \"+ employee.getLastName())")
    EmployeeDto toDto(Employee employee);

    @Mapping(target = "id" ,ignore = true)
    @Mapping(target = "joiningDate" ,defaultExpression = "java(java.time.LocalDate.now())")
    Employee toEntity(EmployeeRequestDto requestDto);

    List<EmployeeDto> toDtos(List<Employee> employees);

}
