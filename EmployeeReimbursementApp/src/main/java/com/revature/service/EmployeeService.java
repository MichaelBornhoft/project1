package com.revature.service;

import java.util.List;

import com.revature.models.Employee;


public interface EmployeeService {

	public Employee login(String Employeename, String password);
	
	public Employee findEmployeeById(int id);
	
	//public List<Employee> findAllEmployees();// can be used in manager java
	
	public boolean editEmployee(Employee e);
	
	
	
	// need implemenation for employee viewing their own info. 
}

