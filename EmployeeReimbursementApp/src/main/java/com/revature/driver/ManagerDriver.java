package com.revature.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.models.Manager;
import com.revature.models.*;
import com.revature.service.ManagerService;
import com.revature.service.ManagerServiceImpl;

public class ManagerDriver {
	private static Logger logger = Logger.getLogger(ManagerDriver.class);
	private static ManagerService MService = new ManagerServiceImpl();
	
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
	
	public void mainMenu(String[] args) {
		logger.info("\nWelcome to the Manager portal!");
		login();
		managerPortal1(); 
	}

	public void login() {
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
			managerPortal1();
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
			getAllEmployees();
			viewAll();
			break;
		case 2:
			logger.info("In driver class: User chose option 2 (Display all requests)...");
			viewAll();
			break;
		case 3:
			logger.info("In driver class: User chose option 3 (Display all pending requests)...");
			viewAll();
			break;
		case 4:
			logger.info("In driver class: User chose option 4 (Display all approved requests)...");
			viewAll();
			break;
		case 5:
			logger.info("In driver class: User chose option 5 (Display all denied requests)...");
			viewAll();
			break;
		case 6:
			logger.info("In driver class: User chose option 6 (Display all from single employee)...");
			viewAll();
			break;
		case 7:
			logger.info("In driver class: User chose option 7 (Select by ticket ID)...");
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
		
	}
	
	private List<ERSMain> getAllPending() {
		
	}
	
	private List<ERSMain> getAllApproved() {
		
	}
	
	private List<ERSMain> getAllDenied() {
		
	}
	
	private List<ERSMain> getAllFromOne(int employeeId) {
		
	}

	private List<Employee> getAllEmployees() {
	
	}
	
	
	
	private ERSMain select(int index) {
		ERSMain temp = null;
		
		return ;
	}
}