package com.revature.driver;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class MainDriver {
	private static Logger logger = Logger.getLogger(MainDriver.class);
	

	public static void main(String[] args) {
		welcomeScreen();
	}

	public static void welcomeScreen() {
		Scanner scanner = new Scanner(System.in);
		ManagerDriver mDriver = new ManagerDriver();
		EmployeeDriver eDriver = new EmployeeDriver();
		
		logger.info("Starting application. Loading welcome screen...");
		System.out.println("Welcome to the Employee Reimbursement App!");
		System.out.println("Are you an Employee or a Manager?");
		System.out.println("1 - Employee");
		System.out.println("2 - Manager");
		System.out.println("0 - Exit app");
		System.out.println("\nSelection: ");
		
		int userPick = Integer.parseInt(scanner.nextLine());
		
		
		switch (userPick) {
		case 1: eDriver.mainMenu();

			break;
		
		case 2: mDriver.managerPortal1(); 
		
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
			logger.info("In driver class: User chose an invalid option...");
			System.out.println("Invalid option. Try again.");
			welcomeScreen();
			break;
		}
	}
}
