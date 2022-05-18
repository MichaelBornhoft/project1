package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


// need to write implementation for mangers.


public class EmployeeReimburseApp extends HttpServlet{
	private static final long serialVersionUID = 8339100247721381693L;

	private static Logger log = Logger.getLogger(EmployeeReimburseApp.class);
	
	//GET methods are used to retrieve resources from an API 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//save the URI and rewrite it to determine what functionality the Employee is requesting based on that endpoint
		final String URI = req.getRequestURI().replace("/EmployeeReimburseApp/", "");
		log.info("URI: " + URI);
		
		switch (URI) {
		case "Employees": // query the DB and return a list of all Employees
			log.info("getting Employee list...");
			RequestHelperEmp.processAllEmployees(req, resp);
			break;
		case "Employee":
			log.info("search Employee by name or id. URI: " + URI);
			RequestHelperEmp.processEmployeeById(req, resp);
			break;
		default:
			log.info("showing error message...");
			RequestHelperEmp.processError(req, resp);
			break;
		}
	}
	
	//POST methods are used for creating resources in your API
	//instead of passing data within the URL, it safely passes that info only in the body
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//save the URI and rewrite it to determine what functionality the Employee is requesting based on that endpoint
		final String URI = req.getRequestURI().replace("/EmployeeReimburseApp/", "");
		log.info("URI: " + URI);
		
		switch (URI) {
		case "register": 
			log.info("registering new Employee...");
			RequestHelperEmp.processRegistration(req, resp);
			break;
		default:
			log.info("showing error message...");
			RequestHelperEmp.processError(req, resp);
			break;
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//save the URI and rewrite it to determine what functionality the Employee is requesting based on that endpoint
		final String URI = req.getRequestURI().replace("/EmployeeReimburseApp/", "");
		log.info("URI: " + URI);
		
		switch (URI) {
		case "update": 
			log.info("updating Employee...");
			RequestHelperEmp.processEmployeeUpdate(req, resp);
			break;
		default:
			log.info("showing error message...");
			RequestHelperEmp.processError(req, resp);
			break;
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//save the URI and rewrite it to determine what functionality the Employee is requesting based on that endpoint
		final String URI = req.getRequestURI().replace("/EmployeeReimburseApp/", "");
		log.info("URI: " + URI);
		
		switch (URI) {
		case "delete": 
			log.info("removing Employee...");
			RequestHelperEmp.processEmployeeDelete(req, resp);
			break;
		default:
			log.info("showing error message...");
			RequestHelperEmp.processError(req, resp);
			break;
		}
	}
}
