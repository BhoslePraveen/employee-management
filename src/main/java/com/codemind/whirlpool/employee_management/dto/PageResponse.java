package com.codemind.whirlpool.employee_management.dto;

import java.util.List;

public class PageResponse {
	private List<EmployeeDto> employeeDtos;
	private int totalPages;
	private long totalElements;
	private int currentPageElements;
	
	public int getCurrentPageElements() {
		return currentPageElements;
	}
	public void setCurrentPageElements(int currentPageElements) {
		this.currentPageElements = currentPageElements;
	}
	public List<EmployeeDto> getEmployeeDtos() {
		return employeeDtos;
	}
	public void setEmployeeDtos(List<EmployeeDto> employeeDtos) {
		this.employeeDtos = employeeDtos;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	@Override
	public String toString() {
		return "PageResponse [employeeDtos=" + employeeDtos + ", totalPages=" + totalPages + ", totalElements="
				+ totalElements + ", currentPageElements=" + currentPageElements + "]";
	}
	
}
