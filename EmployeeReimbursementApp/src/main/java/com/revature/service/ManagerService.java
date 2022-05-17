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
}
