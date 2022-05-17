package com.revature.driver;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.models.Manager;
import com.revature.service.ManagerService;
import com.revature.service.ManagerServiceImpl;
import com.revature.models.ERSMain;
import com.revature.service.ERSService;
import com.revature.service.ERSServiceImpl;

public class ManagerDriver {
	private static Logger logger = Logger.getLogger(ManagerDriver.class);
	private static ManagerService ManagerService = new ManagerServiceImpl();
	//private static class_uarkService Class_uarkService = new class_uarkServiceImpl(); 
	//private static facultyService FacultyService = new facultyServiceImpl();
	
	public static void mainMenu(String[] args) {
		managerPortal1(); 
	}
	
	public static void managerPortal1() {
		
		
		logger.info("\nWelcome to the Manager portal!");

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
}
