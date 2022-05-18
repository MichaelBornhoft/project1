package com.revature.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.models.ERSMain;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.service.ERSMainServiceImpl;
import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceImpl;
import com.revature.service.ManagerService;
import com.revature.service.ManagerServiceImpl;

public class ManagerDriver {
	private static Logger logger = Logger.getLogger(ManagerDriver.class);
	private ManagerService MService = new ManagerServiceImpl();
	private ERSMainServiceImpl TService = new ERSMainServiceImpl();
	private EmployeeService EService = new EmployeeServiceImpl();
	
	private Manager current = null;
	
	private Scanner in;
	
	private List<Employee> allEmps = new ArrayList<Employee>();
	private List<ERSMain> allReqs = new ArrayList<ERSMain>();
	
	
	public ManagerDriver() {
		super();
		in = new Scanner(System.in);
	}

	//private static class_uarkService Class_uarkService = new class_uarkServiceImpl(); 
	//private static facultyService FacultyService = new facultyServiceImpl();
	
	public void mainMenu() {
		logger.info("\nWelcome to the Manager portal!");
		login();
		managerPortal1(); 
	}

	private void login() {
		String username = "";
		String password = "";
		
		System.out.println("Please enter your username");
		username = tryInput();
		
		System.out.println("Please enter your password");
		
		current = MService.login(username, password);
		
		if (current == null) {
			System.out.println("Invalid input. Please try again");
			login();
		}
	}
	
	private String tryInput() {
		String next = "";
		
		try {
			next = in.next();
		}
		catch (NoSuchElementException a) {
			System.out.print("This is an invalid input\n");
			in.next();
			next = tryInput();
		}
		
		return next;
	}
	
	public void managerPortal1() {

		Scanner scanner = new Scanner(System.in);
			
		logger.info("\n Existing Account");
		System.out.println("\nPlease enter your user name:");
		scanner.nextLine();
	
		System.out.println("\nPlease enter your passowrd:");
		scanner.nextLine(); 
		
		System.out.println("Successful login, welcome to the faculty portal.");
		
		System.out.println("\nWelcome to the Employee registration portal!");
	
		logger.info("Starting application. Loading welcome screen...");
		System.out.println("Please choose from the following menu");
		System.out.println("1 - View all employee requests");
		System.out.println("0 - Exit app");
		System.out.println("\nSelection: ");
		scanner.nextLine(); 
		int ManagerPick = Integer.parseInt(scanner.nextLine());
		 
		switch(ManagerPick) {
		case 1:
			logger.info("In driver class: User chose option 1 (View all employees)...");
			viewAll();
			break; 
		case 0: 
		logger.info("In driver class: User chose option 0 (close app)...");
		System.out.println("Exiting app. Goodbye!");
		//close the scanner
		scanner.close();
		//close the app
		System.exit(0);
			break; 
		default:
			logger.info("In ManagerDriver class: User performed invalid actions...");
			System.out.println("Invalid selection. Try again.");
			managerPortal1();
			break;
		}
	
	}
	
	private void viewAll() {
		System.out.println("1. Display all employees");
		System.out.println("2. Display all requests");
		System.out.println("3. Display all requests pending");
		System.out.println("4. Display all approved requests");
		System.out.println("5. Display all denied requests");
		System.out.println("6. Display all from single employee");
		System.out.println("7. Select by ticket ID");
		System.out.println("0. Go back");
		
		//prompt for input
		System.out.print("Input: ");
		
		int ManagerPick = Integer.parseInt(in.nextLine());
		 
		switch(ManagerPick) {
		case 1:
			logger.info("In driver class: User chose option 1 (Display all employees)...");
			allEmps = getAllEmployees();
			displayEmployees(allEmps);
			viewAll();
			break;
		case 2:
			logger.info("In driver class: User chose option 2 (Display all requests)...");
			allReqs = getAll();
			displayTickets(allReqs);
			viewAll();
			break;
		case 3:
			logger.info("In driver class: User chose option 3 (Display all pending requests)...");
			allReqs = getAllPending();
			displayTickets(allReqs);			
			viewAll();
			break;
		case 4:
			logger.info("In driver class: User chose option 4 (Display all approved requests)...");
			allReqs = getAllApproved();
			displayTickets(allReqs);
			viewAll();
			break;
		case 5:
			logger.info("In driver class: User chose option 5 (Display all denied requests)...");
			allReqs = getAllDenied();
			displayTickets(allReqs);
			viewAll();
			break;
		case 6:
			logger.info("In driver class: User chose option 6 (Display all from single employee)...");
			System.out.println("Please eneter an employee ID");
			int tempInput = Integer.parseInt(in.nextLine());
			allReqs = getAllFromOne(tempInput);
			displayTickets(allReqs);
			viewAll();
			break;
		case 7:
			logger.info("In driver class: User chose option 7 (Select by ticket ID)...");
			System.out.println("Please eneter an employee ID");
			int tempTicket = Integer.parseInt(in.nextLine());
			select(tempTicket);
			viewAll();
			break;
		case 0: 
		logger.info("In driver class: User chose option 0 (Go back)...");
		System.exit(0);
			return;
		default:
			logger.info("In ManagerDriver class: User performed invalid actions...");
			System.out.println("Invalid selection. Try again.");
			managerPortal1();
			break;
		}
	}

	private List<ERSMain> getAll() {
		return TService.finadallTickets();
	}
	
	private List<ERSMain> getAllPending() {
		return TService.finadallPending();
	}
	
	private List<ERSMain> getAllApproved() {
		return TService.finadallApproved();
	}
	
	private List<ERSMain> getAllDenied() {
		return TService.finadallDenied();
	}
	
	private List<ERSMain> getAllFromOne(int employeeId) {
		return TService.findTicketsByEmployee_Id(employeeId);
	}

	private List<Employee> getAllEmployees() {
		return EService.findAllEmployees();
	}
	
	private void displayTickets(List<ERSMain> tickets) {
		tickets.forEach((n) -> System.out.println(n));
	}
	
	private void displayEmployees(List<Employee> employees) {
		employees.forEach((n) -> System.out.println(n));
	}
	
	private void select(int index) {
		ERSMain temp = TService.findTicketById(index);
		
		System.out.println(temp);
		System.out.println("\n\n Approve(1) or deny(0)?");
		
		int ManagerPick = Integer.parseInt(in.nextLine());
		 
		switch(ManagerPick) {
		case 1:  
			logger.info("In driver class: User chose option 1 (Approve request)...");
			System.out.println("Request approved\n\n");
			
			TService.approve(temp);
			break; 
		case 0: 
			logger.info("In driver class: User chose option 0 (Deny request)...");
			System.out.println("Request denied\n\n");

			TService.deny(temp);
			break; 
		default:
			logger.info("In ManagerDriver class: User performed invalid actions...");
			System.out.println("Invalid selection. Try again.");
			select(index);
			break;
		}
	}
}