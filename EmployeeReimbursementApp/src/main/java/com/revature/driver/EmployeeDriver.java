package com.revature.driver;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
//import com.revature.service.class_uarkService;
//import com.revature.service.class_uarkServiceImpl;
import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceImpl;
import com.revature.service.facultyService;
import com.revature.service.facultyServiceImpl;

public class EmployeeDriver {
	private static Logger logger = Logger.getLogger(EmployeeDriver.class);
	private static EmployeeService EmployeeService = new EmployeeServiceImpl();
	//private static class_uarkService Class_uarkService = new class_uarkServiceImpl(); 
	//private static facultyService FacultyService = new facultyServiceImpl();
	
	public static void mainMenu(String[] args) {
		EmployeePortal1(); 
	}
	
	public static void EmployeePortal1() {
		
		
		logger.info("\nWelcome to the Employee portal!");
		System.out.println("\nDo you have an Account?(Yes = 1, No = 2)");
		Scanner scanner = new Scanner(System.in);
		
		int userPick1 = Integer.parseInt(scanner.nextLine());
		
		switch (userPick1) {
		case 1:
			logger.info("\n Existing Account");
			System.out.println("\nPlease enter your user name:");
			scanner.nextLine();
		
			System.out.println("\nPlease enter your passowrd:");
			scanner.nextLine(); 
			
			System.out.println("Successful login, welcome to the faculty portal.");
			
			System.out.println("\nWelcome to the Employee registration portal!");
		
				logger.info("Starting application. Loading welcome screen...");
				System.out.println("Please choose from the following menu");
				System.out.println("1 - View available classes");
				System.out.println("2 - register for available classes");
				System.out.println("3 - remove myself from a class I registered for");
				System.out.println("4 - view my reigstered classes");
				System.out.println("0 - Exit app");
				System.out.println("\nSelection: ");
				scanner.nextLine(); 
				int EmployeePick = Integer.parseInt(scanner.nextLine());
				
				// need to create implementation for reimbursemtn system similar to this 
				switch(EmployeePick) {
				case 1:  Class_uarkService.getAllcourses();  
					break; 
				
				case 2: Class_uarkService.addNewcourse(null);
					break; 
				case 3: FacultyService.removecourse(null); 
					break; 
				case 4: System.out.println("No implementation for this portion."); 
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
					logger.info("In Employeedriver class: User performed invalid actions...");
					System.out.println("Invalid username or password. Try again.");
					EmployeePortal1();
					break;
					}
				
			break;
		
		case 2:
		Employee target = new Employee();
		
		logger.info("\n Creating new Employee Account");
		System.out.println("\nPlease set a user name:");
		String name = scanner.nextLine();
		
		System.out.println("\n Please set a password");
		String pass = scanner.nextLine(); 
		
		System.out.println("\nPlease enter your first name"); 
		String first = scanner.nextLine(); 
		
		System.out.println("\nPlease enter your last name"); 
		String last = scanner.nextLine(); 
		
		
		
		
		target.setEmployeename(first);
		target.setEmployeeLast_name(last);
		target.setEmployeeUser_name(name);
		target.setEmployeePass_word(pass); 
		
		EmployeeService.register(target); 
		EmployeePortal1(); 
		
		break;
		
	default:
		logger.info("In Employeedriver class: User performed invalid actions...");
		System.out.println("Invalid username or password. Try again.");
		EmployeePortal1();
		break;
		}
	}
}
