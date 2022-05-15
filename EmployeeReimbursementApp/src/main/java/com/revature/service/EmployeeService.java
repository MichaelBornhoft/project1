package com.revature.service;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeService {

	public Employee login(String Employeename, String password);
	
	public int register(Employee Employee);
	
	public Employee findEmployeeById(int id);
	
	public Employee findEmployeeByFirstName(String firstName);
	
	public List<Employee> findAllEmployees();
	
	public boolean editEmployee(Employee Employee);
	
	public boolean deleteEmployee(Employee Employee);
}

