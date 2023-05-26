package com.webapp.service;

import java.util.List;

import com.webapp.model.Employee;

public interface EmployeeService {

	public Employee createEmployee(Employee employee);

	public Employee getById(Long empId);

	public List<Employee> getAllEmployee();

	public Employee updateEmployee(Employee employee, Long empId);

	public String deleteEmployeeById(Long empId);

}
