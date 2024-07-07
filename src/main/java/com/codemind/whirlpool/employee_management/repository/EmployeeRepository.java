package com.codemind.whirlpool.employee_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codemind.whirlpool.employee_management.enums.Status;
import com.codemind.whirlpool.employee_management.model.Employee;

@Repository //Optional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findByUserName(String user);
	List<Employee> findByIsActive(Status status);
	
	
	//HQL
	@Query("from Employee")
	public List<Employee> getAllEmployess();

	
	//Mysql Native query
	@Query(value = "select * from employee", nativeQuery = true)
	public List<Employee> getAllEmployessNative();

}
