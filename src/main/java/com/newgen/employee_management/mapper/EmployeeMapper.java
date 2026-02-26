package com.newgen.employee_management.mapper;

import com.newgen.employee_management.dto.request.EmployeeRequestDto;
import com.newgen.employee_management.dto.response.EmployeeDto;
import com.newgen.employee_management.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(source = "id", target = "empId")
    @Mapping(source = "dateOfBirth", target = "dob")
    @Mapping(source = "joiningDate", target = "jd")
    EmployeeDto toDto(Employee employee);

    @Mapping(target = "id" ,ignore = true)
    Employee toEntity(EmployeeRequestDto requestDto);
}
