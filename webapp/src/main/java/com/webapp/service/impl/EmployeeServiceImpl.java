package com.webapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.webapp.exception.EmployeeNotFoundException;
import com.webapp.model.Employee;
import com.webapp.repository.EmployeeRepository;
import com.webapp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		Employee response = null;
		try {
			response = employeeRepository.save(employee);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Employee getById(Long empId) {
		Optional<Employee> response = employeeRepository.findById(empId);
		if (response.isEmpty()) {
			throw new EmployeeNotFoundException("Employee Not Found" + HttpStatus.NOT_FOUND);
		}
		return response.get();
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> response = employeeRepository.findAll();
		if (response.isEmpty()) {
			throw new EmployeeNotFoundException("Employee Not Found" + HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@Override
	public Employee updateEmployee(Employee employee, Long empId) {
		if(employeeRepository.existsById(empId)) {
			Employee response = employeeRepository.findById(empId).get();
			response.setFirstName(employee.getFirstName());
			response.setLastName(employee.getLastName());
			response.setEmail(employee.getEmail());
			Employee save = employeeRepository.save(response);
			return save;
		}else {
			throw new EmployeeNotFoundException("Employee Not Found"+HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public String deleteEmployeeById(Long empId) {
		if (!employeeRepository.existsById(empId)) {
			throw new EmployeeNotFoundException("Employee Not Found" + HttpStatus.NOT_FOUND);
		}
		employeeRepository.deleteById(empId);
		return "Employee deleted successfully";
	}

}
