package com.demo.employeeportal.service;

import java.util.List;

import com.demo.employeeportal.model.Employee;
import com.demo.employeeportal.model.EmployeeRegResponse;

public interface EmployeeService {
	
	Employee findById(long id);
	
	Employee findByName(String firstName, String lastName);
	
	void saveEmployee(Employee employee);
	
	void updateEmployee(Employee employee);
	
	void deleteEmployeeById(long id);

	List<Employee> findAllEmployees(); 
	
	void deleteAllEmployees();
	
	public boolean isEmployeeExist(Employee employee);
	
	public void updatEmployee(EmployeeRegResponse response);
	
}
