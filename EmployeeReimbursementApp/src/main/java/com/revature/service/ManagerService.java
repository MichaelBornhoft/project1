package com.revature.service;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Manager;

public interface ManagerService {

	public Manager login(String Managername, String password);
	
	public int register(Manager Manager);
	
	public Manager findManagerById(int id);
	
	public boolean editManager(Manager Manager);
	
	public boolean deleteManager(Manager Manager);
	
	public List<Employee> findAllEmployees();
	
	// manager can view all pending requests from employees (ie the pending table);
	
	
	// manager can view all resolved requests (ie the approved table); 
	
	// manager can view reimbursement requests from a single employee 
	
	// manager approve and deny the request from the pending table. 
}
