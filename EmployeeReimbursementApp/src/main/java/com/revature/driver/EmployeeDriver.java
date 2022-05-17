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
//import com.revature.service.class_uarkService;
//import com.revature.service.class_uarkServiceImpl;
import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceImpl;
import com.revature.service.ManagerService;
import com.revature.service.ManagerServiceImpl;
import com.revature.service.facultyService;
import com.revature.service.facultyServiceImpl;

public class EmployeeDriver {
	private static Logger logger = Logger.getLogger(ManagerDriver.class);
	
	private ERSMainServiceImpl TService = new ERSMainServiceImpl();
	private EmployeeService EService = new EmployeeServiceImpl();
	
	private Employee current = null;
	
	private Scanner in;
	
	private List<ERSMain> allReqs = new ArrayList<ERSMain>();
	
	public EmployeeDriver() {
		super();
		in = new Scanner(System.in);
	}

	//private static class_uarkService Class_uarkService = new class_uarkServiceImpl(); 
	//private static facultyService FacultyService = new facultyServiceImpl();
	
	public void mainMenu() {
		logger.info("\nWelcome to the Employee portal!");
		login();
		EmployeePortal1(); 
	}

	private void EmployeePortal1() {
		
		logger.info("\n Existing Account");
		System.out.println("\nPlease enter your user name:");
		in.nextLine();
	
		System.out.println("\nPlease enter your passowrd:");
		in.nextLine(); 
		
		System.out.println("Successful login, welcome to the faculty portal.");
		
		System.out.println("\nWelcome to the Employee registration portal!");
	
		logger.info("Starting application. Loading welcome screen...");
		System.out.println("Please choose from the following menu");
		System.out.println("1 - View all employee requests");
		System.out.println("0 - Exit app");
		System.out.println("\nSelection: ");
		in.nextLine(); 
		int ManagerPick = Integer.parseInt(in.nextLine());
		 
		switch(ManagerPick) {
		case 1:  
			logger.info("In driver class: User chose option 1 (View all employees)...");
			EmployeePortal2();
			break; 
		case 0: 
			logger.info("In driver class: User chose option 0 (Close app)...");
			System.out.println("Exiting app. Goodbye!");
			break; 
		default:
			logger.info("In ManagerDriver class: User performed invalid actions...");
			System.out.println("Invalid selection. Try again.");
			EmployeePortal1();
			break;
		}
	}

	private void EmployeePortal2() {
		System.out.println("Welcome " + current.getEmployeeName() + "!");
		System.out.println("1. Submit new request");
		System.out.println("2. Display your pending requests");
		System.out.println("3. Display your resolved requests");
		System.out.println("4. Review your personal information");
		System.out.println("5. Change your personal information");
		System.out.println("0. Go back");
		
		//prompt for input
		System.out.print("Input: ");
		
		int ManagerPick = Integer.parseInt(in.nextLine());
		 
		switch(ManagerPick) {
		case 1:
			logger.info("In driver class: User chose option 1 (Submit new request)...");
			Submit();
			EmployeePortal2();
			break;
		case 2:
			logger.info("In driver class: User chose option 2 (Display your pending requests)...");
			
			EmployeePortal2();
			break;
		case 3:
			logger.info("In driver class: User chose option 3 (Display your resolved requests)...");
			
			EmployeePortal2();
			break;
		case 4:
			logger.info("In driver class: User chose option 4 (Review your personal information)...");
			
			EmployeePortal2();
			break;
		case 5:
			logger.info("In driver class: User chose option 5 (Change your personal information)...");
			
			EmployeePortal2();
			break;
		case 0: 
		logger.info("In driver class: User chose option 0 (Go back)...");
		System.exit(0);
			return;
		default:
			logger.info("In ManagerDriver class: User performed invalid actions...");
			System.out.println("Invalid selection. Try again.");
			EmployeePortal2();
			break;
		}
	}
	
	
	
	private void Submit() {
		
	}

	private void login() {
		String username = "";
		String password = "";
		
		System.out.println("Please enter your username");
		username = tryInput();
		
		System.out.println("Please enter your password");
		
		current = EService.login(username, password);
		
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
}
